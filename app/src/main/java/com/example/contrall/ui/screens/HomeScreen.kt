package com.example.contrall.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contrall.R
import com.example.contrall.data.network.models.Device
import com.example.contrall.ui.components.BottomBar
import com.example.contrall.ui.components.DeviceComponent
import com.example.contrall.ui.components.TopAppBar
import com.example.contrall.util.RecentsViewModel
import com.example.contrall.util.SharedDeviceModel

@Composable
fun HomeScreen(recentsViewModel: RecentsViewModel, navController: NavController, sharedDeviceModel: SharedDeviceModel) {
    val painter = painterResource(R.drawable.background)
    Scaffold(
        topBar = {
            TopAppBar(title = stringResource(R.string.home_title), showIcon = false)
        },
        content = {
            BoxWithConstraints() {
                if(maxWidth < 400.dp){
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
                        Column(
                            modifier = Modifier.padding(30.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(R.string.recent_title),
                                fontSize = 30.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                            if(recentsViewModel.recentDevices.size <= 0) {
                                Text(
                                    text = stringResource(R.string.no_recent),
                                    textAlign = TextAlign.Center,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(top = 20.dp)
                                )
                            } else {
                                LazyColumn(
                                    verticalArrangement = Arrangement.spacedBy(16.dp),
                                    contentPadding = PaddingValues(5.dp),
                                    modifier = Modifier
                                        .padding(start = 4.dp, end = 4.dp, bottom = 4.dp, top = 25.dp)
                                        .heightIn(min = 200.dp)
                                ) {
                                    val myDevices: MutableList<Device> =
                                        recentsViewModel.recentDevices.asReversed()
                                    if (myDevices != null) {
                                        items(myDevices.size) { index ->
                                            val device: Device = myDevices[index]
                                            DeviceComponent(
                                                device,
                                                navController,
                                                sharedDeviceModel,
                                                recentsViewModel
                                            )
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
                        Column(
                            modifier = Modifier.padding(30.dp).fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(R.string.recent_title),
                                fontSize = 50.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 20.dp)
                            )
                            if(recentsViewModel.recentDevices.size <= 0) {
                                Text(
                                    modifier = Modifier.padding(top = 50.dp, start = 30.dp).heightIn(min = 32.dp),
                                    text = stringResource(R.string.no_recent),
                                    textAlign = TextAlign.Center,
                                    fontSize = 60.sp,

                                )
                            } else {
                                LazyVerticalGrid(
                                    columns = GridCells.Adaptive(500.dp),
                                    verticalArrangement = Arrangement.spacedBy(40.dp),
                                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                                    contentPadding = PaddingValues(10.dp),
                                    modifier = Modifier
                                        .padding(start = 4.dp, end = 4.dp, bottom = 4.dp, top = 25.dp)
                                        .heightIn(min = 200.dp)
                                ) {
                                    val myDevices: MutableList<Device> =
                                        recentsViewModel.recentDevices.asReversed()
                                    if (myDevices != null) {
                                        items(myDevices.size) { index ->
                                            val device: Device = myDevices[index]
                                            DeviceComponent(
                                                device,
                                                navController,
                                                sharedDeviceModel,
                                                recentsViewModel
                                            )
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