package com.sozge.taratornew.text

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun DraggableTextItem(
    textData: TextData,
    onPositionChange: (Offset) -> Unit,
) {
    var dragOffset by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = Modifier
            .offset { IntOffset(dragOffset.x.roundToInt(), dragOffset.y.roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    dragOffset += dragAmount
                    onPositionChange(dragOffset)
                }
            }
    ) {
        Text(
            text = textData.text,
            fontSize = textData.size,
            color = textData.color,
            modifier = Modifier.padding(8.dp)
        )
    }
}