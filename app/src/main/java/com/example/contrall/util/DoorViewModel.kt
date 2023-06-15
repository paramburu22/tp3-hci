package com.example.contrall.util

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class DoorViewModel: ViewModel() {
    private val openSwitchState = mutableStateOf(false);
    private val lockSwitchState = mutableStateOf(false);
}