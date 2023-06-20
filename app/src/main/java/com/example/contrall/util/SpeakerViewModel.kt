package com.example.contrall.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contrall.data.SongInfo
import com.example.contrall.data.SpeakerState
import com.example.contrall.data.SpeakerType
import com.example.contrall.data.SpeakerUiState
import com.example.contrall.data.network.RetrofitClient
import com.example.contrall.data.network.models.Device
import com.example.contrall.ui.components.PlaylistDialog
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SpeakerViewModel(device : Device = Device()) : ViewModel() {
    // Speaker UI state
    private val _uiState = MutableStateFlow(SpeakerUiState())
    val uiState: StateFlow<SpeakerUiState> = _uiState.asStateFlow()

    var showPlaylist : Boolean = false


    val currentPlaylist : List<SongInfo> = mutableStateListOf(
        SongInfo(title = "Style", artist = "Taylor Swift", album = "1989", duration = "3:30", progress = ""),
        SongInfo(title = "Mean", artist = "Taylor Swift", album = "Speak Now", duration = "4:15", progress = ""),
        SongInfo(title = "August", artist = "Taylor Swift", album = "Folklore", duration = "2:45", progress = "")
    )

    private var fetchJob : Job? = null
    init {
        _uiState.value = SpeakerUiState(
            id = device.id ?: "",
            name = device.name ?: "",
            type = SpeakerType(
                id = device.type?.id ?: "c89b94e8581855bc",
                name = device.type?.name ?: "speaker",
                powerUsage = device.type?.powerUsage ?: 20,
            ),
            state = SpeakerState(
                status = device.state?.status ?: "stopped",
                volume = device.state?.volume ?: 5,
                genre = device.state?.genre ?: "pop",
                song = device.state?.song,
            )
        )
    }



    fun setVolume(value : Float) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            val apiService = RetrofitClient.getApiService()
            apiService.executePI(_uiState.value.id!!,"setVolume", listOf(value.toInt()) ) }
            _uiState.update { currentState ->
                currentState.copy(
                state = currentState.state.copy(volume = value.toInt())
        ) }
    }



    fun changeGenre(newGenre : String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            val apiService = RetrofitClient.getApiService()
            apiService.executePS(_uiState.value.id!!,"setGenre", listOf(newGenre)) }
        _uiState.update { currentState ->
            currentState.copy(
                state = currentState.state.copy(genre = newGenre)
            )
        }
    }


    fun play() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            val apiService = RetrofitClient.getApiService()
            apiService.execute(_uiState.value.id!!,"play") }
        _uiState.update { currentState ->
            currentState.copy(
                state = currentState.state.copy(status = "playing")
            ) }
    }

    fun pause() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            val apiService = RetrofitClient.getApiService()
            apiService.execute(_uiState.value.id!!,"pause") }
        _uiState.update { currentState ->
            currentState.copy(
                state = currentState.state.copy(status = "paused")
            ) }
    }

    fun resume() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            val apiService = RetrofitClient.getApiService()
            apiService.execute(_uiState.value.id!!,"resume") }
        _uiState.update { currentState ->
            currentState.copy(
                state = currentState.state.copy(status = "playing")
            ) }
    }


    fun stop() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            val apiService = RetrofitClient.getApiService()
            apiService.execute(_uiState.value.id!!,"stop") }
        _uiState.update { currentState ->
            currentState.copy(
                state = currentState.state.copy(status = "stopped")
            ) }
    }

    fun nextSong() {
        if(_uiState.value.state.status == "paused" || _uiState.value.state.status == "stopped") {
            return
        }
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            val apiService = RetrofitClient.getApiService()
            apiService.execute(_uiState.value.id!!,"nextSong") }
//        _uiState.update { currentState ->
//            currentState.copy(
//                state = currentState.state.copy(song = )
//            )
//        }
    }
    fun previousSong() {
        if(_uiState.value.state.status == "paused" || _uiState.value.state.status == "stopped") {
            return
        }
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            val apiService = RetrofitClient.getApiService()
            apiService.execute(_uiState.value.id!!,"previousSong") }
//        _uiState.update { currentState ->
//            currentState.copy(
//                state = currentState.state.copy(song = )
//            )
//        }

    }

    fun getPlaylist(/*list : List<SongInfo>*/)  {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {

            val apiService = RetrofitClient.getApiService()
            apiService.execute(_uiState.value.id!!,"getPlaylist") }

        val res : List<SongInfo> = mutableStateListOf(
            SongInfo(title = "Style", artist = "Taylor Swift", album = "1989", duration = "3:30", progress = ""),
            SongInfo(title = "Mean", artist = "Taylor Swift", album = "Speak Now", duration = "4:15", progress = ""),
            SongInfo(title = "August", artist = "Taylor Swift", album = "Folklore", duration = "2:45", progress = "")
        )
    }
}

