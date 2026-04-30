package com.eloueduniv.monsit.presentation.call.add

import androidx.lifecycle.ViewModel
import com.eloueduniv.monsit.data.model.Call
import com.eloueduniv.monsit.domain.usecase.AddCallUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddCallViewModel @Inject constructor(private val addCallUseCase: AddCallUseCase): ViewModel() {

    val _uiState = MutableStateFlow(AddCallUiState())
    val uiState: StateFlow<AddCallUiState> = _uiState.asStateFlow()


    fun onAction(action: AddCallUiAction) {
        when (action) {
            is AddCallUiAction.onContactNameChange -> {
                _uiState.value = _uiState.value.copy(contactName = action.contactName)
            }
            is AddCallUiAction.onCallDateChange -> {
                _uiState.value = _uiState.value.copy(callDate = action.callDate)
            }
            is AddCallUiAction.onCallTimeChange -> {
                _uiState.value = _uiState.value.copy(callTime = action.callTime)
            }
            is AddCallUiAction.onDurationChange -> {
                _uiState.value = _uiState.value.copy(duration = action.duration)
            }
            is AddCallUiAction.onAudioUrlChange -> {
                _uiState.value = _uiState.value.copy(audioUrl = action.audioUrl)
            }
            is AddCallUiAction.onNoteChange -> {
                _uiState.value = _uiState.value.copy(note = action.note)
            }
            is AddCallUiAction.onAddCall -> {
                val call = Call(
                    id = "",
                    contactName = _uiState.value.contactName ?: "",
                    startTime = _uiState.value.callDate.time + _uiState.value.callTime.time,
                    duration = _uiState.value.duration,
                    audioUrl = _uiState.value.audioUrl ?: "",
                    transcript = "",
                    summary = "",
                    contactId = 0,
                    note = _uiState.value.note
                )
                addCallUseCase(call)
            }
        }
    }
    }