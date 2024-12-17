package com.sozge.taratornew.utils.com.sozge.taratornew.utils


import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import java.io.File
import java.io.FileOutputStream


fun bitmapToUri(context: Context, bitmap: Bitmap?): Uri? {
    if (bitmap == null) return null


    val file = File(context.cacheDir, "edited_image_${System.currentTimeMillis()}.jpg")
    val outputStream = FileOutputStream(file)

    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    outputStream.flush()
    outputStream.close()

    return Uri.fromFile(file)
}





