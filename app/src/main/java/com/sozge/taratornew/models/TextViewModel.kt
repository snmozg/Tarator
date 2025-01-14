package com.sozge.taratornew.models

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.TextUnit
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class TextViewModel : ViewModel() {
    private val _textList = MutableStateFlow<List<TextData>>(emptyList())
    val textList: StateFlow<List<TextData>> get() = _textList

    fun addText(text: String, position: Offset, color: androidx.compose.ui.graphics.Color, size: TextUnit) {
        val newText = TextData(
            text = text,
            position = position,
            color = color,
            size = size
        )
        _textList.value = _textList.value + newText
    }
}

data class TextData(
    val text: String,
    val position: Offset,
    val color: androidx.compose.ui.graphics.Color,
    val size: TextUnit
)


