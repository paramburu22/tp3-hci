package com.example.contrall.ui.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Switch
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TextButton
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.contrall.util.SpeakerViewModel
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contrall.R
import com.example.contrall.data.SongInfo
import com.example.contrall.ui.components.TopAppBar

import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun SpeakerScreen(
    speakerViewModel: SpeakerViewModel,
    navController: NavController
) {
    val speakerUiState by speakerViewModel.uiState.collectAsState()
    val painter = painterResource(R.drawable.background)

    var showPlaylist by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
                 TopAppBar(navController)
        }, content = {
            Box( modifier = Modifier
                .fillMaxSize()
                .padding(it),
                contentAlignment = (Alignment.Center),
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
                androidx.compose.material3.Card(
                    //onClick = func,
                    modifier = Modifier
                        .padding(25.dp, 0.dp),
                    border = BorderStroke(2.dp, Color.LightGray),
                    shape = RoundedCornerShape(15.dp),
                    //elevation = 2.dp
                ) {

                    // Your UI content goes here
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(20.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                painter = painterResource(R.drawable.outline_speaker_24),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(48.dp)
                                    .padding(end = 12.dp)
                                    .align(Alignment.CenterVertically)
                            )
                            // Door Title
                            speakerUiState.name?.let { it1 ->
                                androidx.compose.material.Text(
                                    text = it1,
                                    fontSize = 30.sp,
                                )
                            }
                        }

                        Divider(modifier = Modifier.fillMaxWidth())

                        // Songs
                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_library_music_24),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(48.dp)
                                    .padding(end = 12.dp)
                                    .align(Alignment.CenterVertically))
                            Text(
                                text = "No hay canciones en reproduccion",
                                fontSize = 18.sp,
                                modifier = Modifier.padding(5.dp)
                                )
                        }
                        Row(
                            modifier = Modifier
                                .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            IconButton(onClick = { speakerViewModel.previousSong() }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_skip_previous_24),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(48.dp)
                                        .padding(end = 12.dp)
                                        .align(Alignment.CenterVertically))
                            }

                            IconButton(onClick = { when (speakerUiState.state.status) {
                                "stopped" -> speakerViewModel.play()
                                "paused" -> speakerViewModel.resume()
                                else -> speakerViewModel.pause()
                            }

                            }) {
                                Icon(
                                    painter = if (speakerUiState.state.status == "playing") {
                                        painterResource(id = R.drawable.baseline_pause_24)
                                    } else {
                                        painterResource(id = R.drawable.baseline_play_arrow_24)
                                    },
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(48.dp)
                                        .padding(end = 12.dp)
                                        .align(Alignment.CenterVertically))
                            }

                            IconButton(onClick = { speakerViewModel.stop() }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_stop_24),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(48.dp)
                                        .padding(end = 12.dp)
                                )
                            }

                            IconButton(onClick = { speakerViewModel.nextSong() }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_skip_next_24),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(48.dp)
                                        .padding(end = 12.dp)
                                        .align(Alignment.CenterVertically))
                            }

                        }

                        Divider(modifier = Modifier.fillMaxWidth())

                        //volumen
                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Volumen: ${speakerUiState.state.volume}",
                                fontSize = 18.sp,
                            )
                        }
                        Row(
                            modifier = Modifier
                                .padding(bottom = 10.dp, start = 10.dp, end = 10.dp, top = 5.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = { speakerViewModel.setVolume(speakerUiState.state.volume!!.toFloat() - 1) },
                                modifier = Modifier.size(30.dp)) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_round_remove_24),
                                    contentDescription = "Minus"
                                )
                            }
                            Slider(
                                value = speakerUiState.state.volume!!.toFloat(),
                                onValueChange = { value -> speakerViewModel.setVolume(value.toInt().toFloat()) },
                                valueRange = 0f..10f,
                                steps = 1,
                                colors = SliderDefaults.colors(
                                    thumbColor = Color(android.graphics.Color.parseColor("#146C94")),
                                    activeTrackColor = Color(android.graphics.Color.parseColor("#146C94")),
                                    inactiveTrackColor = Color.LightGray
                                ),
                                modifier = Modifier
                                    .padding(start = 10.dp, end = 10.dp)
                                    .width(260.dp)
                            )
                            IconButton(
                                onClick = { speakerViewModel.setVolume(speakerUiState.state.volume!!.toFloat() + 1) },
                                modifier = Modifier.size(30.dp)) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_round_add_24),
                                    contentDescription = "Minus"
                                )
                            }
                        }

                        Divider(modifier = Modifier.fillMaxWidth())

                        Row(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            speakerUiState.state.genre?.let { it1 ->
                                OurDropdownMenu(items = listOf("clasica",
                                    "country",
                                    "dance",
                                    "latina",
                                    "pop",
                                    "rock"),
                                    selectedItem = it1,
                                    onItemSelected = speakerViewModel::changeGenre,
                                    title = "Seleccione un genero")
                            }
                        }
                        Row(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextButton(
                                onClick = { showPlaylist = true },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
                            ) {
                                Text(text = "LISTA DE REPRODUCCION")
                            }
                        }

                        // Trash Bin Image
                        Image(
                            painter = painterResource(R.drawable.ic_baseline_delete_outline_24),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    )

        if (showPlaylist) {
            PlaylistDialog(
                open = showPlaylist,
                onClose = { showPlaylist = false },
                genre = speakerUiState.state.genre!!,
                playlist = mutableStateListOf(
                    SongInfo(title = "Style", artist = "Taylor Swift", album = "1989", duration = "3:30", progress = ""),
                    SongInfo(title = "Mean", artist = "Taylor Swift", album = "Speak Now", duration = "4:15", progress = ""),
                    SongInfo(title = "August", artist = "Taylor Swift", album = "Folklore", duration = "2:45", progress = "")
                )
            )
        }
}


