package com.example.contrall.data

import android.net.Uri
import com.example.contrall.data.network.models.DevicesList
import com.google.gson.Gson
import retrofit2.Response

data class DeviceUiState (
    val isLoading : Boolean? = false,
    val devices : DevicesList? = null,
    val message : String? = null,
) {
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}