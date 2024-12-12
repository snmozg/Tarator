package com.sozge.taratornew.components.tools

import android.graphics.Bitmap
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sozge.taratornew.utils.rotateImage


@Composable
fun Rotate(
    rotationAngle: Float,
    displayBitmap: Bitmap?,
    bitmap: Bitmap?,
    onRotateChanged: (Float, Bitmap?) -> Unit
) {
Text(text = "Rotate Image", style = MaterialTheme.typography.bodyMedium)
Slider(
value = rotationAngle,
onValueChange = { newAngle ->
    onRotateChanged(newAngle, bitmap?.rotateImage(newAngle))
},
valueRange = 0f..360f,
modifier = Modifier.padding(horizontal = 16.dp)
) }