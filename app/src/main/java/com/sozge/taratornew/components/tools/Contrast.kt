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
import com.sozge.taratornew.utils.adjustContrast

@Composable
fun Contrast(
    contrast: Float,
    displayBitmap: Bitmap?,
    bitmap: Bitmap?,
    onContrastChanged: (Float, Bitmap?) -> Unit,

    ) {
    Text(
        text = "Contrast",
        fontSize = 20.sp,
        style = MaterialTheme.typography.bodyMedium)
    Slider(
        value = contrast,
        onValueChange = { newContrast ->
            onContrastChanged(newContrast, bitmap?.adjustContrast(newContrast))
        },
        valueRange = 0.9f..1.2f,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}