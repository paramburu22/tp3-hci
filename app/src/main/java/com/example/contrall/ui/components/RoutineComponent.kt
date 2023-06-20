package com.example.contrall.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contrall.R
import com.example.contrall.data.network.models.Action
import com.example.contrall.data.network.models.Device
import com.example.contrall.data.network.models.Routine
import com.example.contrall.data.network.models.RoutinesList
import com.example.contrall.util.DevicesViewModel
import com.example.contrall.util.RoutinesViewModel
import com.example.contrall.util.SharedDeviceModel
import retrofit2.http.Path

@Composable
fun RoutineComponent(routine : Routine, routinesViewModel: RoutinesViewModel) {
    val routinesUiState by routinesViewModel.uiState.collectAsState()
    Card(
        border = BorderStroke(2.dp, Color.LightGray),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.padding(16.dp),
        backgroundColor = Color.LightGray
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Routine Title
                Text(
                    text = "${routine.name}",
                    fontSize = 14.sp,
                )
                Button(
                    //execute all actions
                    onClick = { routine.id?.let { routinesViewModel.executeRoutine(it) } }, // ok?
                    modifier = Modifier.padding(end = 10.dp),

                    ) {
                    Image(
                        painter = painterResource(R.drawable.ic_baseline_play_circle_outline_24),
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
            //actions
            val actions = routine.actions
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(5.dp),
                modifier = Modifier
                    .padding(start = 4.dp, end = 4.dp, bottom = 4.dp, top = 25.dp)
                    .heightIn(min = 200.dp)
            ) {
                items(actions.size) { index ->
                    val action: Action = actions[index]
                    Divider()
                    Row(
                        modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = "${action.device?.name}",
                            fontSize = 30.sp
                        )
                    }
                    Row() {
                        Text(
                            text = "${action.actionName}",
                            fontSize = 20.sp,
                            color = Color.Gray
                        )
                        if(action.params.size > 0) {
                            Text(
                                text = "${action.params[0]}",
                                fontSize = 20.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ActionsComponent(routine : Routine){

}