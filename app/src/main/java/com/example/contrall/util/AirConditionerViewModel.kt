package com.example.contrall.util

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contrall.R
import com.example.contrall.data.AirConditionerState
import com.example.contrall.data.AirConditionerType
import com.example.contrall.data.AirConditionerUiState
import com.example.contrall.data.OvenState
import com.example.contrall.data.OvenType
import com.example.contrall.data.OvenUIState
import com.example.contrall.data.SpeakerUiState
import com.example.contrall.data.network.RetrofitClient
import com.example.contrall.data.network.models.Device
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class AirConditionerViewModel( device : Device) : ViewModel() {
    // AirCond UI state
    private val _uiState = MutableStateFlow(AirConditionerUiState())
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
                temperature = device.state?.temperature ?: "24",
                mode = device.state?.mode ?: "ventilacion",
                fanSpeed = device.state?.fanSpeed ?: "medium",
                horizontalSwing = device.state?.horizontalSwing ?: "0",
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
    }

    fun setFanSpeed(speed: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val apiService = RetrofitClient.getApiService()
            apiService.executePS(_uiState.value.id!!, "setFanSpeed", listOf(speed))
            _uiState.update {currentState ->
                currentState.copy(
                    state = currentState.state.copy(verticalSwing = speed),
                    isLoading = false
                )
            }
        }
    }
    /*

    val switchState: Boolean
        get() = _uiState.value.state.status

    val temperature: Int
        get() = _uiState.value.temperature

    val fanSpeed: String
        get() = _uiState.value.selectedFanSpeed

    val horizontalSwing: String
        get() = _uiState.value.selectedHorizontalSwing

    val modes: List<String>
        get() = _uiState.value.modes

    val selectedMode: String
        get() = _uiState.value.selectedMode

    val fanSpeeds: List<String>
        get() = _uiState.value.fanSpeeds

    val selectedFanSpeed: String
        get() = _uiState.value.selectedFanSpeed

    val horizontalSwings: List<String>
        get() = _uiState.value.horizontalSwings

    val selectedHorizontalSwing: String
        get() = _uiState.value.selectedHorizontalSwing

    fun toggleSwitchState(newState: Boolean) {
        _uiState.update { currentState -> currentState.copy(
            switchState = newState
        ) }
    }





    fun setHorizontalSwing(swing: String) {
        _uiState.update { currentState -> currentState.copy(
            selectedHorizontalSwing = swing
        ) }
    }
    */
}