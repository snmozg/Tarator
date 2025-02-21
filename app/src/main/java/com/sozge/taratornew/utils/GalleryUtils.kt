package com.sozge.taratornew.utils

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Canvas
import com.sozge.taratornew.models.ImageViewModel

@Composable
fun rememberGalleryLauncher(imageViewModel: ImageViewModel) =
    rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imageViewModel.updateImage(it)
        }
    }

fun combineBitmaps(base: Bitmap, drawing: Bitmap, text: Bitmap): Bitmap {
    val result = Bitmap.createBitmap(base.width, base.height, base.config!!)
    val canvas = android.graphics.Canvas(result)
    // Temel (filtrelenmiş) resmi çiz
    canvas.drawBitmap(base, 0f, 0f, null)
    // Çizim katmanını ekle
    canvas.drawBitmap(drawing, 0f, 0f, null)
    // Yazı katmanını ekle
    canvas.drawBitmap(text, 0f, 0f, null)
    return result
}
