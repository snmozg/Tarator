package com.sozge.taratornew.components.tools

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Contrast() {
    var contrast by remember { mutableStateOf(1f) }

    Text(text = "Contrast", style = MaterialTheme.typography.bodyMedium)
    Slider(
    value = contrast,
    onValueChange =
    { contrast = it },
    valueRange = 0f..10f,
    modifier = Modifier.padding(horizontal = 16.dp)
    )

}