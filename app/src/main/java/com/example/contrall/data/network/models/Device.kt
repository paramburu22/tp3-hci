package com.example.contrall.data.network.models

import com.google.gson.annotations.SerializedName


data class DeviceResult (

    @SerializedName("result" ) var result : Device? = Device()

)
data class Device(
    @SerializedName("id")  var id    : String? = null,
    @SerializedName("name")  var name  : String? = null,
    @SerializedName("type")  var type  : Type?   = Type(),
    @SerializedName("state") var state : State? = State(),
    @SerializedName("room"  ) var room  : Room?   = Room()
)