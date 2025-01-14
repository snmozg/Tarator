package com.sozge.taratornew.components.tools

import android.graphics.Bitmap
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sozge.taratornew.utils.com.sozge.taratornew.utils.adjustDetails


@Composable
fun Details(
    detailValue: Float,
    displayBitmap: Bitmap?,
    bitmap: Bitmap?,
    onDetailChanged: (Float, Bitmap?) -> Unit
) {
    Text(
        text = "Adjust Details",
        fontSize = 20.sp,
        modifier = Modifier.padding(top = 16.dp)
    )
    Slider(
        value = detailValue,
        onValueChange = { newDetail ->
            onDetailChanged(newDetail, bitmap?.adjustDetails(newDetail))
        },
        valueRange = 1f..2f,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}