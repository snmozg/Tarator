package com.sozge.taratornew.components.tools

import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.sozge.taratornew.utils.adjustBlur

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun Blur(
    blurRadius: Float,
    displayBitmap: Bitmap?,
    bitmap: Bitmap?,
    onBlurChanged: (Float, Bitmap?) -> Unit,
) {
    val context = LocalContext.current

    Text(
        text = "Blur",
        fontSize = 20.sp,
        style = MaterialTheme.typography.bodyMedium,
    )
    Slider(
        value = blurRadius,
        onValueChange = { newRadius ->
            onBlurChanged(newRadius, bitmap?.adjustBlur(context, newRadius))
        },
        valueRange = 0f..25f, // Android'de maksimum blur değeri genelde 25f
        modifier = Modifier.fillMaxWidth()
    )
}

