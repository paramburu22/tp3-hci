package com.example.contrall.data

import com.example.contrall.R

data class SpeakerUiState(
    val name : String = R.string.speaker_name.toString(),
    var volume : Int = 0,
    val playing: Boolean = false,
)