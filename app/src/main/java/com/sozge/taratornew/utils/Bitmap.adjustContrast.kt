package com.sozge.taratornew.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint

fun Bitmap.adjustContrast(contrast: Float): Bitmap {
    val width = this.width
    val height = this.height
    val bitmap = Bitmap.createBitmap(width, height, this.config!!)
    val canvas = Canvas(bitmap)
    val paint = Paint()
    val cm = ColorMatrix()
    val scale = contrast
    val translate = (-0.5f * scale + 0.5f) * 255f
    val contrastMatrix = floatArrayOf(
        scale, 0f, 0f, 0f, translate,
        0f, scale, 0f, 0f, translate,
        0f, 0f, scale, 0f, translate,
        0f, 0f, 0f, 1f, 0f
    )
    cm.set(contrastMatrix)
    val filter = ColorMatrixColorFilter(cm)
    paint.colorFilter = filter
    canvas.drawBitmap(this, 0f, 0f, paint)
    return bitmap
}

