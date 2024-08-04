package edu.tecnm.caws.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.tecnm.caws.domain.model.Participant
import edu.tecnm.caws.domain.usecase.ParticipantUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ParticipantViewModel(
    private val participantUseCase: ParticipantUseCase
) : ViewModel() {

    private val _participants = MutableStateFlow<List<Participant>>(emptyList())
    val participants: StateFlow<List<Participant>> get() = _participants

    fun addParticipant(participant: Participant) {
        viewModelScope.launch {
            participantUseCase.addParticipant(participant)
            loadParticipants()
        }
    }

    fun loadParticipants() {
        viewModelScope.launch {
            _participants.value = participantUseCase.getParticipants()
        }
    }
}