package edu.tecnm.caws.data

import edu.tecnm.caws.domain.ParticipantRepository
import edu.tecnm.caws.domain.model.Participant
import javax.inject.Inject

class ParticipantRepositoryImpl @Inject constructor(
    private val firebaseFirestoreService: FirebaseFirestoreService
) : ParticipantRepository {
    override suspend fun addParticipant(participant: Participant) {
        firebaseFirestoreService.addParticipant(participant)
    }

    override suspend fun getParticipants(): List<Participant> {
        return firebaseFirestoreService.getParticipants()
    }

}