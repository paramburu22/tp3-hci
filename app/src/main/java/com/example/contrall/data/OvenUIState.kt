package com.example.contrall.data

import androidx.compose.runtime.mutableStateListOf

data class OvenUIState(
    val temperatureValue: Int = 100,
    val switchState: Boolean = false,
    val heatModes : List<String> = mutableStateListOf("Convencional", "Arriba", "Abajo"),
    val convModes : List<String> = mutableStateListOf("Apagado", "Economico", "Completo"),
    val grillModes : List<String> = mutableStateListOf("Apagado", "Economico", "Convencional"),
    val selectedHeatModeValue : String ="Convencional",
    val selectedConvModeValue : String = "Apagado",
    val selectedGrillModeValue : String = "Apagado",
    )