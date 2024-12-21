package com.sozge.taratornew.models

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.toArgb
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
    fun getCurrentBitmap(width: Int, height: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint()

        for (line in lines) {
            paint.color = line.color.toArgb()
            paint.strokeWidth = line.strokeWith.value
            canvas.drawLine(line.start.x, line.start.y, line.end.x, line.end.y, paint)
        }

        return bitmap
    }
}



data class Line(
    val start: Offset,
    val end: Offset,
    val color: Color = Color.Black,
    val strokeWith: Dp = 1.dp
)