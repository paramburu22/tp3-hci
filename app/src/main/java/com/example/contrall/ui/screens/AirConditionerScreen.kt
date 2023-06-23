package com.example.contrall.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contrall.R
import com.example.contrall.data.DropdownClass
import com.example.contrall.ui.theme.PrimaryLight
import com.example.contrall.util.AirConditionerViewModel

//@Preview
@Composable
fun AirConditionerScreen(
    airConditionerViewModel: AirConditionerViewModel,
    navController: NavController,
) {
    val airConditionerUiState by airConditionerViewModel.uiState.collectAsState()
    val painter = painterResource(R.drawable.background);

    val modeMap : Map<String, String> = mapOf(
        "cool" to stringResource(R.string.cool),
        "heat" to stringResource(R.string.heat),
        "fan" to stringResource(R.string.fan)
    )

    val horSwingMap : Map<String, String> = mapOf(
        "auto" to stringResource(R.string.auto),
        "22" to "22",
        "45" to "45",
        "67" to "67",
        "90" to "90"
    )

    val fanSpeedMap : Map<String, String> = mapOf(
        "auto" to stringResource(R.string.auto),
        "25" to "25%",
        "50" to "50%",
        "75" to "75%",
        "100" to "100%"
    )

    val verSwingMap : Map<String, String> = mapOf(
        "auto" to stringResource(R.string.auto),
        "-90" to "-90",
        "-45" to "-45",
        "0" to "0",
        "45" to "45",
        "90" to "90"
    )

    Scaffold(
        topBar = {
            TopAppBar(navController)
        },content = {
            Box( modifier = Modifier
                .fillMaxSize()
                .padding(it),
                contentAlignment = (Alignment.Center),
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)

                ){
                    item  {
                        Card(
                            border = BorderStroke(2.dp, Color.LightGray),
                            shape = RoundedCornerShape(15.dp),
                            modifier = Modifier.padding(16.dp)
                        ) {

                            // Your UI content goes here
                            Row(
                                modifier = Modifier
                                    .padding(20.dp)
                                    .fillMaxWidth()
                            ) {

                                Image(
                                    painter = painterResource(R.drawable.ic_baseline_ac_unit_24),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(48.dp)
                                        .padding(end = 12.dp)
                                        .align(Alignment.CenterVertically)
                                )
                                // Lamp Title
                                Text(
                                    text = "${airConditionerUiState.name}",
                                    fontSize = 30.sp,
                                )
                            }

                            Divider(modifier = Modifier.fillMaxWidth())


                            Row(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                            ) {
                                Switch(
                                    checked = airConditionerUiState.state.isOn,
                                    onCheckedChange = {
                                        airConditionerViewModel.switchState()
                                    },
                                    modifier = Modifier
                                        .padding(bottom = 8.dp)
                                        .padding(end = 10.dp),
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = PrimaryLight, // Change the color of the switch thumb when checked
                                        checkedTrackColor = PrimaryLight, // Change the color of the switch track when checked
                                        uncheckedThumbColor = Color.Gray, // Change the color of the switch thumb when unchecked
                                        uncheckedTrackColor = Color.Gray // Change the color of the switch track when unchecked
                                    ),
                                )
                                Text(
                                    text = if (airConditionerUiState.state.isOn) stringResource(R.string.on) else stringResource(R.string.off),
                                    fontSize = 18.sp,
                                    modifier = Modifier
                                        .padding(bottom = 8.dp)
                                        .align(Alignment.CenterVertically)
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start

                            ) {
                                modeMap.get(airConditionerUiState.state.mode)?.let { it1 ->
                                    OurDropdownMenu(
                                        items = modeMap,
                                        selectedItem = it1,
                                        onItemSelected = airConditionerViewModel::setMode,
                                        title = stringResource(R.string.mode)
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "${stringResource(R.string.temp)}:",
                                    fontSize = 18.sp
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center

                            ) {
                                IconButton(
                                    onClick = { airConditionerViewModel.decreaseTemperature() },
                                    modifier = Modifier.size(30.dp).padding(end = 10.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_round_remove_24),
                                        contentDescription = "minus"
                                    )
                                }

                                Text(
                                    text = "${airConditionerUiState.state.temperature}°C",
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(end = 10.dp)
                                )


                                IconButton(
                                    onClick = { airConditionerViewModel.increaseTemperature() },
                                    modifier = Modifier.size(30.dp).padding(end = 10.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_round_add_24),
                                        contentDescription = "plus"
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                            ) {
                                verSwingMap[airConditionerUiState.state.verticalSwing]?.let { it1 ->
                                    OurDropdownMenu(
                                        items = verSwingMap,
                                        selectedItem = it1,
                                        onItemSelected = airConditionerViewModel::setVerticalSwing,
                                        title = stringResource(R.string.v_swing)
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                            ) {
                                horSwingMap[airConditionerUiState.state.horizontalSwing]?.let { it1 ->
                                    OurDropdownMenu(
                                        items = horSwingMap,
                                        selectedItem = it1,
                                        onItemSelected = airConditionerViewModel::setHorizontalSwing,
                                        title = stringResource(R.string.h_swing)
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                            ) {

                                fanSpeedMap.get(airConditionerUiState.state.fanSpeed)?.let { it1 ->
                                    OurDropdownMenu(
                                        items = fanSpeedMap,
                                        selectedItem = it1,
                                        onItemSelected = airConditionerViewModel::setFanSpeed,
                                        title = stringResource(R.string.fan_speed)
                                    )
                                }
                            }
                        }
                    }}
            }
        })
}



