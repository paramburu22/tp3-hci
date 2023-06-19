package com.example.contrall.data.network.models


import com.google.gson.annotations.SerializedName


data class DevicesList (
    @SerializedName("result") var devices : List<Device> = listOf()

)
