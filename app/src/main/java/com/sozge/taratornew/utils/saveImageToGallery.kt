package com.sozge.taratornew.utils.com.sozge.taratornew.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream

/*
fun saveImageToGallery(bitmap: Bitmap, context: Context) {

    if (bitmap == null) {
        Toast.makeText(context, "Photo to be saved not found!", Toast.LENGTH_SHORT).show()
        return
    }

    val filename = "Tarator_${System.currentTimeMillis()}.png"

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/Tarator")
        }

        val resolver = context.contentResolver
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        uri?.let {
            resolver.openOutputStream(it).use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream!!)
                Toast.makeText(context, "Photo successfully saved!", Toast.LENGTH_SHORT).show()
            }
        } ?: Toast.makeText(context, "Save failed!", Toast.LENGTH_SHORT).show()
    } else {
        val directory = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/Tarator")
        if (!directory.exists()) directory.mkdirs()

        val file = File(directory, filename)
        FileOutputStream(file).use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            Toast.makeText(context, "Photo successfully saved!", Toast.LENGTH_SHORT).show()
        }

        MediaScannerConnection.scanFile(context, arrayOf(file.toString()), null, null)
    }
}


 */