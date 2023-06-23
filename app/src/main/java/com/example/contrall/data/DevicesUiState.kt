package com.example.contrall.data

import android.net.Uri
import com.example.contrall.data.network.models.DevicesList
import com.google.gson.Gson

data class DevicesUiState (
    val isLoading : Boolean? = false,
    val devices : DevicesList? = null,
    val message : String? = null,
    val filter: String = "all",
    ) {
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}

