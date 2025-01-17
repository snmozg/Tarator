package com.sozge.taratornew.models

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.TextUnit
import androidx.lifecycle.ViewModel
import com.sozge.taratornew.text.TextData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TextViewModel : ViewModel() {
    private val _textList = MutableStateFlow<List<TextData>>(emptyList())
    val textList: StateFlow<List<TextData>> get() = _textList

    fun addText(
        text: String,
        position: Offset,
        color: androidx.compose.ui.graphics.Color,
        size: TextUnit,
    ) {
        val newText = TextData(
            text = text,
            position = position,
            color = color,
            size = size
        )
        _textList.value += newText
    }

    fun updateTextPosition(index: Int, newPosition: Offset) {
        _textList.value = _textList.value.mapIndexed { i, textData ->
            if (i == index) textData.copy(position = newPosition) else textData
        }
    }

    fun updateImageWithText(
        context: Context,
        textList: List<TextData>,
        originalBitmap: Bitmap
    ): Bitmap {
        val updatedBitmap = originalBitmap.copy(originalBitmap.config!!, true)
        val canvas = Canvas(updatedBitmap)
        val paint = Paint().apply {
            color = Color.BLACK
            textSize = 50f
        }

        val density = Density(context.resources.displayMetrics.density)

        textList.forEach { textData ->
            paint.color = textData.color.toArgb()
            paint.textSize = with(density) { textData.size.toPx() } // Convert TextUnit to px
            canvas.drawText(
                textData.text,
                textData.position.x,
                textData.position.y,
                paint
            )
        }

        return updatedBitmap
    }
}