package com.example.contrall.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contrall.R
import com.example.contrall.data.network.models.Device
import com.example.contrall.data.network.models.DevicesList
import com.example.contrall.ui.components.DeviceComponent
import com.example.contrall.ui.components.TopAppBar
@Preview
@Composable
fun RoutinesScreen() {
    val painter = painterResource(R.drawable.background)
    Scaffold(
        topBar = {
            TopAppBar(title = "Rutinas", showIcon = false)
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
                /*val myRoutines : RoutinesList? = routineUiState.devices
                    if (myRoutines != null) {
                        items(myRoutines.devices.size) { index ->
                            val routine : Routine = myRoutines.routines[index]
                            RoutineComponent(routine, navController)
                        }
                    }*/

                Card(
                    border = BorderStroke(2.dp, Color.LightGray),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.padding(16.dp),
                    backgroundColor = Color.LightGray
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Routine Title
                            Text(
                                text = "Rutina",
                                fontSize = 30.sp,
                                modifier = Modifier.weight(1f)
                            )
                            Image(
                                painter = painterResource(R.drawable.ic_baseline_play_circle_outline_24),
                                contentDescription = null,
                                modifier = Modifier.size(48.dp)
                            )
                        }
                        Card(
                            border = BorderStroke(2.dp, Color.LightGray),
                            shape = RoundedCornerShape(15.dp),
                            modifier = Modifier.padding(16.dp),
                            backgroundColor = Color.White,
                        ) {
                            Column(modifier = Modifier.padding(20.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(20.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Luz",
                                        fontSize = 30.sp
                                    )
                                }
                                Row(){
                                    Text(
                                        text = "Prender Luz",
                                        fontSize = 20.sp,
                                        color = Color.Gray
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}
