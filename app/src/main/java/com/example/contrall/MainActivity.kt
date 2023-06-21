package com.example.contrall

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.navigation.compose.rememberNavController
import com.example.contrall.ui.components.BottomBar
import com.example.contrall.ui.theme.ContrAllTheme


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter",
        "UnusedMaterial3ScaffoldPaddingParameter"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContrAllTheme {
                val navController = rememberNavController()
                ContrAllNavGraph(navController = navController)
            }
        }
    }
}

