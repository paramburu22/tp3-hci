package com.example.contrall.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contrall.R

@Composable
fun ColorPickerDialog(
    initialColor: Color,
    onColorSelected: (Color) -> Unit,
    onDismiss: () -> Unit,

) {

    // Track the RGB values
    val red = remember { mutableStateOf(initialColor.red) }
    val green = remember { mutableStateOf(initialColor.green) }
    val blue = remember { mutableStateOf(initialColor.blue) }

    // Dialog background with a semi-transparent overlay
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Color picker content
        Card(
            modifier = Modifier
                .width(400.dp)
                .wrapContentHeight()
                .padding(16.dp),
            elevation = 8.dp
        ) {
            Column {
                Text(
                    text = stringResource(R.string.new_color),
                    fontSize = 20.sp,
                    modifier = Modifier.padding( 8.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    // Red slider
                    Text(
                        text = "R: ${(red.value * 255).toInt()}"
                    )
                    Slider(
                        value = red.value,
                        onValueChange = { value ->
                            red.value = value
                        },
                        valueRange = 0f..1f,
                        colors = SliderDefaults.colors(
                            thumbColor = Color.Red,
                            activeTrackColor = Color.Red,
                            inactiveTrackColor = Color.LightGray
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    // Green slider
                    Text(
                        text = "G: ${(green.value * 255).toInt()}"
                    )
                    Slider(
                        value = green.value,
                        onValueChange = { value ->
                            green.value = value
                        },
                        valueRange = 0f..1f,
                        colors = SliderDefaults.colors(
                            thumbColor = Color.Green,
                            activeTrackColor = Color.Green,
                            inactiveTrackColor = Color.LightGray
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                ){
                    // Blue slider
                    Text(
                        text = "B: ${(blue.value * 255).toInt()}"
                    )
                    Slider(
                        value = blue.value,
                        onValueChange = { value ->
                            blue.value = value
                        },
                        valueRange = 0f..1f,
                        colors = SliderDefaults.colors(
                            thumbColor = Color.Blue,
                            activeTrackColor = Color.Blue,
                            inactiveTrackColor = Color.LightGray
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column() {
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(80.dp)
                                .fillMaxWidth()
                                .background(
                                    Color(
                                        red = red.value,
                                        green = green.value,
                                        blue = blue.value
                                    )
                                )
                        )
                    }
                    Column(modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth()) {
                        val color = Color(red = red.value, green = green.value, blue = blue.value)
                        Row(modifier = Modifier
                            .padding(6.dp)
                            .fillMaxWidth()){
                            Text(
                                text = "${stringResource(R.string.hex)}: #${colorToHex(color)}"
                            )
                        }
                        Row(modifier = Modifier
                            .padding(6.dp)
                            .fillMaxWidth()){
                            Text(
                                text = "${stringResource(R.string.rgb)}: #${colorToRgb(color)}"
                            )
                        }
                        Row(modifier = Modifier
                            .padding(6.dp)
                            .fillMaxWidth()){
                            Button(
                                onClick = onDismiss,
                                modifier = Modifier.padding(end=26.dp)
                            ) {
                                Text(text = stringResource(R.string.cancel))
                            }

                            // Confirm button
                            Button(
                                onClick = {
                                    onColorSelected(color)
                                    onDismiss()
                                },
                            ) {
                                Text(text = stringResource(R.string.done))
                            }
                        }

                    }
                }
            }
        }
    }
}
fun colorToHex(color: Color): String {
    val red = (color.red * 255).toInt()
    val green = (color.green * 255).toInt()
    val blue = (color.blue * 255).toInt()
    return String.format("%02x%02x%02x", red, green, blue)
}

fun colorToRgb(color: Color): Triple<Int, Int, Int> {
    val red = (color.red * 255).toInt()
    val green = (color.green * 255).toInt()
    val blue = (color.blue * 255).toInt()
    return Triple(red, green, blue)
}