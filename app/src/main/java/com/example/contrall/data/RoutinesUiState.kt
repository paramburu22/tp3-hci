package com.example.contrall.data

import android.net.Uri
import com.example.contrall.data.network.models.RoutinesList
import com.google.gson.Gson

data class RoutinesUiState (
    val isLoading : Boolean? = false,
    val routines : RoutinesList? = null,
    val message : String? = null,
    )
    {
        override fun toString(): String = Uri.encode(Gson().toJson(this))
}