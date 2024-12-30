package com.sozge.taratornew.components.brushes

import BottomSheetViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
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
fun BrushColorPicker(
    selectedColor: State<Color>,
    bottomSheetViewModel: BottomSheetViewModel,
    onColorSelected: (Color) -> Unit,
) {

    val colors = listOf(Color.Black, Color.Red, Color.Blue, Color.Green, Color.Yellow)


    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        colors.forEach { color ->
            Button(
                onClick = {
                    onColorSelected(color)
                    bottomSheetViewModel.closeColorSheet()
                },
                colors = ButtonDefaults.buttonColors(containerColor = color),
                shape = CircleShape,
                modifier = Modifier.padding(5.dp)
            ) {

            }
        }
    }
}