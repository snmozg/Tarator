package com.sozge.tarator.helpers

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.sozge.tarator.options.BrushType

data class Drawing(
    val brushType: BrushType,
    val color: Color,
    val strokeWidth: Float,
    val path: List<Offset>
)