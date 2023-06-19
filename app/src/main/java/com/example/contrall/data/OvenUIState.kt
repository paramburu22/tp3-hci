package com.example.contrall.data

import com.example.contrall.R

data class OvenUIState(
    val id: String? = "",
    val name: String? = "",
    val type: OvenType = OvenType(),
    val state: OvenState = OvenState(),
    val img: Int = R.drawable.baseline_cookie_24,
    val isLoading : Boolean = false
    )

data class OvenType(
    val id: String = "im77xxyulpegfmv8",
    val name: String = "oven",
    val powerUsage: Int = 1225,
)

data class OvenState(
    val status: String = "off",
    val temperature: String = "90",
    val heat : String = "conventional",
    val grill : String = "off",
    val convection : String = "off"
)