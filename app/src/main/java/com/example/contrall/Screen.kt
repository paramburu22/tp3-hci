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


sealed class Screen(val title: String, val iconResId: Int, val route: String) {

    object HomeScreen : Screen("Inicio", R.drawable.home_icon, "home")

    object DevicesScreen : Screen("Dispositivos", R.drawable.devices_icon, "devices")

    object RoutinesScreen : Screen("Rutinas", R.drawable.routine_icon, "routines")

    object DeviceScreen: Screen("Dispositivo", R.drawable.outline_speaker_24,"device")

}

