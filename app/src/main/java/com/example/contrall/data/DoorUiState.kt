package com.example.contrall.data

import com.example.contrall.R

data class DoorUIState(
    val id: String? = "",
    val name: String? = "",
    val type: DoorType = DoorType(),
    val state: DoorState = DoorState(),
    val img: Int = R.drawable.outline_sensor_door_24,
    val isLoading : Boolean = false
)

data class DoorType(
    val id: String = "lsf78ly0eqrjbz91",
    val name: String = "door",
    val powerUsage: Int = 350,
)

data class DoorState(
    val status: String = "closed",
    val lock: String = "unlocked",
)