package com.example.contrall.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contrall.R
import com.example.contrall.Screen
import com.example.contrall.data.network.models.Device
import com.example.contrall.util.RecentsViewModel
import com.example.contrall.util.SharedDeviceModel

@Composable

fun DeviceComponent(device : Device, navController: NavController, sharedDeviceModel: SharedDeviceModel, recentsViewModel: RecentsViewModel) {
    lateinit var icon : Painter
    when(device.type?.name) {
        "lamp" -> icon = painterResource(id = R.drawable.ic_baseline_lightbulb_24)
        "oven" -> icon = painterResource(id = R.drawable.baseline_cookie_24)
        "ac" -> icon = painterResource(id = R.drawable.ic_baseline_ac_unit_24)
        "speaker" -> icon = painterResource(id = R.drawable.outline_speaker_24)
        "door" -> icon = painterResource(id = R.drawable.outline_sensor_door_24)
    }

    BoxWithConstraints(modifier = Modifier.fillMaxHeight()) {
        if(maxWidth < 400.dp)
            Card(
                modifier = Modifier
                    .clickable {
                        sharedDeviceModel.addDevice(device)
                        recentsViewModel.addDevice(device)
                        navController?.navigate(Screen.DeviceScreen.route)
                    }
                    .fillMaxHeight(),
            ) {
                Row(modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                )
                {
                    Image(
                        painter = icon,
                        contentDescription = null,
                        modifier = Modifier
                            .size(38.dp)
                            .padding(end = 12.dp)
                            .align(Alignment.CenterVertically)
                    )
                    device.name?.let {
                        Text(
                            text = it,
                            fontSize = 24.sp,
                            maxLines = 3,
                        )
                    }
                }
                Row(modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                )
                {
                    Text(
                        text = "${device.room?.home?.name} - ${device.room?.name}",
                        fontSize = 14.sp,
                    )
                }
            }
        else {
            Card(
                modifier = Modifier
                    .clickable {
                        sharedDeviceModel.addDevice(device)
                        recentsViewModel.addDevice(device)
                        navController?.navigate(Screen.DeviceScreen.route)
                    }
                    .fillMaxHeight(),
            ) {
                Row(modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                )
                {
                    Image(
                        painter = icon,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(end = 12.dp)
                            .align(Alignment.CenterVertically)
                    )
                    device.name?.let {
                        Text(
                            text = it,
                            fontSize = 30.sp,
                            maxLines = 3,
                        )
                    }
                    Text(
                        text = "${device.room?.home?.name} - ${device.room?.name}",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(end = 15.dp)

                    )
                }

            }
        }
    }

}