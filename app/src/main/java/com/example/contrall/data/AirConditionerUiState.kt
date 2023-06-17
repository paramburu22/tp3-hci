package com.example.contrall.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

data class AirConditionerUiState (
    val modes: List<String> = mutableStateListOf("Ventilación", "Frio", "Calor"),
    val selectedMode: String = "Ventilación",

    val fanSpeeds: List<String> = mutableStateListOf("Automático", "25%", "50%", "75%", "100%"),
    val selectedFanSpeed: String = "Medium",

    val horizontalSwings: List<String> = mutableStateListOf("-90", "-45", "0", "45", "90"),
    val selectedHorizontalSwing: String = "0",

    var switchState: Boolean = false,
    //val onSwitchStateChanged: (Boolean) -> Unit = {},

    val temperature: Int = 24,
    ){}