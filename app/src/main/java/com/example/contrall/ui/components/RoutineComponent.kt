package com.example.contrall.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contrall.R
import com.example.contrall.data.network.models.Action
import com.example.contrall.data.network.models.Routine
import com.example.contrall.util.RoutinesViewModel

@Composable
fun RoutineComponent(routine : Routine, routinesViewModel: RoutinesViewModel) {
    val actionsNames : Map<String, String> = mapOf(
        "setVolume" to stringResource(R.string.volume),
        "play" to stringResource(R.string.play),
        "stop" to stringResource(R.string.stop),
        "pause" to stringResource(R.string.pause),
        "resume" to stringResource(R.string.resume),
        "nextSong" to stringResource(R.string.next_song),
        "previousSong" to stringResource(R.string.prev_song),
        "setGenre" to stringResource(R.string.genre),
        "turnOn" to stringResource(R.string.turn_on),
        "turnOff" to stringResource(R.string.turn_off),
        "setColor" to stringResource(R.string.color),
        "setBrightness" to stringResource(R.string.brightness),
        "setTemperature" to stringResource(R.string.temp),
        "setHeat" to stringResource(R.string.heat),
        "setGrill" to stringResource(R.string.grill),
        "setConvection" to stringResource(R.string.convection),
        "setMode" to stringResource(R.string.mode_routine),
        "setVerticalSwing" to stringResource(R.string.v_swing),
        "setHorizontalSwing" to stringResource(R.string.h_swing),
        "setFanSpeed" to stringResource(R.string.fan_speed),
        "open" to stringResource(R.string.open_routine),
        "close" to stringResource(R.string.close_routine),
        "lock" to stringResource(R.string.lock_routine),
        "unlock" to stringResource(R.string.unlock_routine),

    )
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
                            .padding(end = 12.dp),
                        tint = Color(android.graphics.Color.parseColor("#146C94"))
                    )
                }

            }
            val myActions: List<Action> = routine.actions
            for (action in myActions) {
                Divider()
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 15.dp)
                        .fillMaxWidth()
                ) {
                    Column {
                        Text(
                            text = "${action.device?.name}",
                            fontSize = 18.sp
                        )
                        Text(
                            text = "${action.device?.room?.home?.name} - ${action.device?.room?.name}",
                            fontSize = 16.sp,
                            color = Color.Gray,
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Text(
                            text = "${actionsNames.get(action.actionName)}",
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                        if(action.params.isNotEmpty()) {
                            Text(
                                text = ": ${action.params[0]}",
                                fontSize = 16.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
                Divider()
            }
        }
    }
}


