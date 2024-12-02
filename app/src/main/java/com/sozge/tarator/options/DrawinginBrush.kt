package com.sozge.tarator.options

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sozge.tarator.DrawingViewModel
import com.sozge.tarator.ImageViewModel
import com.sozge.tarator.R
import com.sozge.tarator.data.DataCardSection
import com.sozge.tarator.helpers.Drawing
import com.sozge.tarator.helpers.uriToBitmap
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

enum class BrushType {
    BRUSH, BRUSH2
}

val DrawingCards = listOf(
    DataCardSection(
        R.drawable.orangebrush,
        "Brush", id = 1
    ),
    DataCardSection(
        R.drawable.orangebrush,
        "Brush2", id = 2
    )
)

@Composable
fun DrawingSection(imageViewModel: ImageViewModel, drawingViewModel: DrawingViewModel) {
    var brushType by remember { mutableStateOf(BrushType.BRUSH) }

    Column {
        LazyRow {
            itemsIndexed(DrawingCards) { index, item ->
                DrawingCardItem(
                    index = index,
                    selectedBrushType = brushType,
                    onBrushSelected = { newBrushType ->
                        println(newBrushType)
                        brushType = newBrushType
                    }
                )
            }
        }
        DrawingCanvas(
            brushType = brushType,
            imageViewModel = imageViewModel
        )
    }
}

@Composable
fun DrawingCardItem(
    index: Int,
    selectedBrushType: BrushType,
    onBrushSelected: (BrushType) -> Unit,
) {
    val card = DrawingCards[index]
    val image = card.image
    val text = card.text
    val currentBrushType = when (text) {
        "Brush" -> BrushType.BRUSH
        "Brush2" -> BrushType.BRUSH2
        else -> BrushType.BRUSH
    }

    Column(
        modifier = Modifier.padding(start = 10.dp, 5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(start = 10.dp, 5.dp)
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .width(100.dp)
                    .height(100.dp)
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = "logos",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clickable(
                            enabled = true,
                            onClickLabel = "Clickable Image",
                            onClick = {
                                onBrushSelected(currentBrushType)
                            }
                        )
                )
            }
        }
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = text,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal
        )
    }
}
@Composable
fun DrawingCanvas(brushType: BrushType, imageViewModel: ImageViewModel) {
    val paths = remember { mutableStateListOf<MutableList<Offset>>() }
    val currentPath = remember { mutableStateOf<MutableList<Offset>>(mutableListOf()) }
    val drawings = imageViewModel.drawings.value

    val context = LocalContext.current

    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val imageUri = imageViewModel.myImage.value

    imageUri?.let {
        bitmap = uriToBitmap(context, it)
    }

    var brushColor = Color.Black
    var brushSize = 8f
    when (brushType) {
        BrushType.BRUSH -> {
            brushColor = Color.Red
            brushSize = 12f
        }

        BrushType.BRUSH2 -> {
            brushColor = Color.Blue
            brushSize = 12f
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(
            onClick = {
                imageUri?.let { uri ->
                    imageViewModel.updateImage(uri, drawings) // Resmi ve çizimleri güncelle
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(
                fontSize = 20.sp,
                text = "Save changes"
            )
        }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            var imageOffset = Offset.Zero // Resmin başlangıç pozisyonu
            var imageSize = Size.Zero // Resmin genişlik ve yüksekliği

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = { offset ->
                                if (offset.x >= imageOffset.x &&
                                    offset.x <= imageOffset.x + imageSize.width &&
                                    offset.y >= imageOffset.y &&
                                    offset.y <= imageOffset.y + imageSize.height) {
                                    currentPath.value = mutableListOf(offset - imageOffset)
                                }
                            },
                            onDrag = { change, _ ->
                                if (change.position.x >= imageOffset.x &&
                                    change.position.x <= imageOffset.x + imageSize.width &&
                                    change.position.y >= imageOffset.y &&
                                    change.position.y <= imageOffset.y + imageSize.height) {
                                    currentPath.value?.add(change.position - imageOffset)
                                }
                            },
                            onDragEnd = {
                                currentPath.value?.let { path ->
                                    val drawing = Drawing(
                                        brushType = brushType,
                                        color = brushColor,
                                        strokeWidth = brushSize,
                                        path = path
                                    )
                                    imageViewModel.addDrawing(drawing)
                                }
                                currentPath.value = mutableListOf()
                            }
                        )
                    }
            ) {
                if (bitmap != null) {
                    Image(
                        bitmap = bitmap!!.asImageBitmap(),
                        contentDescription = "Selected Image",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                            val position = layoutCoordinates.positionInParent()
                            val size = layoutCoordinates.size
                            imageOffset = Offset(position.x, position.y)
                            imageSize = Size(size.width.toFloat(), size.height.toFloat())
                        }
                    )
                } else {
                    Text("No image selected", modifier = Modifier.fillMaxSize())
                }

                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawings.forEach { drawing ->
                        for (i in 1 until drawing.path.size) {
                            drawLine(
                                color = drawing.color,
                                start = drawing.path[i - 1] + imageOffset,
                                end = drawing.path[i] + imageOffset,
                                strokeWidth = drawing.strokeWidth
                            )
                        }
                    }
                }
            }
        }
    }
}




