package com.sozge.taratornew.components.tools


import android.graphics.Bitmap
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.sozge.taratornew.utils.adjustBrightness

@Composable
fun Brightness(
    brightness: Float,
    displayBitmap: Bitmap?,
    bitmap: Bitmap?,
    onBrightnessChanged: (Float, Bitmap?) -> Unit,

    ) {

    Text(
        text = "Brightness",
        fontSize = 20.sp,
        style = MaterialTheme.typography.bodyMedium,
    )
    Slider(
        value = brightness,
        onValueChange = { newBrightness ->
            onBrightnessChanged(newBrightness, bitmap?.adjustBrightness(newBrightness))
        },
        valueRange = 0.8f..1.1f,
        modifier = Modifier.fillMaxWidth()
    )
}