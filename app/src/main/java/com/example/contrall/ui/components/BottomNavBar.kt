package com.example.contrall.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.contrall.Screen

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        Screen.HomeScreen,
        Screen.DevicesScreen,
        Screen.RoutinesScreen
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background, // Set the background color of the BottomNavigation
        contentColor = MaterialTheme.colors.primary // Set the color of the BottomNavigation icons and text
    ) {
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon( painterResource(screen.iconResId), contentDescription = screen.title) },
                label = { Text(text = screen.title) },
                alwaysShowLabel = true,
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}