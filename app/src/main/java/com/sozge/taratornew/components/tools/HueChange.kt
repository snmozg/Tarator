package com.sozge.taratornew.components.tools

import android.graphics.Bitmap
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sozge.taratornew.utils.applyHue

@Composable
fun HueChange(
    hueValue: Float,
    displayBitmap: Bitmap?,
    bitmap: Bitmap?,
    onHueChanged: (Float, Bitmap?) -> Unit
) {
    Text(
        text = "Change Hue",
        fontSize = 20.sp,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(top = 16.dp)
    )
    Slider(
        value = hueValue,
        onValueChange = { newHue ->
            onHueChanged(newHue, bitmap?.applyHue(newHue))
        },
        valueRange = 0f..360f, // Renk tonu 0-360 arasında değişir
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}

