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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.contrall.R

@Composable
fun TopAppBar(
    navController: NavController = rememberNavController(),
    title: String = stringResource(R.string.device_title),
    showIcon: Boolean = true,
) {

    val contextForToast = LocalContext.current.applicationContext
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = if (showIcon) {
            {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go Back")
                }
            }
        } else null,
        backgroundColor = MaterialTheme.colors.primary,
    )
}