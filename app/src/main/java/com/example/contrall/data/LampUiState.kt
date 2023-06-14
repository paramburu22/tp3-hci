package com.example.contrall.data

data class LampUiState(
    val lightStatus: Boolean = false,
    val color: String = "",
    val intensity: Int = 0,
    val switchState: Boolean = false,
) {
}