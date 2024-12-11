package com.sozge.taratornew.components.tools

import android.graphics.Bitmap
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sozge.taratornew.utils.adjustBrightness

@Composable
fun Brightness(
    brightness: Float,
    displayBitmap: Bitmap?,
    bitmap: Bitmap?,
    onBrightnessChanged: (Float, Bitmap?) -> Unit
) {
    Text(text = "Brightness", style = MaterialTheme.typography.bodyMedium)
    Slider(
        value = brightness,
        onValueChange = { newBrightness ->
            onBrightnessChanged(newBrightness, bitmap?.adjustBrightness(newBrightness))
        },
        valueRange = 0f..4f,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}
