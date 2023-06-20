package com.example.contrall.data.network.models

import com.google.gson.annotations.SerializedName

data class Action (
    @SerializedName("params"     ) var params     : List<String>      = listOf(),
    @SerializedName("actionName" ) var actionName : String?           = null,
    @SerializedName("device"     ) var device     : Device?           = Device(),
    @SerializedName("meta"       ) var meta       : String?           = null
)