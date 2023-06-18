package com.example.contrall.ui.components

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun ColorPickerDialog(
    initialColor: Color,
    onColorSelected: (Color) -> Unit,
    onDismiss: () -> Unit,

) {

    // Track the RGB values
    var red = remember { mutableStateOf(initialColor.red) }
    var green = remember { mutableStateOf(initialColor.green) }
    var blue = remember { mutableStateOf(initialColor.blue) }

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
                    text = "Elegi un Color Nuevo",
                    fontSize = 20.sp,
                    modifier = Modifier.padding( 8.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    // Red slider
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
                        .padding(bottom = 16.dp)
                ) {
                    // Green slider
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
                        .padding(bottom = 16.dp)
                ){
                    // Blue slider
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
                    Column(modifier = Modifier.padding(6.dp).fillMaxWidth()) {
                        Row(modifier = Modifier.padding(6.dp).fillMaxWidth()){
                            Button(
                                onClick = onDismiss,
                                modifier = Modifier.padding(end=26.dp)
                            ) {
                                Text(text = "Cancel")
                            }

                            // Confirm button
                            Button(
                                onClick = {
                                    val color =
                                        Color(red = red.value, green = green.value, blue = blue.value)
                                    onColorSelected(color)
                                    onDismiss()
                                },
                            ) {
                                Text(text = "OK")
                            }
                        }

                    }
                }
            }
        }
    }
}
