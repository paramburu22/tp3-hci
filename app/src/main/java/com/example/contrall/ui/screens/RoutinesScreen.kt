package com.example.contrall.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contrall.R
import com.example.contrall.data.network.models.Device
import com.example.contrall.data.network.models.DevicesList
import com.example.contrall.data.network.models.Routine
import com.example.contrall.data.network.models.RoutinesList
import com.example.contrall.ui.components.DeviceComponent
import com.example.contrall.ui.components.RoutineComponent
import com.example.contrall.ui.components.TopAppBar
import com.example.contrall.util.DevicesViewModel
import com.example.contrall.util.RoutinesViewModel
import com.example.contrall.util.SharedDeviceModel

//@Preview
@Composable
fun RoutinesScreen(routinesViewModel: RoutinesViewModel) {
    val painter = painterResource(R.drawable.background)
    val routinesUiState by routinesViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        routinesViewModel.getRoutines()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = "Rutinas", showIcon = false)
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(5.dp),
                    modifier = Modifier.padding(start = 4.dp, end = 4.dp, bottom = 4.dp, top = 25.dp).heightIn(min = 200.dp)
                ) {
                    val myRoutines : RoutinesList? = routinesUiState.routines
                    if (myRoutines != null) {
                        items(myRoutines.routines.size) { index ->
                            val routine : Routine = myRoutines.routines[index]
                            RoutineComponent(routine, routinesViewModel)
                        }
                    }
                }


            }
        }
    )
}
