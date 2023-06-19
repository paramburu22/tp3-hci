package com.example.contrall.data

import com.example.contrall.data.network.models.DevicesList
import retrofit2.Response

data class DeviceUiState (
    val isLoading : Boolean? = false,
    val devices : DevicesList? = null,
    val message : String? = null,
)