package com.sozge.taratornew.components.tools

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sozge.taratornew.models.ImageViewModel
import com.sozge.taratornew.utils.adjustBrightness
import com.sozge.taratornew.utils.com.sozge.taratornew.utils.bitmapToUri
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

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
        valueRange = 0f..4f,
        modifier = Modifier.fillMaxWidth()
    )
}