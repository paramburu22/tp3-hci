package com.example.contrall.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.unit.dp
import com.example.contrall.ui.components.DeviceComponent
import androidx.compose.ui.tooling.preview.Preview
import com.example.contrall.data.network.models.Device
import com.example.contrall.data.network.models.DevicesList
import com.example.contrall.ui.components.TopAppBar
import com.example.contrall.util.DeviceViewModel

@Composable
@Preview
fun DeviceScreen(imageResId: Int = 2, deviceViewModel: DeviceViewModel) {
    val deviceUiState by deviceViewModel.uiState.collectAsState()

    val painter = painterResource(imageResId)

    deviceViewModel.getDevices()

    Scaffold(
        topBar = {
            TopAppBar(title = "Dispositivos", showIcon = false)
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(5.dp),
                    modifier = Modifier.padding(start = 4.dp, end = 4.dp, bottom = 4.dp, top = 25.dp)
                ) {
                    val myDevices : DevicesList? = deviceUiState.devices
                    if (myDevices != null) {
                        items(myDevices.devices.size) { index ->
                            val device : Device = myDevices.devices[index]
                            DeviceComponent(device)
                        }
                    }
                }
            }
        }
    )
}