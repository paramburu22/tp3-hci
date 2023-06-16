package com.example.contrall.util

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LampViewModel : ViewModel() {
    private val _switchState = mutableStateOf(false)
    val switchState: Boolean
        get() = _switchState.value

    private val _intensityValue = mutableStateOf(0f)
    val intensityValue: Float
        get() = _intensityValue.value

    fun toggleSwitchState(newState: Boolean) {
        _switchState.value = newState
        // Perform any necessary operations based on the new switch state
    }

    fun setIntensityValue(value: Float) {
        _intensityValue.value = value
        // Perform any necessary operations based on the new intensity value
    }

}
