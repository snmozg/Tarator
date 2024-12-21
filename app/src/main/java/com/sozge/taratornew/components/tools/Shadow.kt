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
import com.sozge.taratornew.utils.adjustShadow

@Composable
fun Shadow(
    shadow: Float,
    displayBitmap: Bitmap?,
    bitmap: Bitmap?,
    onShadowChanged: (Float, Bitmap?) -> Unit,
) {
    Text(
        text = "Shadow",
        fontSize = 20.sp,
        style = MaterialTheme.typography.bodyMedium
    )
    Slider(
        value = shadow,
        onValueChange = { newShadow ->
            onShadowChanged(newShadow, bitmap?.adjustShadow(newShadow))
        },
        valueRange = 0f..1f,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}
