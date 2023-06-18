package com.example.contrall.util

import androidx.lifecycle.ViewModel
import com.example.contrall.data.OvenUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OvenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OvenUIState())
    val uiState: StateFlow<OvenUIState> = _uiState.asStateFlow()

    init {
        resetOven()
    }

    fun resetOven() {
        _uiState.value = OvenUIState(temperatureValue = 100)
    }
    val switchState: Boolean
        get() = _uiState.value.switchState

    fun toggleSwitchState(newState: Boolean) {
        _uiState.update { currentState -> currentState.copy(
            switchState = newState
        ) }
    }
    fun setTemperatureValue(value : Int) {
        _uiState.update { currentState -> currentState.copy(
            temperatureValue = value
        ) }
    }
    fun increaseTemperatureValue() {
        if(_uiState.value.temperatureValue < 230) {
            _uiState.update { currentState -> currentState.copy(
                temperatureValue = currentState.temperatureValue + 1
            ) }
        }
    }
    fun decreaseTemperatureValue() {
        if(_uiState.value.temperatureValue > 80) {
            _uiState.update { currentState -> currentState.copy(
                temperatureValue = currentState.temperatureValue - 1
            ) }
        }
    }
    fun setHeatMode(value : String) {
        _uiState.update { currentState -> currentState.copy(
            selectedHeatModeValue = value
        ) }
    }
    fun setConvMode(value : String) {
        _uiState.update { currentState -> currentState.copy(
            selectedConvModeValue = value
        ) }
    }
    fun setGrillMode(value : String) {
        _uiState.update { currentState -> currentState.copy(
            selectedGrillModeValue = value
        ) }
    }

}
