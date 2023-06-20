package com.example.contrall.data.network.models

import com.google.gson.annotations.SerializedName

data class RoutinesList (

    @SerializedName("result" ) var routines : List<Routine> = listOf()

)