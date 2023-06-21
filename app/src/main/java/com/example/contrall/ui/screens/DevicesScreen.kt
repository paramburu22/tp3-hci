package com.example.contrall.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.contrall.ui.components.DeviceComponent
import com.example.contrall.R
import com.example.contrall.data.network.models.Device
import com.example.contrall.data.network.models.DevicesList
import com.example.contrall.ui.components.TopAppBar
import com.example.contrall.util.DevicesViewModel
import com.example.contrall.util.RecentsViewModel
import com.example.contrall.util.SharedDeviceModel

@Composable

fun DevicesScreen(devicesViewModel: DevicesViewModel, navController: NavController, sharedDeviceModel: SharedDeviceModel, recentsModel: RecentsViewModel) {
    val painter = painterResource(R.drawable.background)
    val deviceUiState by devicesViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        devicesViewModel.getDevices();
    }


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
                if(deviceUiState.devices?.devices?.size!! <= 0) {
                    Text(
                        text = "No hay dispositivos utilizados recientemente",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 20.dp),
                    )
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(5.dp),
                        modifier = Modifier.padding(
                            start = 4.dp,
                            end = 4.dp,
                            bottom = 4.dp,
                            top = 25.dp
                        ).heightIn(min = 200.dp)
                    ) {
                        val myDevices: DevicesList? = deviceUiState.devices
                        if (myDevices != null) {
                            items(myDevices.devices.size) { index ->
                                val device: Device = myDevices.devices[index]
                                DeviceComponent(
                                    device,
                                    navController,
                                    sharedDeviceModel,
                                    recentsModel
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}