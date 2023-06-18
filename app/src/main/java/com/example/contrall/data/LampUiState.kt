package com.example.contrall.data

import androidx.compose.ui.graphics.Color


data class LampUiState(
    val intensityValue: Float = 100f,
    val switchState: Boolean = false,
    val sliderValue: Float = 0f,
    var selectedColor: Color = Color.White,
    val selectedX: Float = 0f,
    val selectedY: Float = 0f,
    val showDialog: Boolean = false
)