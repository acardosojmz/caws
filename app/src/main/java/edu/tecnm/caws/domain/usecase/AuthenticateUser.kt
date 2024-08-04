package edu.tecnm.caws.domain.usecase

import edu.tecnm.caws.domain.FirebaseAuthRepository
import edu.tecnm.caws.domain.model.User

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthenticateUser @Inject constructor(
    private val repository: FirebaseAuthRepository
) {
    operator fun invoke(email: String, password: String): Flow<Result<User>> {
        return repository.login(email, password)
    }
}