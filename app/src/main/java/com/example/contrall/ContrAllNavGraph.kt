package com.example.contrall

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.contrall.ui.screens.DeviceScreen
import com.example.contrall.ui.screens.HomeScreen
import com.example.contrall.ui.screens.RoutineScreen

@Composable
fun ContrAllNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.FirstScreen.route
    ) {
        composable(Screen.FirstScreen.route) {
            HomeScreen()
        }
        composable(Screen.SecondScreen.route) {
            DeviceScreen()
        }
        composable(Screen.ThirdScreen.route) {
            RoutineScreen()
        }
    }
}