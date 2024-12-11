package com.sozge.taratornew.components.tools

import android.graphics.Bitmap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun CropToolView(
    imageBitmap: ImageBitmap?,
    onCrop: (ImageBitmap) -> Unit
) {

    var cropRect by remember { mutableStateOf(Rect(0f, 0f, 1f, 1f)) }


    Box(modifier = Modifier.fillMaxSize()) {
        imageBitmap?.let {
            Image(
                bitmap = it,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectDragGestures { _, dragAmount ->
                            cropRect = cropRect.copy(
                                left = cropRect.left + dragAmount.x,
                                top = cropRect.top + dragAmount.y
                            )
                        }
                    }
            )


            Box(
                modifier = Modifier
                    .border(2.dp, Color.Blue)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Canvas(
                    modifier = Modifier.fillMaxSize()
                ) {
                    drawRect(
                        color = Color.Black.copy(alpha = 0.5f),
                        topLeft = Offset(cropRect.left, cropRect.top),
                        size = Size(
                            width = cropRect.width,
                            height = cropRect.height
                        )
                    )
                }
            }
        }


        Button(
            onClick = {
                val cropped = imageBitmap?.let { cropImage(it, cropRect) }
                cropped?.let { onCrop(it) }
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text("Crop")
        }
    }
}

fun cropImage(imageBitmap: ImageBitmap, cropRect: Rect): ImageBitmap {

    val width = (cropRect.right - cropRect.left).toInt()
    val height = (cropRect.bottom - cropRect.top).toInt()
    val bitmap = imageBitmap.asAndroidBitmap()


    val croppedBitmap = Bitmap.createBitmap(bitmap, cropRect.left.toInt(), cropRect.top.toInt(), width, height)

    return croppedBitmap.asImageBitmap()
}
