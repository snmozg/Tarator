package com.sozge.taratornew.utils

import android.graphics.Bitmap

fun Bitmap.applyHue(hue: Float): Bitmap {
    val bitmap = this.copy(Bitmap.Config.ARGB_8888, true)
    val matrix = android.graphics.ColorMatrix()

    // Hue dönüşüm matrisi
    matrix.setRotate(0, hue) // Kırmızı kanal
    matrix.setRotate(1, hue) // Yeşil kanal
    matrix.setRotate(2, hue) // Mavi kanal

    val paint = android.graphics.Paint()
    paint.colorFilter = android.graphics.ColorMatrixColorFilter(matrix)

    val canvas = android.graphics.Canvas(bitmap)
    canvas.drawBitmap(this, 0f, 0f, paint)

    return bitmap
}