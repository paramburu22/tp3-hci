package com.example.contrall.data

import android.icu.text.IDNA
import androidx.compose.runtime.mutableStateListOf
import com.example.contrall.R

data class SpeakerUiState(
    val name : String = R.string.speaker_name.toString(),
    var volume : Int = 0,
    val playing: Boolean = false,
    val stopped : Boolean = true,
    val genres : List<String> = mutableStateListOf("Pop", "Rock", "Latina", "Clasica"),
    val currentGenre : String = "Pop",
    val currentPlaylist : List<SongInfo> = mutableStateListOf(
        SongInfo(title = "Style", artist = "Taylor Swift", album = "1989", duration = "3:30"),
        SongInfo(title = "Mean", artist = "Taylor Swift", album = "Speak Now", duration = "4:15"),
        SongInfo(title = "August", artist = "Taylor Swift", album = "Folklore", duration = "2:45")
    ),
    var showPlaylist : Boolean = false
)