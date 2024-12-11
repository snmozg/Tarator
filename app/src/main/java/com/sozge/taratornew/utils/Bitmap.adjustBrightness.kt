package com.sozge.taratornew.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint

fun Bitmap.adjustBrightness(brightness: Float): Bitmap {
    val width = this.width
    val height = this.height
    val bitmap = Bitmap.createBitmap(width, height, this.config!!)
    val canvas = Canvas(bitmap)
    val paint = Paint()
    val cm = ColorMatrix()
    cm.setScale(brightness, brightness, brightness, 1.0f) // Parlaklık ayarını yap
    val filter = ColorMatrixColorFilter(cm)
    paint.colorFilter = filter
    canvas.drawBitmap(this, 0f, 0f, paint)
    return bitmap
}