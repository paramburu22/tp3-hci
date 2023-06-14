package com.example.contrall.util

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LampViewModel : ViewModel() {

    private val _switchState = mutableStateOf(false)

    val switchState: Boolean
        get() = _switchState.value

    private val _intensity = mutableStateOf(0)
    val intensity: Int
        get() = _intensity.value



    fun toggleSwitch(set: Boolean) {
        _switchState.value = set

        // Update the lamp state or perform any other necessary operations
        // ...
    }

    fun setIntensity(intensity: Int) {
        _intensity.value = intensity

        // Update the lamp state or perform any other necessary operations
        // ...
    }


}
