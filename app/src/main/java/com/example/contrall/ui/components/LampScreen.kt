package com.example.contrall.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contrall.R
import com.example.contrall.ui.theme.PrimaryLight
import com.example.contrall.util.LampViewModel


//@Preview
@Composable
fun LampScreen(
    lampViewModel: LampViewModel,
    navController: NavController,
) {
    val lampUiState by lampViewModel.uiState.collectAsState()
    val painter = painterResource(R.drawable.background)

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
                Card(
                    border = BorderStroke(2.dp, Color.LightGray),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.padding(16.dp)
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
                            text = "Luz",
                            fontSize = 30.sp,
                        )
                    }

                        Divider()

                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Switch(
                                checked = lampUiState.state.isOn,
                                onCheckedChange =
                                {lampViewModel.toggleSwitchState()}
                                ,
                                modifier = Modifier.padding(end = 8.dp),
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = PrimaryLight,
                                    checkedTrackColor = PrimaryLight,
                                    uncheckedThumbColor = Color.Gray,
                                    uncheckedTrackColor = Color.Gray
                                )
                            )
                            Text(
                                text = if (lampUiState.state.isOn) "Prendido" else "Apagado",
                                fontSize = 18.sp
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(){
                                Box(
                                    modifier = Modifier.padding(8.dp)
                                        .size(80.dp)
                                        .fillMaxWidth()
                                        .background(hexToColor(lampUiState.state.color!!))
                                        .clickable { lampViewModel.changeDialog(true) }
                                )
                            }
                            Column() {
                                Text(
                                    text = "Valor Hexa: ${colorToHex(lampViewModel.selectedColor)}",
                                    fontSize = 18.sp
                                )

                                Text(
                                    text = "Valor RGB: ${colorToRgb(lampViewModel.selectedColor)} ",
                                    fontSize = 18.sp
                                )
                            }

                        }

                        Row(
                            modifier = Modifier
                                .padding( 8.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Intensidad: ${lampUiState.state.brightness!!.toInt()}%",
                                fontSize = 18.sp,
                                modifier = Modifier.padding(end = 10.dp)
                            )
                            Slider(
                                value = lampUiState.state.brightness!!.toFloat(),
                                onValueChange = { value ->
                                    lampViewModel.setIntensityValue(value.toInt().toFloat())
                                },
                                valueRange = 0f..100f,
                                steps = 1,
                                colors = SliderDefaults.colors(
                                    thumbColor = Color(android.graphics.Color.parseColor("#146C94")),
                                    activeTrackColor = Color(android.graphics.Color.parseColor("#146C94")),
                                    inactiveTrackColor = Color.LightGray
                                ),
                                modifier = Modifier.width(260.dp)
                            )
                        }

                        Image(
                            painter = painterResource(R.drawable.ic_baseline_delete_outline_24),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth()
                        )
                        if (lampViewModel.showDialog) {
                            ColorPickerDialog(
                                initialColor = hexToColor(lampUiState.state.color!!),
                                onColorSelected = { color ->
                                    lampViewModel.setColor(color)
                                },
                                onDismiss = { lampViewModel.changeDialog(false) },
                            )
                        }

                }

            }

        }
    )

}
fun colorToHex(color: Color): String {
    val alpha = (color.alpha * 255).toInt()
    val red = (color.red * 255).toInt()
    val green = (color.green * 255).toInt()
    val blue = (color.blue * 255).toInt()
    return String.format("#%02x%02x%02x%02x", alpha, red, green, blue)
}

fun colorToRgb(color: Color): Triple<Int, Int, Int> {
    val red = (color.red * 255).toInt()
    val green = (color.green * 255).toInt()
    val blue = (color.blue * 255).toInt()
    return Triple(red, green, blue)
}

fun hexToColor(hex : String) : Color {
    val red = Integer.parseInt(hex.substring(0, 2), 16)
    val green = Integer.parseInt(hex.substring(2, 4), 16)
    val blue = Integer.parseInt(hex.substring(4, 6), 16)
    // Create the Color object with the extracted color values
    return Color(red, green, blue)
}