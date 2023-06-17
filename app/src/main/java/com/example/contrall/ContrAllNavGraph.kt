package com.example.contrall

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.contrall.ui.components.SpeakerScreen
import com.example.contrall.ui.screens.DeviceScreen
import com.example.contrall.ui.screens.HomeScreen
import com.example.contrall.ui.screens.RoutineScreen
import com.example.contrall.util.SpeakerViewModel

@Composable
fun ContrAllNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.FirstScreen.route
    ) {
        composable(Screen.FirstScreen.route) {
            SpeakerScreen(SpeakerViewModel(),  R.drawable.background)
        }
        composable(Screen.SecondScreen.route) {
            DeviceScreen(R.drawable.background)
        }
        composable(Screen.ThirdScreen.route) {
            RoutineScreen(R.drawable.background)
        }
    }
}