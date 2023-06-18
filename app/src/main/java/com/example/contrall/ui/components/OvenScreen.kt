package com.example.contrall.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contrall.R
import com.example.contrall.data.OvenUIState
import com.example.contrall.ui.theme.PrimaryLight
import com.example.contrall.util.OvenViewModel


@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun OvenScreen(
    ovenViewModel : OvenViewModel,
    ovenUI : OvenUIState,
    imageResId: Int
) {

    val ovenUiState by ovenViewModel.uiState.collectAsState()
    val painter = painterResource(imageResId)
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
                    painter = painterResource(R.drawable.baseline_cookie_24),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                        .padding(end = 12.dp)
                        .align(Alignment.CenterVertically)
                )
                // Lamp Title
                Text(
                    text = "Horno",
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
                    onCheckedChange = ovenViewModel ::toggleSwitchState ,
                    modifier = Modifier.padding(bottom = 8.dp).padding(end = 10.dp),
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = PrimaryLight, // Change the color of the switch thumb when checked
                        checkedTrackColor = PrimaryLight, // Change the color of the switch track when checked
                        uncheckedThumbColor = Color.Gray, // Change the color of the switch thumb when unchecked
                        uncheckedTrackColor = Color.LightGray // Change the color of the switch track when unchecked
                    ),
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

            val title1 = "Fuente Calor"
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                OurDropdownMenu(ovenUI.heatModes, title1)
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
                    onClick = ovenViewModel :: decreaseTemperatureValue,
                    modifier = Modifier.padding(end = 10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)

                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_round_remove_24),
                        contentDescription = null,
                    )
                }

                TextField(
                    value = "100", // Set the initial text value
                    onValueChange = { value -> ovenViewModel.setTemperatureValue(value.toInt()) },
                    modifier = Modifier.width(100.dp) // Expand to fill the available width
                )

                Button(
                    onClick = ovenViewModel :: increaseTemperatureValue,
                    modifier = Modifier.padding(end = 10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)

                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_round_add_24),
                        contentDescription = null,
                    )
                }

            }
            val titleGrill = "Modo Grill"
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                OurDropdownMenu(ovenUI.grillModes, titleGrill)
            }
            val titleConvection = "Modo Conveccion"
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                OurDropdownMenu(ovenUI.convModes, titleConvection)
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

