package com.example.contrall.data

data class LampUiState(
    /*val lightStatus: Boolean = false,
    val color: String = "",
    val intensity: Int = 0,
    val switchState: Boolean,
    val sliderValue: Float*/
    val intensityValue: Float,
    val switchState: Boolean,
    val onSwitchStateChanged: (Boolean) -> Unit = {},
    val func: ()->Unit = {},
    val sliderValue: Float = 0f,
    val onSliderValueChanged: (Float) -> Unit = {},

) {
}