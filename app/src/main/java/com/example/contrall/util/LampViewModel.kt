package com.example.contrall.util

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contrall.data.AirConditionerUiState
import com.example.contrall.data.LampState
import com.example.contrall.data.LampType
import com.example.contrall.data.LampUiState
import com.example.contrall.data.SpeakerState
import com.example.contrall.data.SpeakerType
import com.example.contrall.data.SpeakerUiState
import com.example.contrall.data.network.RetrofitClient
import com.example.contrall.data.network.models.Device
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LampViewModel(device : Device = Device()) : ViewModel() {
    private val _uiState = MutableStateFlow(LampUiState())
    val uiState: StateFlow<LampUiState> = _uiState.asStateFlow()


    private var fetchJob : Job? = null

    init {
        _uiState.value = LampUiState(
            id = device.id ?: "",
            name = device.name ?: "",
            type = LampType(
                id = device.type?.id ?: "go46xmbqeomjrsjr",
                name = device.type?.name ?: "lamp",
                powerUsage = device.type?.powerUsage ?: 15,
            ),
            state = LampState(
                status = device.state?.status ?: "stopped",
                color = device.state?.color ?: "FFFFFF",
                brightness = device.state?.brightness ?: 100,
            )
        )
    }



    fun toggleSwitchState() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            val apiService = RetrofitClient.getApiService()
            if (!_uiState.value.state.isOn) {
                apiService.execute(_uiState.value.id!!, "turnOn")
            } else {
                apiService.execute(_uiState.value.id!!, "turnOff")
            }
        }
        if (!_uiState.value.state.isOn) {
            _uiState.update { currentState ->
                currentState.copy(state = currentState.state.copy(status = "on", isOn = true))
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(state = currentState.state.copy(status = "off", isOn = false))
            }
        }

    }

    fun setIntensityValue(value: Float) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            val apiService = RetrofitClient.getApiService()
            apiService.executePI(_uiState.value.id!!,"setBrightness", listOf(value.toInt()) ) }
        _uiState.update { currentState ->
            currentState.copy(
                state = currentState.state.copy(brightness = value.toInt())
            ) }
    }

    fun setColor(color: Color) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            val apiService = RetrofitClient.getApiService()
            apiService.executePS(_uiState.value.id!!,"setColor", listOf(colorToHex(color)) ) }
        _uiState.update { currentState ->
            currentState.copy(
                state = currentState.state.copy(color = colorToHex(color))
            )
        }
    }


    fun intToColor(colorValue: Int): Color {
        val alpha = (colorValue shr 24 and 0xFF) / 255f
        val red = (colorValue shr 16 and 0xFF) / 255f
        val green = (colorValue shr 8 and 0xFF) / 255f
        val blue = (colorValue and 0xFF) / 255f
        return Color(red, green, blue, alpha)
    }
    fun colorToHex(color: Color): String {
        val red = (color.red * 255).toInt()
        val green = (color.green * 255).toInt()
        val blue = (color.blue * 255).toInt()
        return String.format("%02x%02x%02x", red, green, blue)
    }

    fun changeDialog(value: Boolean){
        _uiState.update { currentState ->
            currentState.copy(
                state = currentState.state.copy(showDialog = value)
            )
        }
        //showDialog = value
    }

}
