package com.example.contrall.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contrall.data.DeviceUiState
import com.example.contrall.data.network.RetrofitClient
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DeviceViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DeviceUiState())
    val uiState: StateFlow<DeviceUiState> = _uiState.asStateFlow()

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
//                response.body()?.devices?.let { devices ->
//                    currentDevices = devices.toMutableList()
//                }
            }.onFailure { e->
                _uiState.update { it.copy(
                    message = e.message,
                    isLoading = false
                ) }
            }
        }
    }
}