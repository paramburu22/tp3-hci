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

    object HomeScreen : Screen("Inicio", R.drawable.home_icon, "home_screen")

    object DevicesScreen : Screen("Dispositivos", R.drawable.devices_icon, "devices_screen")

    object RoutinesScreen : Screen("Rutinas", R.drawable.routine_icon, "routines_screen")

    object AirConditionerScreen : Screen("Aire Acondicionado", R.drawable.ic_baseline_ac_unit_24, "ac_screen/{id}")

    object DoorScreen : Screen("Puerta", R.drawable.outline_sensor_door_24, "door_screen/{id}")

    object LampScreen: Screen("Lampara", R.drawable.ic_baseline_lightbulb_24,"lamp_screen/{id}")

    object OvenScreen: Screen("Oven", R.drawable.baseline_cookie_24,"oven_screen/{id}")

    object SpeakerScreen: Screen("Speaker", R.drawable.outline_speaker_24,"speaker_screen/{id}")

}

