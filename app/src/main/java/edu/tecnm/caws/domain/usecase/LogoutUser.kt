package edu.tecnm.caws.domain.usecase

import edu.tecnm.caws.domain.FirebaseAuthRepository
import javax.inject.Inject

class LogoutUser @Inject constructor(
    private val repository: FirebaseAuthRepository
) {
    operator fun invoke() {
        repository.logout()
    }
}