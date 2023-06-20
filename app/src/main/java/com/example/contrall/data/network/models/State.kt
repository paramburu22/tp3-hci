package com.example.contrall.data.network.models

import com.example.contrall.data.SongInfo
import com.google.gson.annotations.SerializedName

data class State (
    //lamp
    @SerializedName("status"             ) var status             : String? = null,
    @SerializedName("color"              ) var color              : String? = null,
    @SerializedName("brightness"         ) var brightness         : Int?    = null,
    //ac
    @SerializedName("temperature"        ) var temperature        : String?    = null,
    @SerializedName("mode"               ) var mode               : String? = null,
    @SerializedName("verticalSwing"      ) var verticalSwing      : String? = null,
    @SerializedName("horizontalSwing"    ) var horizontalSwing    : String? = null,
    @SerializedName("fanSpeed"           ) var fanSpeed           : String? = null,
    //speaker
    @SerializedName("volume" ) var volume : Int?    = null,
    @SerializedName("genre") var genre :String? = null,
    @SerializedName("song") var song :SongInfo? = null,
    //door
    @SerializedName("lock"               ) var lock               : String? = null,
    //oven
    @SerializedName("heat"       ) var heat       : String?    = null,
    @SerializedName("grill"       ) var grill       : String?    = null,
    @SerializedName("convection"       ) var convection       : String?    = null,
)
