package com.sozge.taratornew.utils.com.sozge.taratornew.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint

fun Bitmap.adjustDetails(detailLevel: Float): Bitmap {
    val kernel = arrayOf(
        floatArrayOf(0f, -1f, 0f),
        floatArrayOf(-1f, 4f + detailLevel, -1f),
        floatArrayOf(0f, -1f, 0f)
    )

    val width = this.width
    val height = this.height
    val output = Bitmap.createBitmap(width, height, this.config!!)

    // Orijinal piksel değerleri
    val pixels = IntArray(width * height)
    this.getPixels(pixels, 0, width, 0, 0, width, height)

    // Yeni piksel değerleri
    val newPixels = IntArray(width * height)

    // Çekirdek hesaplama
    for (y in 1 until height - 1) {
        for (x in 1 until width - 1) {
            var r = 0f
            var g = 0f
            var b = 0f

            for (ky in -1..1) {
                for (kx in -1..1) {
                    val pixel = pixels[(y + ky) * width + (x + kx)]
                    val kernelValue = kernel[ky + 1][kx + 1]

                    r += ((pixel shr 16 and 0xFF) * kernelValue)
                    g += ((pixel shr 8 and 0xFF) * kernelValue)
                    b += ((pixel and 0xFF) * kernelValue)
                }
            }

            // RGB değerlerini sıkıştır ve sınırlı aralıkta tut
            val newR = r.coerceIn(0f, 255f).toInt()
            val newG = g.coerceIn(0f, 255f).toInt()
            val newB = b.coerceIn(0f, 255f).toInt()

            newPixels[y * width + x] = (0xFF shl 24) or (newR shl 16) or (newG shl 8) or newB
        }
    }

    output.setPixels(newPixels, 0, width, 0, 0, width, height)
    return output
}







