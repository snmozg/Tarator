package com.sozge.taratornew.utils

import android.graphics.Bitmap

fun Bitmap.applyHue(hue: Float): Bitmap {
    val bitmap = this.copy(Bitmap.Config.ARGB_8888, true)
    val matrix = android.graphics.ColorMatrix()


    matrix.setRotate(0, hue)
    matrix.setRotate(1, hue)
    matrix.setRotate(2, hue)

    val paint = android.graphics.Paint()
    paint.colorFilter = android.graphics.ColorMatrixColorFilter(matrix)

    val canvas = android.graphics.Canvas(bitmap)
    canvas.drawBitmap(this, 0f, 0f, paint)

    return bitmap
}