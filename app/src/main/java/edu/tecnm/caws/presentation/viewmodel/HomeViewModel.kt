package edu.tecnm.caws.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.tecnm.caws.domain.FirebaseAuthRepository
import edu.tecnm.caws.domain.UserRepository
import edu.tecnm.caws.domain.model.User
import edu.tecnm.caws.domain.usecase.LogoutUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logoutUser: LogoutUser,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _user = MutableStateFlow(userRepository.getCurrentUser())
    val user: StateFlow<FirebaseUser?> = _user.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.LogoutClicked -> logout()
        }
    }

    private fun logout() {
        viewModelScope.launch {
            logoutUser()
        }
    }

}


sealed class HomeEvent {
    data object LogoutClicked : HomeEvent()
}