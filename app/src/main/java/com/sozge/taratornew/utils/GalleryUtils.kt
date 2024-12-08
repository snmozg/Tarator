package com.sozge.taratornew.utils

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
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
