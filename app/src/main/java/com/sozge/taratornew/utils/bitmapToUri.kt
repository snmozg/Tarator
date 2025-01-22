package com.sozge.taratornew.utils.com.sozge.taratornew.utils


import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun bitmapToUri(context: Context, bitmap: Bitmap?): Uri? {
    if (bitmap == null) return null

    return try {

        val file = File(context.cacheDir, "edited_image_${System.currentTimeMillis()}.jpg")
        val outputStream = FileOutputStream(file)


        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()
        outputStream.close()


        FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
    } catch (e: IOException) {
        Log.e("bitmapToUri", "Failed to create URI: ${e.message}")
        null
    }
}








