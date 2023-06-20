package com.example.contrall.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contrall.R
import com.example.contrall.data.DoorState
import com.example.contrall.data.DoorType
import com.example.contrall.data.DoorUIState
import com.example.contrall.data.network.RetrofitClient
import com.example.contrall.data.network.models.Device
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DoorViewModel(device : Device)  : ViewModel() {


    private val _uiState = MutableStateFlow(DoorUIState())
    val uiState: StateFlow<DoorUIState> = _uiState.asStateFlow()
    private var fetchJob: Job? = null

    init {
        _uiState.value = DoorUIState(
            id = device.id ?: "",
            name = device.name ?: "",
            type = DoorType(
                id = device.type?.id ?: "lsf78ly0eqrjbz91",
                name = device.type?.name ?: "door",
                powerUsage = device.type?.powerUsage ?: 350,
            ),
            state = DoorState(
                status = device.state?.status ?: "closed",
                lock = device.state?.lock ?: "unlocked"
            ),
            img = R.drawable.outline_sensor_door_24,
            isLoading = false
        )
    }

    fun switchState() {
        if(_uiState.value.state.lock.toBoolean())
            return
        if (_uiState.value.state.status.toBoolean()) {
            openDoor()
        } else {
            closeDoor()
        }
    }

    fun openDoor() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val apiService = RetrofitClient.getApiService()
            apiService.execute(_uiState.value.id!!, "open")
            _uiState.update { currentState ->
                currentState.copy(
                    state = currentState.state.copy(status = "opened"),
                    isLoading = false
                )
            }
        }
    }

    fun closeDoor() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val apiService = RetrofitClient.getApiService()
            apiService.execute(_uiState.value.id!!, "close")
            _uiState.update { currentState ->
                currentState.copy(
                    state = currentState.state.copy(status = "closed"),
                    isLoading = false
                )
            }
        }
    }


    fun switchLock(){
        if(_uiState.value.state.lock.toBoolean())
            unlockDoor()
        else lockDoor()
    }
    fun lockDoor() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val apiService = RetrofitClient.getApiService()
            apiService.execute(_uiState.value.id!!, "lock")
            _uiState.update { currentState ->
                currentState.copy(
                    state = currentState.state.copy(status = "closed", lock = "locked"),
                    isLoading = false
                )
            }
        }
    }

    fun unlockDoor() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val apiService = RetrofitClient.getApiService()
            apiService.execute(_uiState.value.id!!, "unlock")
            _uiState.update { currentState ->
                currentState.copy(
                    state = currentState.state.copy(lock = "unlocked"),
                    isLoading = false
                )
            }
        }
    }

}