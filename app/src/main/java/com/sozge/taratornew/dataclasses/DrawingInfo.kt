package com.sozge.taratornew.dataclasses

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

data class DrawingInfo(
    val brushType: BrushType,
    val color: Color,
    val strokeWidth: Float,
    val path: List<Offset>,
)