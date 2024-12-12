package com.sozge.taratornew.utils

import android.graphics.Bitmap

fun Bitmap.rotateImage(angle: Float): Bitmap {
    val matrix = android.graphics.Matrix()
    matrix.postRotate(angle)
    return Bitmap.createBitmap(this, 0, 0, this.width, this.height, matrix, true)
}
