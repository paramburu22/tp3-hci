package com.example.contrall.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import com.example.contrall.R
import com.example.contrall.data.network.models.Device
import com.example.contrall.ui.components.BottomBar
import com.example.contrall.ui.components.DeviceComponent
import com.example.contrall.ui.components.OurDropdownMenu
import com.example.contrall.ui.components.TopAppBar
import com.example.contrall.util.DevicesViewModel
import com.example.contrall.util.RecentsViewModel
import com.example.contrall.util.SharedDeviceViewModel

@Composable

fun DevicesScreen(devicesViewModel: DevicesViewModel, navController: NavController, sharedDeviceViewModel: SharedDeviceViewModel, recentsModel: RecentsViewModel) {
    val painter = painterResource(R.drawable.background)
    val devicesUiState by devicesViewModel.uiState.collectAsState()

    val lifecycleObserver = LifecycleEventObserver { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                devicesViewModel.getDevices();
            }
            else -> Unit
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val filterMap : Map<String, String> = mapOf(
        "all" to stringResource(R.string.default_filter),
        "ac" to stringResource(R.string.ac_name),
        "oven" to stringResource(R.string.oven_name),
        "lamp" to stringResource(R.string.lamp_name),
        "speaker" to stringResource(R.string.speaker_name),
        "door" to stringResource(R.string.door_name)
    )
    DisposableEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(title = stringResource(R.string.devices_title), showIcon = false)
        },
        content = {
            BoxWithConstraints() {

                if(maxWidth < 520.dp){
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
                        if(devicesUiState.isLoading == true) {
                            CircularProgressIndicator()
                        } else {
                            if(devicesUiState.devices?.devices?.isEmpty() == true) {
                                Text(
                                    text = stringResource(R.string.no_devices),
                                    textAlign = TextAlign.Center,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(top = 20.dp),
                                )
                            } else {
                                Column(modifier = Modifier.fillMaxSize()) {
                                    Row(modifier = Modifier.width(250.dp).padding(15.dp).align(Alignment.End),
                                        horizontalArrangement = Arrangement.End) {
                                        filterMap.get(devicesUiState.filter)?.let { it1 ->
                                            OurDropdownMenu(
                                                items = filterMap,
                                                selectedItem = it1,
                                                onItemSelected = devicesViewModel::setFilter,
                                                title = stringResource(R.string.filter)
                                            )
                                        }
                                    }
                                    LazyVerticalGrid(
                                        columns = GridCells.Adaptive(150.dp),
                                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                                        verticalArrangement = Arrangement.spacedBy(16.dp),
                                        contentPadding = PaddingValues(5.dp),
                                        modifier = Modifier
                                            .padding(
                                                start = 4.dp,
                                                end = 4.dp,
                                                bottom = 4.dp,
                                                top = 25.dp
                                            )
                                            .heightIn(min = 200.dp)
                                    ) {
                                        if(devicesUiState.devices != null) {
                                            var myDevices: List<Device> = if( devicesUiState.filter == "all" ) {
                                                devicesUiState.devices?.devices!!
                                            } else {
                                                devicesUiState.devices?.devices?.filter { device -> device.type?.name ==  devicesUiState.filter }!!
                                            }
                                            items(myDevices.size) { index ->
                                                val device: Device = myDevices[index]
                                                DeviceComponent(
                                                    device,
                                                    navController,
                                                    sharedDeviceViewModel,
                                                    recentsModel
                                                )
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
                else {
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
                        if(devicesUiState.isLoading == true) {
                            CircularProgressIndicator()
                        } else {
                            if(devicesUiState.devices?.devices?.isEmpty() == true) {
                                Text(
                                    text = stringResource(R.string.no_devices),
                                    textAlign = TextAlign.Center,
                                    fontSize = 50.sp,
                                    modifier = Modifier.padding(top = 20.dp),
                                )
                            } else {
                                Column(modifier = Modifier.fillMaxSize()) {
                                    Row(
                                        modifier = Modifier.width(250.dp).padding(15.dp)
                                            .align(Alignment.End),
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        filterMap.get(devicesUiState.filter)?.let { it1 ->
                                            OurDropdownMenu(
                                                items = filterMap,
                                                selectedItem = it1,
                                                onItemSelected = devicesViewModel::setFilter,
                                                title = stringResource(R.string.filter)
                                            )
                                        }
                                    }
                                    LazyVerticalGrid(
                                        columns = GridCells.Adaptive(250.dp),
                                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                                        verticalArrangement = Arrangement.spacedBy(16.dp),
                                        contentPadding = PaddingValues(5.dp),
                                        modifier = Modifier
                                            .padding(
                                                start = 4.dp,
                                                end = 4.dp,
                                                bottom = 4.dp
                                            )
                                            .heightIn(min = 200.dp)
                                    ) {
                                        if (devicesUiState.devices != null) {
                                            var myDevices: List<Device> =
                                                if (devicesUiState.filter == "all") {
                                                    devicesUiState.devices?.devices!!
                                                } else {
                                                    devicesUiState.devices?.devices?.filter { device -> device.type?.name == devicesUiState.filter }!!
                                                }
                                            items(myDevices.size) { index ->
                                                val device: Device = myDevices[index]
                                                DeviceComponent(
                                                    device,
                                                    navController,
                                                    sharedDeviceViewModel,
                                                    recentsModel
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        },
        bottomBar = { BottomBar(navController = navController) }
    )
}