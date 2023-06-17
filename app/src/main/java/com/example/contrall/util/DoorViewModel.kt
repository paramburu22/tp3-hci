package com.example.contrall.util

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.contrall.data.DoorUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DoorViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(DoorUiState());
    val uiState: StateFlow<DoorUiState> = _uiState.asStateFlow();
    init {
        resetDoor()
    }

    fun resetDoor() {
        _uiState.value = DoorUiState(openSwitchState = false, lockSwitchState = false);
    }

    fun lockOrUnlockDoor(value: Boolean) {
        _uiState.update { currentState -> currentState.copy(
            lockSwitchState = value
        ) }
    }

    fun openOrCloseDoor(value: Boolean) {
        _uiState.update { currentState -> currentState.copy(
            openSwitchState  = value
        ) }
    }
}