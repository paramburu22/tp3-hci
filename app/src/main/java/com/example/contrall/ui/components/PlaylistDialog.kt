package com.example.contrall.ui.components

import android.content.ClipData
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.contrall.R
import com.example.contrall.data.SongInfo
import com.example.contrall.data.SpeakerUiState

@Composable
fun PlaylistDialog(
    open: Boolean,
    onClose: () -> Unit,
    genre: String,
    playlist: List<SongInfo>
) {
    if (open) {
        Dialog(
            onDismissRequest = { onClose() }
        ){
                Card(
                    modifier = Modifier.width(500.dp),
                    elevation = 8.dp
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Lista de reproduccion de ${genre}",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        LazyColumn (content = {
                            items(playlist) { info ->
                                SongItem(info)
                                Divider()
                            }
                        })
                        Row(
                            modifier = Modifier.padding(top = 16.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Button(onClick = { onClose() }) {
                                Text(text = "Cerrar")
                            }
                        }
                    }
                }
            }

    }
}
@Composable
fun SongItem(info: SongInfo) {
    Row(modifier = Modifier.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_library_music_24),
            contentDescription = "Song Icon",
            tint = Color.Gray,
            modifier = Modifier.size(49.dp)
        )
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                BoxWithConstraints(modifier = Modifier.weight(0.8f)) {
                    info.title?.let { Text(text = it, style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .fillMaxWidth()) }
                }
                info.duration?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.End
                    )
                }
            }
            info.artist?.let { Text(text = it, style = MaterialTheme.typography.body2) }
            Text(text = "Del album ${info.album}", style = MaterialTheme.typography.body2)
        }
        info.duration?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End
            )
        }
    }
}