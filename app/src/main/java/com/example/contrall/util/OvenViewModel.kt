package com.example.contrall.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contrall.R
import com.example.contrall.data.OvenState
import com.example.contrall.data.OvenType
import com.example.contrall.data.OvenUIState
import com.example.contrall.data.network.RetrofitClient
import com.example.contrall.data.network.models.Device
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OvenViewModel(device : Device)  : ViewModel(){


    private val _uiState = MutableStateFlow(OvenUIState())
    val uiState: StateFlow<OvenUIState> = _uiState.asStateFlow()
    private var fetchJob : Job? = null

    init {
        _uiState.value = OvenUIState(
            id= device.id ?: "",
            name= device.name ?: "",
            type = OvenType(
                id = device.type?.id ?: "im77xxyulpegfmv8",
                name = device.type?.name ?: "oven",
                powerUsage= device.type?.powerUsage ?: 1225,
            ),
            state = OvenState(
                status = device.state?.status ?: "",
                temperature = device.state?.temperature ?: "90",
                heat  = device.state?.heat ?: "conventional",
                grill  = device.state?.grill ?: "off",
                convection  = device.state?.status ?: "off"
            ) ,
            img = R.drawable.baseline_cookie_24  ,
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
    fun turnOn() {
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
    }

    fun turnOff() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val apiService = RetrofitClient.getApiService()
            apiService.execute(_uiState.value.id!!, "turnOff")
            _uiState.update {currentState ->
                currentState.copy(
                    state = currentState.state.copy(status = "off", isOn = false),
                    isLoading = false
                )
            }
        }
    }


    fun setTemperatureValue(value : Int) {
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
    }
    fun increaseTemperatureValue() {
        if(_uiState.value.state.temperature.toInt() < 230)
            setTemperatureValue(_uiState.value.state.temperature.toInt()+1)
    }

    fun decreaseTemperatureValue() {
        if(_uiState.value.state.temperature.toInt() > 80) {
            setTemperatureValue(_uiState.value.state.temperature.toInt()-1)
        }
    }
    fun setHeatMode(value : String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val apiService = RetrofitClient.getApiService()
            apiService.executePS(_uiState.value.id!!, "setHeat", listOf(value))
            _uiState.update {currentState ->
                currentState.copy(
                    state = currentState.state.copy(heat = value),
                    isLoading = false
                )
            }
        }
    }
    fun setConvMode(value : String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val apiService = RetrofitClient.getApiService()
            apiService.executePS(_uiState.value.id!!, "setConvection", listOf(value))
            _uiState.update {currentState ->
                currentState.copy(
                    state = currentState.state.copy(convection = value),
                    isLoading = false
                )
            }
        }
    }
    fun setGrillMode(value : String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val apiService = RetrofitClient.getApiService()
            apiService.executePS(_uiState.value.id!!, "setGrill", listOf(value))
            _uiState.update {currentState ->
                currentState.copy(
                    state = currentState.state.copy(grill = value),
                    isLoading = false
                )
            }
        }
    }

}
