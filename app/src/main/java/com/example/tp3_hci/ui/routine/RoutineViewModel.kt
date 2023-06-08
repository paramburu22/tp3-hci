package com.example.tp3_hci.ui.routine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RoutineViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is routine Fragment"
    }
    val text: LiveData<String> = _text
}