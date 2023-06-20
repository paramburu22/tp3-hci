package com.example.contrall.util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.contrall.data.network.models.Device

class SharedDeviceModel: ViewModel() {

    var device by mutableStateOf<Device?>(null)
        private  set

    fun addDevice(newDevice: Device) {
        device = newDevice;
    }
}