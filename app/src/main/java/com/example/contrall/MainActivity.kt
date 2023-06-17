package com.example.contrall

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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
                Scaffold(

                    bottomBar = { BottomBar(navController = navController)}
                ) {
                    ContrAllNavGraph(navController = navController)
                }
            }
        }
    }
}



//@Composable
//fun BirthdayGreetingWithText(message: String, from: String, modifier: Modifier = Modifier)
// {
//     Column(
//         modifier = modifier.fillMaxSize(),
//         verticalArrangement = Arrangement.Top,
//         horizontalAlignment = Alignment.CenterHorizontally
//
//     ) {
//         Text(
//             text = message,
//             fontSize = 36.sp,
//             modifier = Modifier
//                 .padding(top = 16.dp)
//         )
//         Text(
//             text = from,
//             fontSize = 24.sp,
//             modifier = Modifier
//                 .padding(top = 16.dp, end = 16.dp)
//                 .align(alignment = Alignment.End)
//         )
//     }
//
// }
//
//@Composable
//fun BirthdayGreetingWithImage(message: String, from: String, modifier: Modifier = Modifier) {
//    val image = painterResource(R.drawable.background)
//    Box {
//        Image(
//            painter = image,
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .fillMaxSize()
//        )
//        BirthdayGreetingWithText(message = message, from = from)
//    }
//}



@Preview(showBackground = false)
@Composable
private fun ContrAllPreview() {
    ContrAllTheme {
    }
}

