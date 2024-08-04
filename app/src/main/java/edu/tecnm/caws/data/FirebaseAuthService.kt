package edu.tecnm.caws.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import edu.tecnm.caws.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthService  @Inject constructor( val auth: FirebaseAuth) {

    suspend fun login(email: String, password: String): Flow<Result<FirebaseUser?>> = flow {
       try {
        val result = auth.signInWithEmailAndPassword(email, password).await()
        emit(Result.success(result.user))
       } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    suspend fun register(email: String, password: String): FirebaseUser? {
        try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            return result.user
        } catch (e:Exception) {
            throw e
        }
    }

    fun getCurrentUser(): FirebaseUser? = auth.currentUser

    fun logout() = auth.signOut()
}