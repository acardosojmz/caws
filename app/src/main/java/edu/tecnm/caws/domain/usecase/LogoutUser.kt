package edu.tecnm.caws.domain.usecase

import edu.tecnm.caws.domain.UserRepository
import javax.inject.Inject

class LogoutUser @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke() {
        repository.logout()
    }
}