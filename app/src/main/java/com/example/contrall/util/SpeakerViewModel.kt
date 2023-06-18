package com.example.contrall.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.example.contrall.data.SpeakerUiState
import com.example.contrall.ui.components.PlaylistDialog
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
        _uiState.value = SpeakerUiState(volume = 0, playing = false)
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

    fun changeGenre(newGenre : String) {
        _uiState.update { currentState -> currentState.copy(
            currentGenre = newGenre
        ) }
    }

    fun play() {
        if(_uiState.value.stopped) {
            // arranco el parlante
            _uiState.update { currentState ->
                currentState.copy(
                    stopped = false,
                    playing = true,
                )
            }
        }
        if (_uiState.value.playing) {
            // pause
            _uiState.update { currentState -> currentState.copy(playing = false) }
        } else {
            // resume
            _uiState.update { currentState -> currentState.copy(playing = true) }
        }
    }

    fun stop() {
        _uiState.update { currentState ->
            currentState.copy(
                stopped = true,
                playing = false,
            )
        }
    }

    fun nextSong() {

    }
    fun previousSong() {

    }

}