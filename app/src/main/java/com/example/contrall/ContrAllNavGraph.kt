package com.example.contrall

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.contrall.ui.components.AirConditionerScreen
import com.example.contrall.ui.components.DoorScreen
import com.example.contrall.ui.components.LampScreen
import com.example.contrall.ui.components.OvenScreen
import com.example.contrall.ui.components.SpeakerScreen
import com.example.contrall.ui.screens.DevicesScreen
import com.example.contrall.ui.screens.HomeScreen
import com.example.contrall.ui.screens.RoutinesScreen
import com.example.contrall.util.AirConditionerViewModel
import com.example.contrall.util.DevicesViewModel
import com.example.contrall.util.DoorViewModel
import com.example.contrall.util.LampViewModel
import com.example.contrall.util.OvenViewModel
import com.example.contrall.util.SharedDeviceModel
import com.example.contrall.util.SpeakerViewModel

@Composable
fun ContrAllNavGraph(navController: NavHostController) {
    val sharedDeviceModel: SharedDeviceModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen()
        }
        composable(Screen.DevicesScreen.route) {
            DevicesScreen(DevicesViewModel(), navController, sharedDeviceModel)
        }
        composable(Screen.RoutinesScreen.route) {
            RoutinesScreen()
        }
        composable(Screen.DeviceScreen.route) {
            when(sharedDeviceModel.device?.type?.name) {
                "lamp" -> LampScreen(LampViewModel())
                "oven" -> OvenScreen(OvenViewModel())
                "ac" -> AirConditionerScreen(AirConditionerViewModel())
                "speaker" -> SpeakerScreen(SpeakerViewModel())
                "door" -> DoorScreen(DoorViewModel())
            }
        }
    }
}