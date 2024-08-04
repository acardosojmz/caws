package edu.tecnm.caws.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.tecnm.caws.data.CourseRepositoryImpl
import edu.tecnm.caws.data.FirebaseAuthService
import edu.tecnm.caws.data.FirebaseFirestoreService
import edu.tecnm.caws.data.UserRepositoryImpl
import edu.tecnm.caws.domain.CourseRepository
import edu.tecnm.caws.domain.UserRepository

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    // ----------
    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseAuthService(auth: FirebaseAuth): FirebaseAuthService {
        return FirebaseAuthService(auth)
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestoreService(db : FirebaseFirestore): FirebaseFirestoreService {
        return FirebaseFirestoreService(db)
    }

    @Provides
    @Singleton
    fun provideUserRepository(firebaseAuthService: FirebaseAuthService): UserRepository {
        return UserRepositoryImpl(firebaseAuthService)
    }

    @Provides
    @Singleton
    fun provideCourseRepository(firebaseFirestoreService: FirebaseFirestoreService): CourseRepository {
        return CourseRepositoryImpl(firebaseFirestoreService)
    }

}