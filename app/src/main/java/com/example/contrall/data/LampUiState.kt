package com.example.contrall.data

import androidx.compose.ui.graphics.Color


data class LampUiState(
    val id : String? = "",
    val name : String? = "",
    val type : LampType = LampType(),
    val state : LampState = LampState(),
)


data class LampType(
    val id : String = "go46xmbqeomjrsjr",
    val name : String = "lamp",
    val powerUsage : Int = 15
)

data class LampState(
    val status : String? = "off",
    val color : String? = "FFFFFF",
    val brightness : Int? = 100
)