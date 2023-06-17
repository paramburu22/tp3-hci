package com.example.contrall.ui.components

import android.widget.Toast
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun DevicesTopAppBar() {

    val contextForToast = LocalContext.current.applicationContext
    TopAppBar(
        title = {
            Text(text = "Device")
        },
        navigationIcon = {
            IconButton(onClick = {
                Toast.makeText(contextForToast, "Back Icon Click", Toast.LENGTH_SHORT)
                    .show()
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go Back")
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
    )
}