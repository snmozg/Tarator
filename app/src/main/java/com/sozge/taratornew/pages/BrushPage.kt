package com.sozge.taratornew.utils.com.sozge.taratornew.pages

import BottomSheetViewModel
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material.icons.outlined.LineWeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.sozge.taratornew.components.CustomOptionsButton
import com.sozge.taratornew.components.HeaderBar
import com.sozge.taratornew.components.brushes.BrushColorPicker
import com.sozge.taratornew.components.brushes.Line
import com.sozge.taratornew.components.filters.FilterSection
import com.sozge.taratornew.models.DrawingViewModel
import com.sozge.taratornew.models.FilterViewModel
import com.sozge.taratornew.models.ImageViewModel
import com.sozge.taratornew.utils.toBitmap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrushPage(
    navController: NavController,
    imageViewModel: ImageViewModel,
    drawingViewModel: DrawingViewModel,
    filterViewModel: FilterViewModel,
    bottomSheetViewModel: BottomSheetViewModel,
) {
    val context = LocalContext.current
    var brushColor by remember { mutableStateOf(Color.Black) }
    var strokeWidth by remember { mutableStateOf(5.dp) }
    val imageUri = imageViewModel.myImage.value
    val bitmap = imageUri?.toBitmap(context)
    val imageBitmap = bitmap?.asImageBitmap()
    val height = imageBitmap?.height?.toFloat()
    val width = imageBitmap?.width?.toFloat()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HeaderBar(
                navController = navController,
                actionImageVector = Icons.Outlined.Check,
                actionContentDescription = "Check",
                isBackButtonEnable = true,
                imageViewModel = imageViewModel,
                drawingViewModel = drawingViewModel,
                filterViewModel = filterViewModel,
                onClick = {
                    navController.popBackStack()
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .width(width!!.dp),
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(context)
                            .data(imageViewModel.myImage.value)
                            .build()
                    ),
                    contentDescription = null,
                    colorFilter = filterViewModel.filter.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height!!.dp)
                        .width(width.dp)
                )
                Canvas(
                    modifier = Modifier
                        .border(2.dp, Color.White)
                        .fillMaxWidth()
                        .height(height.dp)
                        .width(width.dp)
                        .pointerInput(Unit) {
                            detectDragGestures { change, dragAmount ->
                                change.consume()
                                val localPosition = change.position
                                if (localPosition.x in 0f..size.width.toFloat() && localPosition.y in 0f..size.height.toFloat()) {
                                    val line = Line(
                                        start = localPosition - dragAmount,
                                        end = localPosition,
                                        color = brushColor,
                                        strokeWith = strokeWidth
                                    )
                                    drawingViewModel.addLine(line)
                                }
                            }
                        }
                ) {
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

            Spacer(modifier = Modifier.height(50.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomOptionsButton(
                    Icons.Outlined.ColorLens,
                    "Color Picker",
                    "COLOR",
                    onClick = {
                        bottomSheetViewModel.openColorSheet()
                    }
                )
                CustomOptionsButton(
                    Icons.Outlined.LineWeight,
                    "Line Width",
                    "WIDTH",
                    onClick = {
                        bottomSheetViewModel.openWidthSheet()
                    }
                )
            }

            if (bottomSheetViewModel.isColorSheetOpen.value) {
                ModalBottomSheet(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.primary,
                    onDismissRequest = { bottomSheetViewModel.closeColorSheet() },
                    sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
                ) {
                    BrushColorPicker(
                        selectedColor = remember { mutableStateOf(brushColor) },
                        bottomSheetViewModel = bottomSheetViewModel
                    ) { color ->
                        brushColor = color
                    }
                }
            }

            if (bottomSheetViewModel.isWidthSheetOpen.value) {
                ModalBottomSheet(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.primary,
                    onDismissRequest = { bottomSheetViewModel.closeWidthSheet() },
                    sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
                ) {
                    Slider(
                        value = strokeWidth.value,
                        onValueChange = { strokeWidth = it.dp },
                        valueRange = 1f..50f,
                        steps = 49,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}