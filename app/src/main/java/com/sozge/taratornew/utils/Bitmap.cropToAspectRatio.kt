package com.sozge.taratornew.utils.com.sozge.taratornew.utils

import android.graphics.Bitmap

fun Bitmap.cropToAspectRatio(aspectRatio: Float): Bitmap {
    if (aspectRatio == 0f) return this

    val width = this.width
    val height = this.height

    val targetWidth: Int
    val targetHeight: Int

    if (width.toFloat() / height > aspectRatio) {
        targetHeight = height
        targetWidth = (height * aspectRatio).toInt()
    } else {
        targetWidth = width
        targetHeight = (width / aspectRatio).toInt()
    }

    val startX = (width - targetWidth) / 2
    val startY = (height - targetHeight) / 2

    return Bitmap.createBitmap(this, startX, startY, targetWidth, targetHeight)
}

