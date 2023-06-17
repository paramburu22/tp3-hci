package com.example.contrall.ui.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contrall.R

@Composable
@Preview
fun DeviceComponent() {
    Card(
        border = BorderStroke(2.dp, Color.LightGray),
        shape = RoundedCornerShape(15.dp),
    ) {
        Row(modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        )
        {
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
                fontSize = 24.sp,
            )
        }
        Row(modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        )
        {
            Text(
                text = "Oficina - Baño",
                fontSize = 14.sp,
            )
        }
    }
}