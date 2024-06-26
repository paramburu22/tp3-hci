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

class DoorViewModel(device : Device, devicesViewModel: DevicesViewModel)  : ViewModel() {


    private val _uiState = MutableStateFlow(DoorUIState())
    val uiState: StateFlow<DoorUIState> = _uiState.asStateFlow()
    private var fetchJob: Job? = null

    var deviceModel =  devicesViewModel

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
                lock = device.state?.lock ?: "unlocked",
                isLocked =  if(device.state?.lock != null) {
                    device.state?.lock == "locked"
                } else {
                    false
                },
                isOpen =  if(device.state?.status != null) {
                    device.state?.status == "opened"
                } else {
                    false
                },
            ),
            img = R.drawable.outline_sensor_door_24,
            isLoading = false
        )
    }

    fun switchState() {

        if(_uiState.value.state.isLocked)
            return
        if (_uiState.value.state.isOpen) {
            closeDoor()
        } else {
            openDoor()
        }
        skipNoti()
    }

    fun openDoor() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val apiService = RetrofitClient.getApiService()
            apiService.execute(_uiState.value.id!!, "open")
            _uiState.update { currentState ->
                currentState.copy(
                    state = currentState.state.copy(status = "opened", isOpen = true),
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
                    state = currentState.state.copy(status = "closed", isOpen = false),
                    isLoading = false
                )
            }
        }
    }


    fun switchLock(){
        if(_uiState.value.state.isLocked)
            unlockDoor()
        else lockDoor()
        skipNoti()
    }
    fun lockDoor() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val apiService = RetrofitClient.getApiService()
            apiService.execute(_uiState.value.id!!, "lock")
            if(_uiState.value.state.status == "opened") {
                closeDoor()
            }
            _uiState.update { currentState ->
                currentState.copy(
                    state = currentState.state.copy(lock = "locked", isLocked = true),
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
                    state = currentState.state.copy(lock = "unlocked", isLocked = false),
                    isLoading = false
                )
            }
        }
    }

    fun skipNoti(){
        _uiState.value.id?.let { deviceModel.notifGenerate(it) }
    }

}