package com.sozge.taratornew.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.content.ContentResolver
import android.content.Context
import android.provider.MediaStore
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.ImageBitmap
import java.io.InputStream

fun Uri.toBitmap(context: Context): Bitmap? {
    val inputStream: InputStream? = context.contentResolver.openInputStream(this)
    return BitmapFactory.decodeStream(inputStream)
}


