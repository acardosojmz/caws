package edu.tecnm.caws.domain

import edu.tecnm.caws.domain.model.Participant

interface ParticipantRepository {
    suspend fun addParticipant(participant: Participant)
    suspend fun getParticipants(): List<Participant>
}