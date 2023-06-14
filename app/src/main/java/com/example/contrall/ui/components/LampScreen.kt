package com.example.contrall.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contrall.R
import com.example.contrall.util.LampViewModel

@Composable
fun LampScreen(
    lampViewModel: LampViewModel,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        // Your UI content goes here
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            // Lamp Title
            Text(
                text = "Lamp",
                fontSize = 30.sp,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            // Lamp Switch
            Switch(
                checked = lampViewModel.switchState,
                onCheckedChange = { set -> lampViewModel.toggleSwitch(set) },
                modifier = Modifier.padding(bottom = 10.dp)
            )


            // Color Button
            Button(
                onClick = { /* ???? */ },
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                Text(text = "Choose Color")
            }

            // Intensity SeekBar
            Slider(
                value = lampViewModel.intensity.toFloat(),
                onValueChange = { value -> lampViewModel.setIntensity(value.toInt()) },
                valueRange = 0f..100f,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            // Trash Bin Image
            Image(
                painter = painterResource(R.drawable.ic_baseline_delete_outline_24),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
