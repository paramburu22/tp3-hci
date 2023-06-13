package com.example.contrall.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.contrall.util.SpeakerViewModel
import androidx.compose.material3.Text

@Composable
fun SpeakerScreen(speakerViewModel: SpeakerViewModel = viewModel()) {
    val speakerUiState by speakerViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = speakerUiState.name
        )
    }
}