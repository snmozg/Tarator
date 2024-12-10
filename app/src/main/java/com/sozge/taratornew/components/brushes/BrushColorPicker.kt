package com.sozge.taratornew.components.brushes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BrushColorPicker(selectedColor: State<Color>, onColorSelected: (Color) -> Unit) {

    val colors = listOf(Color.Black, Color.Red, Color.Green, Color.Blue, Color.Yellow)

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        colors.forEach { color ->
            Button(
                onClick = { onColorSelected(color) },
                colors = ButtonDefaults.buttonColors(containerColor = color),
                shape = CircleShape,
                modifier = Modifier.padding(4.dp)
            ) {
                // Empty content as we are using button as color picker
            }
        }
    }
}
