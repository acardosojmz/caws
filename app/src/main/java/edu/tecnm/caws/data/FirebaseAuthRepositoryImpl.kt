package edu.tecnm.caws.data

import com.google.firebase.auth.FirebaseAuth
import edu.tecnm.caws.domain.FirebaseAuthRepository
import edu.tecnm.caws.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthRepositoryImpl @Inject constructor(
    private val authService: FirebaseAuth
) : FirebaseAuthRepository {

    override fun login(email: String, password: String): Flow<Result<User>> = flow {
        try {
            val authResult = authService.signInWithEmailAndPassword(email, password).await()
            val user = authResult.user?.let { User(it.uid, it.email.toString()) }
            emit(Result.success(user) as Result<User>)
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun logout() {
        authService.signOut()
    }

}