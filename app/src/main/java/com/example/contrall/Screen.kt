package com.example.contrall

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val title: String, val icon: ImageVector, val route: String) {

    object FirstScreen : Screen("Home", Icons.Filled.Home, "first_screen")

    object SecondScreen : Screen("Devices", Icons.Filled.Favorite, "second_screen")

    object ThirdScreen : Screen("Routines", Icons.Filled.Face, "third_screen")
}

