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
import com.sozge.taratornew.utils.adjustVignette

@Composable
fun Vignette(
    vignetteIntensity: Float,
    displayBitmap: Bitmap?,
    bitmap: Bitmap?,
    onVignetteChanged: (Float, Bitmap?) -> Unit
){
        Text(
            text = "Vignette",
            fontSize = 20.sp, style = MaterialTheme.typography.bodyMedium)
        Slider(
            value = vignetteIntensity,
            onValueChange = { newVignette ->
                onVignetteChanged(newVignette, bitmap?.adjustVignette(newVignette))
            },
            valueRange = 0f..1f,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }

