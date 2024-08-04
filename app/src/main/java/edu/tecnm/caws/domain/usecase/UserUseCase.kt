package edu.tecnm.caws.domain.usecase

import com.google.firebase.auth.FirebaseUser
import edu.tecnm.caws.domain.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun login(email: String, password: String): Flow<Result<FirebaseUser?>> {
        return userRepository.login(email, password)
    }

    suspend fun register(email: String, password: String):FirebaseUser? {
        return userRepository.register(email, password)
    }

    fun getCurrentUser(): FirebaseUser? {
        return userRepository.getCurrentUser()
    }

    fun logout() {
        userRepository.logout()
    }
}