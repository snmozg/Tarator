package com.sozge.taratornew.utils.com.sozge.taratornew.utils

import android.graphics.Bitmap
import android.graphics.Canvas

fun bitmapWithDrawing(backgroundBitmap: Bitmap, drawingBitmap: Bitmap): Bitmap {
    val resultBitmap = Bitmap.createBitmap(
        backgroundBitmap.width,
        backgroundBitmap.height,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(resultBitmap)


    canvas.drawBitmap(backgroundBitmap, 0f, 0f, null)


    canvas.drawBitmap(drawingBitmap, 0f, 0f, null)

    return resultBitmap
}
