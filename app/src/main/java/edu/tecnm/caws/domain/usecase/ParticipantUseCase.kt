package edu.tecnm.caws.domain.usecase

import edu.tecnm.caws.domain.ParticipantRepository
import edu.tecnm.caws.domain.model.Participant
import javax.inject.Inject

class ParticipantUseCase @Inject constructor(
    private val participantRepository: ParticipantRepository
) {
    suspend fun addParticipant(participant: Participant) {
        participantRepository.addParticipant(participant)
    }

    suspend fun getParticipants(): List<Participant> {
        return participantRepository.getParticipants()
    }
}