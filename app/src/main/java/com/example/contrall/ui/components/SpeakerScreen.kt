package com.example.contrall.ui.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.End
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import com.example.contrall.R
import com.example.contrall.data.DropdownClass
import com.example.contrall.data.SongInfo
import com.example.contrall.ui.components.TopAppBar
import kotlinx.coroutines.launch

import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.util.Timer
import java.util.TimerTask

@Composable
fun SpeakerScreen(
    speakerViewModel: SpeakerViewModel,
    navController: NavController
) {
    val speakerUiState by speakerViewModel.uiState.collectAsState()
    val painter = painterResource(R.drawable.background)

    var showPlaylist by remember { mutableStateOf(false) }

    val timer = Timer()

    val task = object : TimerTask() {
        override fun run() {
            speakerViewModel.updateState()
        }
    }

    val lifecycleObserver = LifecycleEventObserver { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                timer.scheduleAtFixedRate(task, 0L, 5000L)
            }
            Lifecycle.Event.ON_STOP -> {
                task.cancel()
            }
            else -> Unit
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
            timer.cancel()
        }
    }


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
                Card (
                    modifier = Modifier.padding(16.dp),
                    border = BorderStroke(2.dp, Color.LightGray),
                    shape = RoundedCornerShape(15.dp),
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
                            speakerUiState.name?.let { it1 ->
                                Text(
                                    text = it1,
                                    fontSize = 30.sp,
                                )
                            }
                        }

                        Divider(modifier = Modifier.fillMaxWidth())

                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_library_music_24),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(48.dp)
                                    .padding(end = 12.dp)
                                    .align(Alignment.CenterVertically)
                            )
                            if(speakerUiState.state.song == null || speakerUiState.state.status == "stopped") {
                                Text(
                                    text = stringResource(R.string.no_song),
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(5.dp)
                                )
                            } else {
                                Column(
                                    modifier = Modifier
                                        .padding(10.dp)
                                ) {
                                    Row(modifier = Modifier.fillMaxWidth()) {
                                        BoxWithConstraints(modifier = Modifier.weight(0.8f)) {
                                            speakerUiState.state.song!!.title?.let { it1 ->
                                                Text(
                                                    text = it1,
                                                    fontSize = 22.sp,
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                )
                                            }

                                        }
                                        speakerUiState.state.song!!.progress?.let { it1 ->
                                            Text(text = it1, fontSize = 14.sp,
//                                            modifier = Modifier.align(alignment = Alignment.End)
                                            )
                                        }

                                    }
                                    speakerUiState.state.song!!.artist?.let { it1 ->
                                        Text(
                                            text = it1,
                                            fontSize = 18.sp,
                                        )
                                    }
                                    speakerUiState.state.song!!.album?.let { it1 ->
                                        Text(
                                            text = "${stringResource(R.string.from_album)} $it1",
                                            fontSize = 16.sp,
                                        )
                                    }
                                }
                            }
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

                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${stringResource(R.string.volume)}: ${speakerUiState.state.volume}",
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
                                    contentDescription = "Plus"
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
                                OurDropdownMenu(items = listOf(
                                    DropdownClass("classical", stringResource(R.string.classical)),
                                    DropdownClass("country", stringResource(R.string.country)),
                                    DropdownClass("dance", stringResource(R.string.dance)),
                                    DropdownClass("latina", stringResource(R.string.latina)),
                                    DropdownClass("pop", stringResource(R.string.pop)),
                                    DropdownClass("rock", stringResource(R.string.rock))
                                ),
                                    selectedItem = it1,
                                    onItemSelected = speakerViewModel::changeGenre,
                                    title = stringResource(R.string.sel_genre))
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
                                Text(text = stringResource(R.string.playlist))
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
            speakerViewModel.getPlaylist()
            speakerUiState.playlist?.let {
                PlaylistDialog(
                    open = showPlaylist,
                    onClose = { showPlaylist = false },
                    genre = when (speakerUiState.state.genre) {
                        "classical" -> stringResource(R.string.classical)
                        "country" -> stringResource(R.string.country)
                        "dance" -> stringResource(R.string.dance)
                        "latina" -> stringResource(R.string.latina)
                        "pop" -> stringResource(R.string.pop)
                        else -> {stringResource(R.string.rock)}
                    },
                    playlist = it
                )
            }
        }
}


