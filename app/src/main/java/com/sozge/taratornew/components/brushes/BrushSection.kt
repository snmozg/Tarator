package com.sozge.taratornew.components.brushes


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sozge.taratornew.models.DrawingViewModel
import com.sozge.taratornew.models.FilterViewModel
import com.sozge.taratornew.models.ImageViewModel
import com.sozge.taratornew.models.Line
import com.sozge.taratornew.utils.toBitmap

@Composable
fun BrushSection(
    imageViewModel: ImageViewModel,
    filterViewModel: FilterViewModel,
    drawingViewModel: DrawingViewModel,
) {
    val context = LocalContext.current
    val imageUri = imageViewModel.myImage.value
    val bitmap = imageUri?.toBitmap(context)
    val imageBitmap = bitmap?.asImageBitmap()

    // Handle Drawing settings
    var brushColor by remember { mutableStateOf(Color.Black) }
    var strokeWidth by remember { mutableStateOf(5.dp) }

    /*
    val lines = remember {
        mutableStateListOf<Line>()
    }

     */

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Canvas(
            modifier = Modifier
                .height(300.dp)
                .width(100.dp)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        val line = Line(
                            start = change.position - dragAmount,
                            end = change.position,
                            color = brushColor,
                            strokeWith = strokeWidth
                        )
                        drawingViewModel.addLine(line)
                    }
                }
        ) {

            // Draw the background image if exists
            imageBitmap?.let {
                drawImage(
                    it, Offset(
                        0f,
                        0f
                    )
                )
            }
            // Draw the lines on top of the image
            drawingViewModel.lines.forEach { line ->
                drawLine(
                    color = line.color,
                    start = line.start,
                    end = line.end,
                    strokeWidth = line.strokeWith.toPx(),
                    cap = StrokeCap.Round
                )
            }
        }

        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BrushColorPicker(selectedColor = remember { mutableStateOf(brushColor) }) { color ->
                brushColor = color
            }
            Slider(
                value = strokeWidth.value,
                onValueChange = { strokeWidth = it.dp },
                valueRange = 1f..50f,
                steps = 49,
                modifier = Modifier.padding(16.dp)
            )
            Button(onClick = {
                drawingViewModel.clearDrawing()
            }) {
                Text(
                    "Clear Drawing",
                    color = Color.White
                )
            }
        }
    }
}