package com.sozge.taratornew.utils.com.sozge.taratornew.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.ColorFilter
import android.util.Log

fun applyFilterToBitmap(originalBitmap: Bitmap?, colorFilter: ColorFilter?): Bitmap? {
    if (originalBitmap == null) {
        Log.e("applyFilterToBitmap", "Original bitmap is null!")
        return null
    }

    return try {
        val resultBitmap = Bitmap.createBitmap(
            originalBitmap.width,
            originalBitmap.height,
            originalBitmap.config!!
        )

        val canvas = Canvas(resultBitmap)
        val paint = Paint()



        canvas.drawBitmap(originalBitmap, 0f, 0f, paint)



         colorFilter?.let {
            paint.colorFilter = it
            canvas.drawBitmap(originalBitmap, 0f, 0f, paint)
        }





        resultBitmap
    } catch (e: Exception) {
        Log.e("applyFilterToBitmap", "Error ${e.message}")
        null
    }
}


