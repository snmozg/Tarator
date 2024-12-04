package com.sozge.tarator

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sozge.tarator.helpers.Drawing


class ImageViewModel : ViewModel() {

    private val _myImage = mutableStateOf<Uri?>(null)
    val myImage: State<Uri?> = _myImage

    private val _drawings = mutableStateOf<List<Drawing>>(emptyList())
    val drawings: State<List<Drawing>> get() = _drawings



    fun updateImage(newValue: Uri, newDrawings: List<Drawing>) {
        _myImage.value = newValue
        _drawings.value = newDrawings 
    }

    fun deleteImage() {
        _myImage.value = null
        _drawings.value = emptyList()
    }

    fun addDrawing(drawing: Drawing) {
        val currentList = _drawings.value.toMutableList()
        currentList.add(drawing)
        _drawings.value = currentList
    }
}