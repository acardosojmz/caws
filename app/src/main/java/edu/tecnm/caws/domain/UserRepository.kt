package edu.tecnm.caws.domain

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun login(email: String, password: String): Flow<Result<FirebaseUser?>>
    suspend fun register(email: String, password: String): FirebaseUser?
    fun getCurrentUser(): FirebaseUser?
    fun logout()
}