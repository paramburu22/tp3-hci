package com.example.contrall

import androidx.compose.runtime.Composable
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
import com.example.contrall.util.SpeakerViewModel

@Composable
fun ContrAllNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen()
        }
        composable(Screen.DevicesScreen.route) {
            DevicesScreen(DevicesViewModel())
        }
        composable(Screen.RoutinesScreen.route) {
            RoutinesScreen()
        }
        composable(
            route = Screen.AirConditionerScreen.route,
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
            })
        ) {
            AirConditionerScreen(AirConditionerViewModel())
        }
        composable(
            route = Screen.DoorScreen.route,
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
            })
        ) {
            DoorScreen(DoorViewModel())
        }
        composable(
            route = Screen.LampScreen.route,
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
            })
        ) {
            LampScreen(LampViewModel())
        }
        composable(
            route = Screen.OvenScreen.route,
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
            })
        ) {
            OvenScreen(OvenViewModel())
        }
        composable(
            route = Screen.SpeakerScreen.route,
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
            }),
        ) {
            SpeakerScreen(SpeakerViewModel())
        }

    }
}