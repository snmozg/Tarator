package com.sozge.taratornew.models

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ImageViewModel : ViewModel() {
    private val _myImage = mutableStateOf<Uri?>(null)
    val myImage: State<Uri?> = _myImage

    fun updateImage(newValue: Uri) {
        _myImage.value = newValue

    }

    fun deleteImage() {
        _myImage.value = null
    }


}