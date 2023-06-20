package com.example.contrall.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contrall.data.RoutinesUiState
import com.example.contrall.data.network.RetrofitClient
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RoutinesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RoutinesUiState())
    val uiState: StateFlow<RoutinesUiState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    fun getRoutines() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.getRoutines()
            }.onSuccess { response ->
                _uiState.update {
                    it.copy(
                        routines = response.body(),
                        isLoading = false
                    )
                }
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

    fun executeRoutine(id: String){
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            val apiService = RetrofitClient.getApiService()
            apiService.execute(id!!,"executeRoutine") }
    }
}