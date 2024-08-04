package edu.tecnm.caws.data

import com.google.firebase.auth.FirebaseUser
import edu.tecnm.caws.domain.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseAuthService: FirebaseAuthService
) : UserRepository {
    override suspend fun login(email: String, password: String): Flow<Result<FirebaseUser?>> {
        return firebaseAuthService.login(email, password)
    }

    override suspend fun register(email: String, password: String): FirebaseUser?  {
        return firebaseAuthService.register(email, password)
    }

    override fun getCurrentUser(): FirebaseUser? {
        return firebaseAuthService.getCurrentUser()
    }

    override fun logout() {
        firebaseAuthService.logout()
    }
}