package com.example.contrall.util

import androidx.lifecycle.ViewModel
import com.example.contrall.data.SpeakerUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SpeakerViewModel : ViewModel() {
    // Speaker UI state
    private val _uiState = MutableStateFlow(SpeakerUiState())
    val uiState: StateFlow<SpeakerUiState> = _uiState.asStateFlow()

    init {
        resetSpeaker()
    }

    fun resetSpeaker() {
        _uiState.value = SpeakerUiState(volume = 0)
    }

    fun decreaseVolume() {
        if(_uiState.value.volume > 0) {
            _uiState.update { currentState -> currentState.copy(
                volume = currentState.volume - 1
            ) }
        }
    }

    fun setVolume(value : Float) {
        _uiState.update { currentState -> currentState.copy(
            volume = value.toInt()
        ) }
    }

    fun increaseVolume() {
        if(_uiState.value.volume < 10) {
            _uiState.update { currentState -> currentState.copy(
                volume = currentState.volume + 1
            ) }
        }
    }
}