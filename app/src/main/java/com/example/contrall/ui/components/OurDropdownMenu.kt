package com.example.contrall.ui.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun OurDropdownMenu(items: List<String>, title: String) {
    //val items = listOf("Item 1", "Item 2", "Item 3")
    var selectedItem by remember{mutableStateOf(items[0])}
    val expanded = remember { mutableStateOf(false) }
    val func: ()->Unit = {}

    Box() {
        Button(
            onClick = func,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            modifier = Modifier.fillMaxWidth()

        ) {
            Column( ) {
                Row(modifier = Modifier.align(Alignment.Start)){
                    Text(
                        text = title,
                        color = Color.LightGray,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Start
                    )
                }
                Row(modifier = Modifier.align(Alignment.Start)){
                    Text(
                        text = selectedItem,
                        modifier = Modifier
                            .clickable { expanded.value = true },
                        textAlign = TextAlign.Start
                        //.padding(16.dp)
                    )
                }

            }

        }
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            //modifier = Modifier.align(Alignment.CenterStart)
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        selectedItem = item
                        expanded.value = false
                    }
                ) {
                    Text(text = item)
                }
            }
        }
    }
}
