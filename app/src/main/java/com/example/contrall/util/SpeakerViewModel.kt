package com.example.contrall.util

import androidx.lifecycle.ViewModel
import com.example.contrall.ui.SpeakerUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

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
}