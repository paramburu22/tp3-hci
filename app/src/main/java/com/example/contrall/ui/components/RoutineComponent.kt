package com.example.contrall.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import com.example.contrall.R
import com.example.contrall.data.network.models.Action
import com.example.contrall.data.network.models.Routine
import com.example.contrall.data.network.models.RoutinesList
import com.example.contrall.util.RoutinesViewModel

@Composable
fun RoutineComponent(routine : Routine, routinesViewModel: RoutinesViewModel) {
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
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${routine.name}",
                    fontSize = 26.sp,
                    modifier = Modifier.padding(end = 16.dp),
                )
                IconButton(onClick = { routine.id?.let { routinesViewModel.executeRoutine(it) } }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_play_circle_outline_24),
                        contentDescription = null,
                        modifier = Modifier
                            .size(48.dp)
                            .padding(end = 12.dp)
                    )
                }

            }
            val myActions: List<Action> = routine.actions
            for (action in myActions) {
                Divider()
                Row(
                    modifier = Modifier.padding(top = 10.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "${action.device?.name}",
                        fontSize = 24.sp
                    )
                    Row() {
                        Text(
                            text = "${action.actionName}",
                            fontSize = 20.sp,
                            color = Color.Gray
                        )
                        if(action.params.size > 0) {
                            Text(
                                text = " - ${action.params[0]}",
                                fontSize = 20.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
                Row(modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth()) {
                    Text(
                        text = "${action.device?.room?.home?.name} - ${action.device?.room?.name}",
                        fontSize = 16.sp,
                        color = Color.Gray,
                    )
                }
                Divider()
            }
        }
    }
}


