package com.example.contrall.ui.components

import android.content.res.Resources.Theme
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.contrall.R

@Preview
@Composable
fun DoorScreen(
    //lampViewModel: LampViewModel,
    lockState: Boolean = false,
    openState: Boolean = false,
    onLockStateChanged: (Boolean) -> Unit = {},
    onOpenStateChanged: (Boolean) -> Unit = {},
) {
    val painter = painterResource(R.drawable.background);
    val contextForToast = LocalContext.current.applicationContext;

    Scaffold(
        topBar = {
            DevicesTopAppBar()
        }, content = {
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
                    //onClick = func,
                    modifier = Modifier
                        .padding(25.dp, 0.dp),
                    border = BorderStroke(2.dp, Color.LightGray),
                    shape = RoundedCornerShape(15.dp),
                    //elevation = 2.dp
                ){

                    // Your UI content goes here
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Row(modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ){
                            Image(
                                painter = painterResource(R.drawable.outline_sensor_door_24),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(48.dp)
                                    .padding(end = 12.dp)
                                    .align(Alignment.CenterVertically)
                            )
                            // Door Title
                            Text(
                                text = "Door",
                                fontSize = 30.sp,
                            )
                        }

                        Divider(modifier = Modifier.fillMaxWidth())


                        Row(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        ) {
                            // Lock Switch
                            Switch(
                                checked = lockState,
                                onCheckedChange = { value -> onLockStateChanged(value) },
                                modifier = Modifier
                                    .padding(bottom = 8.dp)
                                    .padding(end = 10.dp)
                            )
                            Text(
                                text = if (lockState) "Bloqueada" else "Desbloqueada",
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(bottom = 8.dp)
                                    .align(Alignment.CenterVertically)
                            )
                        }

                        Divider(modifier = Modifier.fillMaxWidth())

                        Row(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        ) {
                            // Open Switch
                            Switch(
                                checked = openState,
                                onCheckedChange = { value -> onOpenStateChanged(value) },
                                modifier = Modifier
                                    .padding(bottom = 8.dp)
                                    .padding(end = 10.dp)
                            )
                            Text(
                                text = if (openState) "Abierta" else "Cerrada",
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(bottom = 8.dp)
                                    .align(Alignment.CenterVertically)
                            )
                        }

                        // Trash Bin Image
                        Image(
                            painter = painterResource(R.drawable.ic_baseline_delete_outline_24),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        })
}
