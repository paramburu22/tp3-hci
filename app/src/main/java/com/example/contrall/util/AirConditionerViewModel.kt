package com.example.contrall.util

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.contrall.data.AirConditionerUiState
import com.example.contrall.data.SpeakerUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class AirConditionerViewModel : ViewModel() {
    // AirCond UI state
    private val _uiState = MutableStateFlow(AirConditionerUiState())
    val uiState: StateFlow<AirConditionerUiState> = _uiState.asStateFlow()

    val switchState: Boolean
        get() = _uiState.value.switchState

    val temperature: Int
        get() = _uiState.value.temperature

    val fanSpeed: String
        get() = _uiState.value.selectedFanSpeed

    val horizontalSwing: String
        get() = _uiState.value.selectedHorizontalSwing

    val modes: List<String>
        get() = _uiState.value.modes

    val selectedMode: String
        get() = _uiState.value.selectedMode

    val fanSpeeds: List<String>
        get() = _uiState.value.fanSpeeds

    val selectedFanSpeed: String
        get() = _uiState.value.selectedFanSpeed

    val horizontalSwings: List<String>
        get() = _uiState.value.horizontalSwings

    val selectedHorizontalSwing: String
        get() = _uiState.value.selectedHorizontalSwing

    fun toggleSwitchState(newState: Boolean) {
        _uiState.update { currentState -> currentState.copy(
            switchState = newState
        ) }
    }

    fun setMode(mode: String) {
        _uiState.update { currentState -> currentState.copy(
            selectedMode = mode
        ) }
    }

    fun increaseTemperature() {
            _uiState.update { currentState -> currentState.copy(
                temperature = currentState.temperature + 1
            ) }

    }
    fun decreaseTemperature() {
            _uiState.update { currentState -> currentState.copy(
                temperature = currentState.temperature - 1
            ) }

    }

    fun setFanSpeed(speed: String) {
        _uiState.update { currentState -> currentState.copy(
            selectedFanSpeed = speed
        ) }
    }

    fun setHorizontalSwing(swing: String) {
        _uiState.update { currentState -> currentState.copy(
            selectedHorizontalSwing = swing
        ) }
    }
}