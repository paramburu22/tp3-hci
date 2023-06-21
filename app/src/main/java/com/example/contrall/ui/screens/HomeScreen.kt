package com.example.contrall.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contrall.R
import com.example.contrall.data.network.models.Device
import com.example.contrall.data.network.models.DevicesList
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
            TopAppBar(title = "Inicio", showIcon = false)
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
                Column(
                    modifier = Modifier.padding(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Dispositivos Recientes",
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    if(recentsViewModel.recentDevices.size <= 0) {
                        Text(
                            text = "No hay dispositivos utilizados recientemente",
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
        },
        bottomBar = { BottomBar(navController = navController) }
    )
}