package com.example.contrall.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import com.example.contrall.R

data class AirConditionerUiState (
    /*val modes: List<String> = mutableStateListOf("Ventilación", "Frio", "Calor"),
    val selectedMode: String = "Ventilación",

    val fanSpeeds: List<String> = mutableStateListOf("Automático", "25%", "50%", "75%", "100%"),
    val selectedFanSpeed: String = "Medium",

    val horizontalSwings: List<String> = mutableStateListOf("-90", "-45", "0", "45", "90"),
    val selectedHorizontalSwing: String = "0",

    var switchState: Boolean = false,
    val temperature: Int = 24,*/

    val id: String? = "",
    val name: String? = "",
    val type: AirConditionerType = AirConditionerType(),
    val state: AirConditionerState = AirConditionerState(),
    val img: Int = R.drawable.ic_baseline_ac_unit_24,
    val isLoading : Boolean = false
)
data class AirConditionerType(
    val id: String = "li6cbv5sdlatti0j",
    val name: String = "ac",
    val powerUsage: Int = 1600,
)

data class AirConditionerState(
    val status: String = "off", //switch
    val mode: String = "fan",
    val modeClass : DropdownClass = DropdownClass("fan", "ventilacion"),
    val fanSpeed: String = "medium",
    val horizontalSwing: String = "0",
    val verticalSwing: String = "auto",
    val temperature: String = "24",
    val isOn : Boolean = false
)