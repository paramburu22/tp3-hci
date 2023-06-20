package com.example.contrall.data

import android.icu.text.IDNA
import androidx.compose.runtime.mutableStateListOf
import com.example.contrall.R


data class SpeakerUiState(
    val id : String? = "",
    val name : String? = "",
    val type : SpeakerType = SpeakerType(),
    val state : SpeakerState = SpeakerState(),

)

data class SpeakerType(
    val id : String = "c89b94e8581855bc",
    val name : String = "speaker",
    val powerUsage : Int = 20
)

data class SpeakerState(
    val status : String? = "stopped",
    var volume : Int? = 5,
    val genre : String? = "pop",
    val song : SongInfo? = null,
)

