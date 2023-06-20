package com.example.contrall.util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.contrall.data.network.models.Device

class RecentsViewModel: ViewModel() {

    var recentDevices by mutableStateOf<MutableList<Device>>(mutableListOf())
        private  set

    fun addDevice(newDevice: Device) {
        if(recentDevices.contains(newDevice)) {
            recentDevices.remove(newDevice)
        }
        recentDevices.add(newDevice)
    }
}