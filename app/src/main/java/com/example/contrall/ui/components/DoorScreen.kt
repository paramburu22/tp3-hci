package com.example.contrall.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.contrall.R

//@Preview
@Composable
fun DoorScreen(
    //lampViewModel: LampViewModel,
    lockState: Boolean = false,
    openState: Boolean = false,
    onLockStateChanged: (Boolean) -> Unit = {},
    onOpenStateChanged: (Boolean) -> Unit = {},
) {
    Card(
        //onClick = func,
        //modifier = Modifier.fillMaxSize(),
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
                .fillMaxWidth()
            ){

                Image(
                    painter = painterResource(R.drawable.ic_baseline_lightbulb_24),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
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
                    modifier = Modifier.padding(bottom = 8.dp).padding(end = 10.dp)
                )
                Text(
                    text = if (lockState) "Bloqueada" else "Desbloqueada",
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
                // Open Switch
                Switch(
                    checked = openState,
                    onCheckedChange = { value -> onOpenStateChanged(value) },
                    modifier = Modifier.padding(bottom = 8.dp).padding(end = 10.dp)
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
