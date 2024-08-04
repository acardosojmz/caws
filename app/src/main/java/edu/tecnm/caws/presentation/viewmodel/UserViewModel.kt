package edu.tecnm.caws.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.tecnm.caws.domain.usecase.UserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val _user = MutableStateFlow<FirebaseUser?>(null)
    val user: StateFlow<FirebaseUser?> get() = _user

    private val _uiState = MutableStateFlow(RegisterState())
    val uiState: StateFlow<RegisterState> = _uiState.asStateFlow()

    fun register(email: String, password: String) {
        viewModelScope.launch {
            try {
                userUseCase.register(email, password)
                _uiState.value = _uiState.value.copy(success = true)
            } catch (e: FirebaseAuthWeakPasswordException) {
                _uiState.value = _uiState.value.copy(error = "Weak Password", success = false)
            } catch (e: FirebaseAuthUserCollisionException) {
                _uiState.value = _uiState.value.copy(error = "User already exists")
            } catch (e: FirebaseAuthException) {
                _uiState.value = _uiState.value.copy(error = "Registration failed")
            }
        }
    }

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EmailChanged ->
                _uiState.value = _uiState.value.copy(email = event.email)

            is RegisterEvent.PasswordChanged ->
                _uiState.value = _uiState.value.copy(password = event.password)

            is RegisterEvent.ConfirmPasswordChanged ->
                _uiState.value = _uiState.value.copy(confirmPassword = event.confirmPassword)

            is RegisterEvent.RegisterClicked -> register(
                email = _uiState.value.email,
                password = _uiState.value.password
            )

            is RegisterEvent.ResultChanged ->
                _uiState.value = _uiState.value.copy(success = false)
            is RegisterEvent.EmptyControls -> {
                _uiState.value = _uiState.value.copy(email = "",
                    password = "", confirmPassword = "", user = null,
                    success = false, error = "")
            }
        }
    }


}
    data class RegisterState(
        val email: String = "",
        val password: String = "",
        val confirmPassword: String="",
        val user: FirebaseUser? = null,
        val error: String = "",
        val success: Boolean=false
    )


    sealed class RegisterEvent {
        data class EmailChanged(val email: String) : RegisterEvent()
        data class PasswordChanged(val password: String) : RegisterEvent()
        data class ConfirmPasswordChanged(val confirmPassword: String) : RegisterEvent()
        data class ResultChanged(val success: Boolean): RegisterEvent()
        data object RegisterClicked : RegisterEvent()
        data object EmptyControls:RegisterEvent()
    }
