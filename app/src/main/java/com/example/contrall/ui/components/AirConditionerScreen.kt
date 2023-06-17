package com.example.contrall.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contrall.R
import com.example.contrall.data.AirConditionerUiState
import com.example.contrall.ui.theme.PrimaryLight
import com.example.contrall.util.AirConditionerViewModel

//@Preview
@Composable
fun AirConditionerScreen(
    airConditionerViewModel: AirConditionerViewModel,
) {
    val airConditionerUiState by airConditionerViewModel.uiState.collectAsState()
    val painter = painterResource(R.drawable.background);

    Scaffold(
        topBar = {
            DevicesTopAppBar()
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
                Card(
                    border = BorderStroke(2.dp, Color.LightGray),
                    shape = RoundedCornerShape(15.dp),
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
                            text = "Aire Acondicionado",
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
                            checked = airConditionerViewModel.switchState,
                            onCheckedChange = { isChecked ->
                                airConditionerViewModel.toggleSwitchState(
                                    isChecked
                                )
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
                            text = if (airConditionerViewModel.switchState) "Prendido" else "Apagado",
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
                        OurDropdownMenu(
                            items = airConditionerUiState.modes,
                            selectedItem = airConditionerUiState.selectedMode,
                            onItemSelected = airConditionerViewModel::setMode,
                            title = "Seleccione un Modo"
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Temperatura:",
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
                            text = "${airConditionerViewModel.temperature}°C",
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
                        OurDropdownMenu(
                            items = airConditionerUiState.fanSpeeds,
                            selectedItem = airConditionerUiState.selectedFanSpeed,
                            onItemSelected = airConditionerViewModel::setFanSpeed,
                            title = "Velocidad Ventilador"
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    ) {
                        OurDropdownMenu(
                            items = airConditionerUiState.horizontalSwings,
                            selectedItem = airConditionerUiState.selectedHorizontalSwing,
                            onItemSelected = airConditionerViewModel::setHorizontalSwing,
                            title = "Desplazamiento Horizontal"
                        )
                    }
                    // Trash Bin Image
                    Image(
                        painter = painterResource(R.drawable.ic_baseline_delete_outline_24),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth().padding(10.dp)
                    )
                }
            }
        })
}


