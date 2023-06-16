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
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contrall.R

@Preview
@Composable
fun AirConditionerScreen(
    switchState: Boolean,
    onSwitchStateChanged: (Boolean) -> Unit = {},
    func: ()->Unit = {},
    sliderValue: Float = 0f,
    onSliderValueChanged: (Float) -> Unit = {},
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
                        painter = painterResource(R.drawable.ic_baseline_ac_unit_24),
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                            .padding(end = 12.dp)
                            .align(Alignment.CenterVertically)
                    )
                    // Lamp Title
                    Text(
                        text = "Aire Acondicionado",
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
                        onCheckedChange = onSwitchStateChanged,
                        modifier = Modifier.padding(bottom = 8.dp).padding(end = 10.dp)
                        //checked = lampViewModel.switchState,
                        //checked = switchState,
                        //onCheckedChange = { set -> lampViewModel.toggleSwitch(set) },
                        //onCheckedChange = func,
                        //modifier = Modifier.padding(bottom = 10.dp)
                    )
                    Text(
                        text = if (switchState) "Prendido" else "Apagado",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .align(Alignment.CenterVertically)
                    )
                }

                val items = listOf("Item 1", "Item 2", "Item 3")
                val title1 = "Seleccione un Modo"
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    OurDropdownMenu(items, title1)
                }
                Text(
                    text = "Temperatura",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(10.dp)
                        //.align(Alignment.CenterVertically)
                )

                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = func,
                        modifier = Modifier.padding(end = 10.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)

                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_round_remove_24),
                            contentDescription = null,
                        )
                    }

                    Text(
                        text = "24 C",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(10.dp)
                    )

                    Button(
                        onClick = func,
                        modifier = Modifier.padding(end = 10.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)

                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_round_add_24),
                            contentDescription = null,
                        )
                    }

                }

                val title2 = "Velocidad Ventilador"
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    OurDropdownMenu(items, title2)
                }

                val title3 = "Desplazamiento Horizontal"
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    OurDropdownMenu(items, title3)
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
