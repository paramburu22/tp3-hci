package com.example.contrall

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap

sealed class Screen(var title: String, var iconResId: Int, val route: String) {
    // Declarar los objetos sin inicializarlos
    object HomeScreen : Screen("", 0, "home")
    object DevicesScreen : Screen("", 0, "devices")
    object RoutinesScreen : Screen("", 0, "routines")
    object DeviceScreen : Screen("", 0, "device")
}

@Composable
fun setupScreens() {
    val homeTitle = stringResource(R.string.home_title)
    val devicesTitle = stringResource(R.string.devices_title)
    val routineTitle = stringResource(R.string.routine_title)
    val deviceTitle = stringResource(R.string.device_title)

    Screen.HomeScreen.title = homeTitle
    Screen.HomeScreen.iconResId = R.drawable.home_icon

    Screen.DevicesScreen.title = devicesTitle
    Screen.DevicesScreen.iconResId = R.drawable.devices_icon

    Screen.RoutinesScreen.title = routineTitle
    Screen.RoutinesScreen.iconResId = R.drawable.routine_icon

    Screen.DeviceScreen.title = deviceTitle
    Screen.DeviceScreen.iconResId = R.drawable.outline_speaker_24
}
