package com.sozge.taratornew.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

class DrawingViewModel : ViewModel() {
    val lines: SnapshotStateList<Line> = mutableStateListOf()

    fun addLine(line: Line) {
        lines.add(line)
    }

    fun clearDrawing() {
        lines.clear()
    }
}

data class Line(
    val start: Offset,
    val end: Offset,
    val color: Color = Color.Black,
    val strokeWith: Dp = 1.dp
)