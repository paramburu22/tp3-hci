package com.example.contrall.ui.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.contrall.R

@Composable
fun OurDropdownMenu(
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
    title: String
) {
    var expanded by remember { mutableStateOf(false) }

    Button(
        onClick = { expanded = true },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(5.dp)) {
            Text(
                text = title,
                color = Color.LightGray,
                fontSize = 15.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = selectedItem,
                    modifier = Modifier.clickable { expanded = true },
                    textAlign = TextAlign.Start
                )
                Icon(
                    painter = painterResource(
                        id = if (expanded) R.drawable.ic_baseline_arrow_drop_up_24
                        else R.drawable.ic_baseline_arrow_drop_down_24
                    ),
                    contentDescription = "Dropdown Icon",
                    modifier = Modifier.size(24.dp)
                        .padding(start = 4.dp)
                )
            }
        }
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
    ) {
        items.forEach { item ->
            DropdownMenuItem(
                onClick = {
                    onItemSelected(item)
                    expanded = false
                }
            ) {
                Text(text = item)
            }
        }
    }
}
