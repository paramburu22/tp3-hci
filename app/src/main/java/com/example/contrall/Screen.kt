package com.example.contrall

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource


sealed class Screen(val title: String,val iconResId: Int, val route: String) {

    object FirstScreen : Screen("Inicio",R.drawable.home_icon, "first_screen")

    object SecondScreen : Screen("Dispositivos", R.drawable.devices_icon, "second_screen")

    object ThirdScreen : Screen("Rutinas",R.drawable.routine_icon, "third_screen")
}

