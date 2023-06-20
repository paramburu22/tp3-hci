package com.example.contrall.data.network.models

import com.google.gson.annotations.SerializedName

data class RoutineResult (

    @SerializedName("result" ) var result : Routine? = Routine()

)
data class Routine(
    @SerializedName("id")  var id    : String? = null,
    @SerializedName("name")  var name  : String? = null,
    @SerializedName("actions" ) var actions : List<Action>   = listOf()

)