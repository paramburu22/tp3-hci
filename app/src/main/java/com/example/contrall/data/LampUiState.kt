package com.example.contrall.data

import androidx.compose.ui.graphics.Color
import com.example.contrall.R


data class LampUiState(
    val id : String? = "",
    val name : String? = "",
    val type : LampType = LampType(),
    val state : LampState = LampState(),
    val img: Int = R.drawable.ic_baseline_lightbulb_24,
    val isLoading : Boolean = false
)


data class LampType(
    val id : String = "go46xmbqeomjrsjr",
    val name : String = "lamp",
    val powerUsage : Int = 15
)

data class LampState(
    val status : String? = "off",
    val color : String? = "FFFFFF",
    val brightness : Int? = 100,
    val isOn : Boolean = false,
    val showDialog: Boolean = false,
)