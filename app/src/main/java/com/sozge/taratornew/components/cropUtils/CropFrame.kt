package com.sozge.taratornew.components.cropUtils

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable

fun CropFrame(
    aspectRatio: Float,
    onCropRectChange: (Rect) -> Unit
) {
    val density = LocalDensity.current.density
    var offset by remember { mutableStateOf(Offset(100f, 100f)) }
    var size by remember { mutableStateOf(Size(300f, 300f)) }



    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val boxWidth = constraints.maxWidth.toFloat()
        val boxHeight = constraints.maxHeight.toFloat()

        Box(
            modifier = Modifier
                .offset { IntOffset(offset.x.toInt(), offset.y.toInt()) }
                .size(size.width.dp, size.height.dp)
                .border(2.dp, Color.White)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        offset = Offset(
                            (offset.x + dragAmount.x).coerceIn(0f, boxWidth - size.width),
                            (offset.y + dragAmount.y).coerceIn(0f, boxHeight - size.height)
                        )
                        onCropRectChange(
                            Rect(
                                offset.x / density,
                                offset.y / density,
                                (offset.x + size.width) / density,
                                (offset.y + size.height) / density
                            )
                        )
                    }
                }
        )
    }
}



