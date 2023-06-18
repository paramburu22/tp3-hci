package com.example.contrall.util

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.contrall.data.AirConditionerUiState
import com.example.contrall.data.LampUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LampViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LampUiState())
    val uiState: StateFlow<LampUiState> = _uiState.asStateFlow()

    val switchState: Boolean
        get() = _uiState.value.switchState

    val intensityValue: Float
        get() = _uiState.value.intensityValue

    val selectedColor: Color
        get() = _uiState.value.selectedColor

    val showDialog: Boolean
        get() = _uiState.value.showDialog

    fun toggleSwitchState(newState: Boolean) {
        _uiState.update { currentState -> currentState.copy(
            switchState = newState
        ) }
    }

    fun setIntensityValue(value: Float) {
        _uiState.update { currentState -> currentState.copy(
            intensityValue = value
        ) }
    }

    fun setColor(color: Color) {
        _uiState.update { currentState -> currentState.copy(
            selectedColor = color
        ) }
    }

    fun setSelectedPosition(x: Float, y: Float) {
        _uiState.update { currentState -> currentState.copy(
            selectedX = x,
            selectedY = y
        ) }
    }

    fun intToColor(colorValue: Int): Color {
        val alpha = (colorValue shr 24 and 0xFF) / 255f
        val red = (colorValue shr 16 and 0xFF) / 255f
        val green = (colorValue shr 8 and 0xFF) / 255f
        val blue = (colorValue and 0xFF) / 255f
        return Color(red, green, blue, alpha)
    }

    fun changeDialog(value: Boolean){
        _uiState.update { currentState -> currentState.copy(
            showDialog = value
        ) }
    }

}
