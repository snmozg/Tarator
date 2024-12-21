package com.sozge.taratornew.components.brushes

import BottomSheetViewModel
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
    bottomSheetViewModel: BottomSheetViewModel
) {
    val context = LocalContext.current
    val imageUri = imageViewModel.myImage.value
    val bitmap = imageUri?.toBitmap(context)
    val imageBitmap = bitmap?.asImageBitmap()

    // Handle Drawing settings
    var brushColor by remember { mutableStateOf(Color.Black) }
    var strokeWidth by remember { mutableStateOf(5.dp) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = {
                bottomSheetViewModel.closeToolsSheet()
            },
            modifier = Modifier
                .width(50.dp)
                .padding(1.dp)
                .align(Alignment.End)
        ) {
            Icon(
                imageVector = Icons.Rounded.Check,
                contentDescription = "Save Brightness",
                tint = Color(0xFFFC6310)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            imageBitmap?.let {
                Canvas(
                    modifier = Modifier
                        .aspectRatio(it.width.toFloat() / it.height.toFloat())
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
                    drawImage(imageBitmap)


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
            }
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BrushColorPicker(selectedColor = remember { mutableStateOf(brushColor) }) { color ->
                brushColor = color
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Stroke Width: ${strokeWidth.value.toInt()}")
            Slider(
                value = strokeWidth.value,
                onValueChange = { strokeWidth = it.dp },
                valueRange = 1f..50f,
                steps = 49,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { drawingViewModel.clearDrawing() }) {
                Text("Clear Drawing", color = Color.White)
            }
        }
    }
}
