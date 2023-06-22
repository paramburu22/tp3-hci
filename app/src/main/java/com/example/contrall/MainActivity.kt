package com.example.contrall

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.contrall.ui.theme.ContrAllTheme
import com.example.contrall.ui.theme.rememberWindowSizeClass


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter",
        "UnusedMaterial3ScaffoldPaddingParameter"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val window = rememberWindowSizeClass()
            ContrAllTheme(window) {
                setupScreens()
                val navController = rememberNavController()
                ContrAllNavGraph(navController = navController)
            }
        }
    }
}

