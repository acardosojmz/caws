package edu.tecnm.caws.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.tecnm.caws.domain.model.User
import edu.tecnm.caws.domain.usecase.AuthenticateUser
import edu.tecnm.caws.domain.usecase.UserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged ->
                _uiState.value = _uiState.value.copy(email = event.email)
            is LoginEvent.PasswordChanged ->
                _uiState.value = _uiState.value.copy(password = event.password)
            LoginEvent.LoginClicked -> login()
        }
    }

    private fun login() {
        viewModelScope.launch {
            userUseCase.login(_uiState.value.email, _uiState.value.password)
                .collect { result ->
                    result.onSuccess { user ->
                        _uiState.value = _uiState.value.copy(user = user, error = "", success = true)
                    }.onFailure { exception ->
                        _uiState.value = _uiState.value.copy(error = exception.message ?: "Unknown error")
                    }
                }
        }
    }
}

data class LoginState(
    val email: String = "",
    val password: String = "",
    val user: FirebaseUser? = null,
    val error: String = "",
    val success: Boolean=false
    )


sealed class LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    data object LoginClicked : LoginEvent()
}