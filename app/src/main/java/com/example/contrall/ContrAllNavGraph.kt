package com.example.contrall

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.contrall.ui.screens.DeviceScreen
import com.example.contrall.ui.screens.RoutineScreen

@Composable
fun ContrAllNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            DeviceScreen(R.drawable.background)
        }
        composable(Screen.DevicesScreen.route) {
            DeviceScreen(R.drawable.background)
        }
        composable(Screen.RoutinesScreen.route) {
            RoutineScreen(R.drawable.background)
        }
    }
}