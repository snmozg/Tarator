package com.sozge.taratornew.utils.com.sozge.taratornew.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import java.io.IOException

fun saveBitmapToGallery(context: Context, bitmap: Bitmap?): Uri? {
    if (bitmap == null) return null

    return try {
        // Görüntüyü galeriye kaydetmek için MediaStore kullanıyoruz
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "edited_image_${System.currentTimeMillis()}.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/TaratorApp") // Galeride bir klasör ismi
        }

        val contentResolver = context.contentResolver
        val imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        imageUri?.let { uri ->
            contentResolver.openOutputStream(uri)?.use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            }
        }

        imageUri
    } catch (e: IOException) {
        Log.e("saveBitmapToGallery", "Failed to save image: ${e.message}")
        null
    }
}
