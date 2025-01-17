package com.sozge.taratornew.text

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.TextUnit

data class TextData(
    val text: String,
    val position: Offset,
    val color: androidx.compose.ui.graphics.Color,
    val size: TextUnit,
)
