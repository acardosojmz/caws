package edu.tecnm.caws.domain

import edu.tecnm.caws.domain.model.User
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthRepository {
    fun login(email: String, password: String): Flow<Result<User>>
    fun logout()
}