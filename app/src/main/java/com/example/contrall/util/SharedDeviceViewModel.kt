package com.example.contrall.util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contrall.data.network.RetrofitClient
import com.example.contrall.data.network.models.Device
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SharedDeviceViewModel: ViewModel() {
    private var message by mutableStateOf("")
    var device by mutableStateOf<Device?>(null)
        private  set

    fun addDevice(newDevice: Device) {
        device = newDevice;
    }

    private var fetchJob : Job? = null

    fun getDevice(id: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.getDevice(id)
            }.onSuccess { response ->
                device = response.body()?.result
            }.onFailure { e->
                message = e.message.toString()
            }
        }
    }
}