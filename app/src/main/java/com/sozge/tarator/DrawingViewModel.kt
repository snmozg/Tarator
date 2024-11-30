package com.sozge.tarator

import androidx.compose.runtime.mutableStateListOf

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel

class DrawingViewModel : ViewModel() {
    // Çizim noktalarını saklayan mutable state listesi
    private val _drawingPaths = mutableStateListOf<Offset>()
    val drawingPaths: List<Offset> = _drawingPaths // Sadece getter olarak expose ediyoruz

    // Yeni nokta eklemek için fonksiyon
    fun addPoint(point: Offset) {
        _drawingPaths.add(point)
    }

    // Çizimi temizlemek için fonksiyon
    fun clearDrawing() {
        _drawingPaths.clear()
    }
}
