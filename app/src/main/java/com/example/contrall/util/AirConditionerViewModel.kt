package com.example.contrall.util

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contrall.MainActivity
import com.example.contrall.R
import com.example.contrall.data.AirConditionerState
import com.example.contrall.data.AirConditionerType
import com.example.contrall.data.AirConditionerUiState
import com.example.contrall.data.network.RetrofitClient
import com.example.contrall.data.network.models.Device
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class AirConditionerViewModel( device : Device, devicesViewModel: DevicesViewModel) : ViewModel() {
    // AirCond UI state
    private val _uiState = MutableStateFlow(AirConditionerUiState())
    var deviceModel =  devicesViewModel
    val uiState: StateFlow<AirConditionerUiState> = _uiState.asStateFlow()

    private var fetchJob : Job? = null

    init {
        _uiState.value = AirConditionerUiState(
            id= device.id ?: "",
            name= device.name ?: "",
            type = AirConditionerType(
                id = device.type?.id ?: "li6cbv5sdlatti0j",
                name = device.type?.name ?: "ac",
                powerUsage= device.type?.powerUsage ?: 1225,
            ),
            state = AirConditionerState(
                status = device.state?.status ?: "off",
                isOn = if(device.state?.status != null) {
                    device.state?.status == "on"
                } else {
                       false
                },
                temperature = device.state?.temperature ?: "24",
                mode = device.state?.mode ?: "ventilacion",
                fanSpeed = device.state?.fanSpeed ?: "medium",
                horizontalSwing = device.state?.horizontalSwing ?: "0",
                verticalSwing = device.state?.verticalSwing ?: "0",
            ) ,
            img = R.drawable.ic_baseline_ac_unit_24  ,
            isLoading = false
        )
    }
    fun switchState(){
        if(_uiState.value.state.isOn){
            turnOff()
        }
        else{
            turnOn()
        }
    }
    fun turnOn(){
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val apiService = RetrofitClient.getApiService()
            apiService.execute(_uiState.value.id!!, "turnOn")
            _uiState.update {currentState ->
                currentState.copy(
                    state = currentState.state.copy(status = "on", isOn = true),
                    isLoading = false
                )
            }
        }
        skipNoti()
    }
    fun turnOff(){
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val apiService = RetrofitClient.getApiService()
            apiService.execute(_uiState.value.id!!, "turnOff")
            _uiState.update {currentState ->
                currentState.copy(
                    state = currentState.state.copy(status = "off" , isOn = false),
                    isLoading = false
                )
            }
        }
        skipNoti()
    }

    fun setTemperature(value: Int){
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val apiService = RetrofitClient.getApiService()
            apiService.executePI(_uiState.value.id!!, "setTemperature", listOf(value))
            _uiState.update {currentState ->
                currentState.copy(
                    state = currentState.state.copy(temperature = value.toString()),
                    isLoading = false
                )
            }
        }
        skipNoti()
    }
    @SuppressLint("SuspiciousIndentation")
    fun increaseTemperature() {
        if(_uiState.value.state.temperature.toInt() < 38)
        setTemperature(_uiState.value.state.temperature.toInt()+1)


    }
    fun decreaseTemperature() {
        if(_uiState.value.state.temperature.toInt() > 18)
            setTemperature(_uiState.value.state.temperature.toInt()-1)


    }
    fun setMode(mode: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val apiService = RetrofitClient.getApiService()
            apiService.executePS(_uiState.value.id!!, "setMode", listOf(mode))
            _uiState.update {currentState ->
                currentState.copy(
                    state = currentState.state.copy(mode = mode),
                    isLoading = false
                )
            }
        }
        skipNoti()
    }

    fun setVerticalSwing(mode: String){
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val apiService = RetrofitClient.getApiService()
            apiService.executePS(_uiState.value.id!!, "setVerticalSwing", listOf(mode))
            _uiState.update {currentState ->
                currentState.copy(
                    state = currentState.state.copy(verticalSwing = mode),
                    isLoading = false
                )
            }
        }
        skipNoti()
    }

    fun setHorizontalSwing(mode: String){
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val apiService = RetrofitClient.getApiService()
            apiService.executePS(_uiState.value.id!!, "setHorizontalSwing", listOf(mode))
            _uiState.update {currentState ->
                currentState.copy(
                    state = currentState.state.copy(horizontalSwing = mode),
                    isLoading = false
                )
            }
        }
        skipNoti()
    }

    fun setFanSpeed(speed: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val apiService = RetrofitClient.getApiService()
            apiService.executePS(_uiState.value.id!!, "setFanSpeed", listOf(speed))
            _uiState.update {currentState ->
                currentState.copy(
                    state = currentState.state.copy(fanSpeed = speed),
                    isLoading = false
                )
            }
        }
        skipNoti()
    }


    fun skipNoti(){
        _uiState.value.id?.let { deviceModel.notifGenerate(it) }
    }

}