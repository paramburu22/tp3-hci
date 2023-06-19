package com.example.contrall.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.ui.unit.dp
import com.example.contrall.ui.components.DeviceComponent
import androidx.compose.ui.tooling.preview.Preview
import com.example.contrall.ui.components.TopAppBar

@Composable
@Preview
fun DeviceScreen(imageResId: Int = 2) {
    val painter = painterResource(imageResId)
    val sections = (0 until 25).toList()


    Scaffold(
        topBar = {
            TopAppBar(title = "Dispositivos", showIcon = false)
        },
        content = {
            Box(
                modifier = Modifier
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
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(5.dp),
                    content = {
                        items(sections.size) { index ->
                            DeviceComponent()
                        }
                    },
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    )
}