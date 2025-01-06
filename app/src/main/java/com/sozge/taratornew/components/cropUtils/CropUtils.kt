package com.sozge.taratornew.components.cropUtils


import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

object CropUtils {
    fun cropBitmap(bitmap: Bitmap, cropRect: androidx.compose.ui.geometry.Rect, scaleFactor: Float): Bitmap {
        return Bitmap.createBitmap(
            bitmap,
            (cropRect.left * scaleFactor).toInt(),
            (cropRect.top * scaleFactor).toInt(),
            (cropRect.width * scaleFactor).toInt(),
            (cropRect.height * scaleFactor).toInt()
        )
    }


    fun saveBitmapToUri(context: Context, bitmap: Bitmap): Uri {
        val file = File(context.cacheDir, "cropped_image_${System.currentTimeMillis()}.png")
        file.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        return FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
    }
}
