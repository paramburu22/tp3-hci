package com.example.contrall.util

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contrall.data.SongInfo
import com.example.contrall.data.SpeakerState
import com.example.contrall.data.SpeakerType
import com.example.contrall.data.SpeakerUiState
import com.example.contrall.data.network.RetrofitClient
import com.example.contrall.data.network.models.Device
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SpeakerViewModel(device : Device = Device()) : ViewModel() {
    // Speaker UI state
    private val _uiState = MutableStateFlow(SpeakerUiState())
    val uiState: StateFlow<SpeakerUiState> = _uiState.asStateFlow()

    private val _playlistState = MutableStateFlow<List<SongInfo>?>(null)
    val playlistState: StateFlow<List<SongInfo>?> = _playlistState

    var showPlaylist : Boolean = false



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

    fun updateState() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.getState(_uiState.value.id!!)
            }.onSuccess { response ->
                _uiState.update {
                    it.copy(
                        state = it.state.copy(
                            song = response.body()?.result?.song,
                            volume = response.body()?.result?.volume,
                            genre = response.body()?.result?.genre,
                            status = response.body()?.result?.status,
                        )
                    )
                }
            }.onFailure { e->
                _uiState.update { it.copy(
                    message = e.message,
                    isLoading = false
                ) }
            }
        }

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

    }
    fun previousSong() {
        if(_uiState.value.state.status == "paused" || _uiState.value.state.status == "stopped") {
            return
        }
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            val apiService = RetrofitClient.getApiService()
            apiService.execute(_uiState.value.id!!,"previousSong") }
    }

    fun getPlaylist() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.getPlaylist(_uiState.value.id!!)
            }.onSuccess { response ->
                _uiState.update {
                    it.copy(
                        playlist = response.body()?.result,
                        isLoading = false
                    )
                }
                _playlistState.update {
                    response.body()?.result
                }// Actualizar el valor de playlistState
            }.onFailure { e ->
                _uiState.update {
                    it.copy(
                        message = e.message,
                        isLoading = false
                    )
                }
            }
        }
    }
}

