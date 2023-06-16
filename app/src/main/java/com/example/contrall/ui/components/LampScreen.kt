package com.example.contrall.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contrall.R
import com.example.contrall.data.LampUiState
import com.example.contrall.ui.theme.PrimaryLight
import com.example.contrall.util.LampViewModel

//@Preview
@Composable
fun LampScreen(
    lampViewModel: LampViewModel,
    lampUiState: LampUiState
    /*intensityValue: Float,
    switchState: Boolean,
    onSwitchStateChanged: (Boolean) -> Unit = {},
    func: ()->Unit = {},
    sliderValue: Float = 0f,
    onSliderValueChanged: (Float) -> Unit = {},*/
) {
    val switchState = lampViewModel.switchState
    val intensityValue = lampViewModel.intensityValue

    Box(
        //modifier = Modifier.size(width = 150.dp, height = 110.dp).padding(start = 8.dp, end = 8.dp),
    ) {
        Card(
            //onClick = func,
            //modifier = Modifier.fillMaxSize(),
            border = BorderStroke(2.dp, Color.LightGray),
            shape = RoundedCornerShape(15.dp),
            //elevation = 2.dp
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
                        painter = painterResource(R.drawable.ic_baseline_lightbulb_24),
                        contentDescription = null,
                        modifier = Modifier
                            .size(48.dp)
                            .padding(end = 12.dp)
                            .align(Alignment.CenterVertically)
                    )
                    // Lamp Title
                    Text(
                        text = "Lamp",
                        fontSize = 30.sp,
                    )
                }

                Divider(modifier = Modifier.fillMaxWidth())


                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    // Lamp Switch
                    Switch(
                        checked = switchState,
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = PrimaryLight, // Change the color of the switch thumb when checked
                            checkedTrackColor = PrimaryLight, // Change the color of the switch track when checked
                            uncheckedThumbColor = Color.Gray, // Change the color of the switch thumb when unchecked
                            uncheckedTrackColor = Color.LightGray // Change the color of the switch track when unchecked
                        ),
                        onCheckedChange = lampViewModel::toggleSwitchState,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .padding(end = 10.dp)
                        /*
                        checked = switchState,
                        onCheckedChange = onSwitchStateChanged,
                        modifier = Modifier.padding(bottom = 8.dp).padding(end = 10.dp)*/
                        //checked = lampViewModel.switchState,
                        //checked = switchState,
                        //onCheckedChange = { set -> lampViewModel.toggleSwitch(set) },
                        //onCheckedChange = func,
                        //modifier = Modifier.padding(bottom = 10.dp)
                    )
                    Text(
                        text = if (switchState) "Prendido" else "Apagado",
                        //text = if (switchState) "Prendido" else "Apagado",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .align(Alignment.CenterVertically)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    // Color Button
                    Button(
                        onClick = lampUiState.func,
                        modifier = Modifier.padding(end = 10.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)

                    ) {
                        Text(text = "Elegir Color")
                    }
                    Text(
                        text = "Hexa: ",
                        fontSize = 18.sp,
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Intensidad: ${intensityValue.toInt()}%",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(end = 10.dp)
                    )
                    // Intensity SeekBar
                    Slider(
                        value = intensityValue.toInt().toFloat(),
                        onValueChange = { value -> lampViewModel.setIntensityValue(value.toInt().toFloat()) },
                        valueRange = 0f..100f,
                        steps = 1,
                        colors = SliderDefaults.colors(
                            thumbColor = Color(android.graphics.Color.parseColor("#146C94")),
                            activeTrackColor = Color(android.graphics.Color.parseColor("#146C94")),
                            inactiveTrackColor = Color.LightGray
                        )

                        /*
                        value = lampViewModel.intensity.toFloat(),
                        onValueChange = { value -> lampViewModel.setIntensity(value.toInt()) },
                        valueRange = 0f..100f,
                        modifier = Modifier.padding(bottom = 10.dp)*/
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
        //}
    }
}

