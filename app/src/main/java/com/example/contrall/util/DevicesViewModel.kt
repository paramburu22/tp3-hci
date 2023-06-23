package com.example.contrall.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contrall.data.DevicesUiState
import com.example.contrall.data.network.RetrofitClient
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DevicesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DevicesUiState())
    val uiState: StateFlow<DevicesUiState> = _uiState.asStateFlow()

    private var fetchJob : Job? = null

    fun getDevices() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.getDevices()
            }.onSuccess { response ->
                _uiState.update {
                    it.copy(
                        devices = response.body(),
                        isLoading = false
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

    fun setFilter(mode: String) {
        _uiState.update { it.copy(
            filter = mode
        )}
    }
}