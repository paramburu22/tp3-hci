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

    object HomeScreen : Screen(R.string.home_title.toString(), R.drawable.home_icon, "home")

    object DevicesScreen : Screen(R.string.devices_title.toString(), R.drawable.devices_icon, "devices")

    object RoutinesScreen : Screen(R.string.routine_title.toString(), R.drawable.routine_icon, "routines")

    object DeviceScreen: Screen(R.string.device_title.toString(), R.drawable.outline_speaker_24,"device")

}

