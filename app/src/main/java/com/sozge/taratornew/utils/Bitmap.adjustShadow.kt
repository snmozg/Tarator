package com.sozge.taratornew.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint

fun Bitmap.adjustShadow(shadow: Float): Bitmap {
    val width = this.width
    val height = this.height
    val bitmap = Bitmap.createBitmap(width, height, this.config!!)
    val canvas = Canvas(bitmap)
    val paint = Paint()


    val darkeningFactor = 1f - shadow.coerceIn(0f, 1f)

    val cm = ColorMatrix(
        floatArrayOf(
            darkeningFactor, 0f, 0f, 0f, 0f,
            0f, darkeningFactor, 0f, 0f, 0f,
            0f, 0f, darkeningFactor, 0f, 0f,
            0f, 0f, 0f, 1f, 0f
        )
    )

    val filter = ColorMatrixColorFilter(cm)
    paint.colorFilter = filter
    canvas.drawBitmap(this, 0f, 0f, paint)
    return bitmap
}

