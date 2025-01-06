package com.sozge.taratornew.utils.com.sozge.taratornew.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint

fun Bitmap.adjustDetails(intensity: Float): Bitmap {
    val width = this.width
    val height = this.height

    // Yeni bitmap oluştur
    val resultBitmap = Bitmap.createBitmap(width, height, this.config!!)

    // Keskinlik için bir kernel tanımla
    val sharpness = intensity.coerceIn(0f, 10f)
    val kernel = floatArrayOf(
        0f, -sharpness, 0f,
        -sharpness, 1f + 4 * sharpness, -sharpness,
        0f, -sharpness, 0f
    )

    val paint = Paint()
    val canvas = Canvas(resultBitmap)


    // Keskinleştirme işlemi için yeni bir bitmap oluştur
    val inputBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val inputCanvas = Canvas(inputBitmap)
    inputCanvas.drawBitmap(this, 0f, 0f, paint)

    // Kernel matrisini uygulama
    val colorMatrix = android.graphics.ColorMatrix()
    colorMatrix.setSaturation(1f) // Renk doygunluğunu koruyalım

    // ColorMatrix ile keskinleştirme matrisini uygulayalım
    val colorFilter = android.graphics.ColorMatrixColorFilter(colorMatrix)
    paint.colorFilter = colorFilter

    // Yeni bitmap üzerinde keskinleştirme işlemini uygula
    canvas.drawBitmap(inputBitmap, 0f, 0f, paint)

    return resultBitmap
}





