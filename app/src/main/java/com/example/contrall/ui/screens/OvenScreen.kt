package com.example.contrall.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
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
import com.example.contrall.ui.theme.PrimaryLight
import com.example.contrall.util.OvenViewModel


@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun OvenScreen(
    ovenViewModel : OvenViewModel,
    navController: NavController,
) {
    val ovenUiState by ovenViewModel.uiState.collectAsState()
    val painter = painterResource(R.drawable.background)

    val heatModeMap : Map<String, String> = mapOf(
        "conventional" to stringResource(R.string.conv),
        "top" to stringResource(R.string.top),
        "bottom" to stringResource(R.string.bottom)
    )

    val grillModeMap : Map<String, String> = mapOf(
        "large" to stringResource(R.string.mode_large),
        "eco" to stringResource(R.string.mode_eco),
        "off" to stringResource(R.string.off)
    )

    val convModeMap : Map<String, String> = mapOf(
        "normal" to stringResource(R.string.mode_normal),
        "eco" to stringResource(R.string.mode_eco),
        "off" to stringResource(R.string.off)
    )

    Scaffold(
        topBar = {
            TopAppBar(navController)
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                //contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
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
                            Column(
                                modifier = Modifier
                                    .padding(10.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(20.dp)
                                        .fillMaxWidth()
                                ) {

                                    Image(
                                        painter = painterResource(R.drawable.baseline_cookie_24),
                                        contentDescription = null,
                                        modifier = Modifier.size(48.dp)
                                            .padding(end = 12.dp)
                                            .align(Alignment.CenterVertically)
                                    )
                                    Text(
                                        text = "${ovenUiState.name}",
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
                                        checked = ovenUiState.state.isOn,
                                        onCheckedChange = {
                                            ovenViewModel.switchState()
                                        },
                                        modifier = Modifier.padding(bottom = 8.dp).padding(end = 10.dp),
                                        colors = SwitchDefaults.colors(
                                            checkedThumbColor = PrimaryLight, // Change the color of the switch thumb when checked
                                            checkedTrackColor = PrimaryLight, // Change the color of the switch track when checked
                                            uncheckedThumbColor = Color.Gray, // Change the color of the switch thumb when unchecked
                                            uncheckedTrackColor = Color.LightGray // Change the color of the switch track when unchecked
                                        ),
                                    )
                                    Text(
                                        text = if (ovenUiState.state.isOn) stringResource(R.string.on) else stringResource(R.string.off),
                                        fontSize = 18.sp,
                                        modifier = Modifier
                                            .padding(bottom = 8.dp)
                                            .align(Alignment.CenterVertically)
                                    )
                                }

                                Row(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .fillMaxWidth()
                                ) {
                                    heatModeMap.get(ovenUiState.state.heat)?.let { it1 ->
                                        OurDropdownMenu(
                                            items = heatModeMap,
                                            selectedItem = it1,
                                            onItemSelected = ovenViewModel::setHeatMode,
                                            title = stringResource(R.string.heat_source)
                                        )
                                    }
                                }

                                Text(
                                    text = stringResource(R.string.temp),
                                    fontSize = 18.sp,
                                    modifier = Modifier
                                        .padding(10.dp)
                                    //.align(Alignment.CenterVertically)
                                )

                                Row(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Button(
                                        onClick = {ovenViewModel.decreaseTemperatureValue()},
                                        modifier = Modifier.padding(end = 10.dp),
                                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)

                                    ) {
                                        Image(
                                            painter = painterResource(R.drawable.ic_round_remove_24),
                                            contentDescription = null,
                                        )
                                    }

                                    TextField(
                                        value = ovenUiState.state.temperature,
                                        onValueChange = { value: String ->
                                            val intValue = value.toIntOrNull() ?: 0
                                            ovenViewModel.setTemperatureValue(intValue)
                                        },
                                        modifier = Modifier.width(100.dp) // Expand to fill the available width
                                    )

                                    Button(
                                        onClick = { ovenViewModel.increaseTemperatureValue() },
                                        modifier = Modifier.padding(end = 10.dp),
                                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)

                                    ) {
                                        Image(
                                            painter = painterResource(R.drawable.ic_round_add_24),
                                            contentDescription = null,
                                        )
                                    }

                                }
                                Row(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .fillMaxWidth()
                                ) {
                                    grillModeMap.get(ovenUiState.state.grill)?.let { it1 ->
                                        OurDropdownMenu(
                                            items = grillModeMap,
                                            selectedItem = it1,
                                            onItemSelected = ovenViewModel::setGrillMode,
                                            title = stringResource(R.string.grill_mode)
                                        )
                                    }
                                }
                                Row(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .fillMaxWidth()
                                ) {
                                    convModeMap.get(ovenUiState.state.convection)?.let { it1 ->
                                        OurDropdownMenu(
                                            items = convModeMap,
                                            selectedItem = it1,
                                            onItemSelected = ovenViewModel::setConvMode,
                                            title = stringResource(R.string.convection_mode)
                                        )
                                    }
                                }
                            }
                        }
                    }}

            }

    })
}


