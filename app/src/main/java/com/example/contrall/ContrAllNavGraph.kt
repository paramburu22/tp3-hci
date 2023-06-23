package com.example.contrall

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
import com.example.contrall.util.RecentsViewModel
import com.example.contrall.util.RoutinesViewModel
import com.example.contrall.util.SharedDeviceViewModel
import com.example.contrall.util.SpeakerViewModel

@Composable
fun ContrAllNavGraph(navController: NavHostController, mainActivity: MainActivity, id: String?) {
    val sharedDeviceViewModel: SharedDeviceViewModel = viewModel()
    val devicesModel = DevicesViewModel(mainActivity)
    val routinesModel = RoutinesViewModel()
    val recentsModel = RecentsViewModel()

    var currId = id;

    devicesModel.setMainActivity(mainActivity)


    NavHost(
        navController = navController,
        startDestination =
        if(currId == null) {
            Screen.HomeScreen.route
        } else {
            Screen.DeviceScreen.route
        }
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(recentsModel, navController, sharedDeviceViewModel)
        }
        composable(Screen.DevicesScreen.route) {
            DevicesScreen(devicesModel, navController, sharedDeviceViewModel, recentsModel)
        }
        composable(Screen.RoutinesScreen.route) {
            RoutinesScreen(routinesModel, navController)
        }
        composable(Screen.DeviceScreen.route) {
            if(currId != null) {
                sharedDeviceViewModel.getDevice(currId!!);
                currId = null
            }
            when(sharedDeviceViewModel.device?.type?.name) {
                "lamp" -> LampScreen(LampViewModel(sharedDeviceViewModel.device!!,devicesModel), navController)
                "oven" -> OvenScreen(OvenViewModel(sharedDeviceViewModel.device!!,devicesModel), navController)
                "ac" -> AirConditionerScreen(AirConditionerViewModel(sharedDeviceViewModel.device!!,devicesModel), navController)
                "speaker" -> SpeakerScreen(SpeakerViewModel(sharedDeviceViewModel.device!!,devicesModel), navController)
                "door" -> DoorScreen(DoorViewModel(sharedDeviceViewModel.device!!,devicesModel), navController)
            }
        }
    }
}