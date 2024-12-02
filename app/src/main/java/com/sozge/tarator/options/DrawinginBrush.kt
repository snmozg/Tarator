package com.sozge.tarator.options

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
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

@SuppressLint("MutableCollectionMutableState")
@Composable
fun DrawingCanvas(brushType: BrushType, imageViewModel: ImageViewModel) {
    val paths = remember { mutableStateListOf<MutableList<Offset>>() }
    val currentPath = remember { mutableStateOf<MutableList<Offset>>(mutableListOf()) }
    val drawings = imageViewModel.drawings.value

    val context = LocalContext.current

    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val imageUri = imageViewModel.myImage.value

    // URI'yi Bitmap'e dönüştür

    imageUri?.let {
        bitmap = uriToBitmap(context, it)
    }

    // -----CHAT------Image URI'den bitmap oluşturuluyor

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
    )
    {
        TextButton(

            onClick = {
                if (imageUri != null) {
                    imageViewModel.updateImage(imageUri, imageViewModel.drawings.value)
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(
                fontSize = 20.sp,
                text = "Save changes"
            )
        }

        Box(modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        // Yeni bir path başlatılıyor
                        currentPath.value = mutableListOf(offset) // Yeni bir path başlatılıyor
                        println("Path Start: $offset")
                        //currentPath.value = mutableListOf(offset)
                        //imageViewModel.addDrawing(currentPath.value)
                        //println("Path Value: ${drawings.size}")
                    },
                    onDrag = { change, _ ->
                        // Çizim devam ediyor
                        currentPath.value?.add(change.position)
                    },
                    onDragEnd = {
                        // Çizim bitiyor ve ViewModel'e ekleniyor
                        currentPath.value?.let { path ->
                            val drawing = Drawing(
                                brushType = brushType,
                                color = brushColor,
                                strokeWidth = brushSize,
                                path = path
                            )
                            imageViewModel.addDrawing(drawing)
                        }
                        currentPath.value = mutableListOf() // Path sıfırlanıyor

                    }
                )
            }
        )
        {
            // Bitmap'i göster
            if (bitmap != null) {
                Image(
                    bitmap = bitmap!!.asImageBitmap(),
                    contentDescription = "Selected Image",
                    contentScale = ContentScale.Fit
                )
            } else {
                Text("No image selected", modifier = Modifier.fillMaxSize())
            }


            Canvas(modifier = Modifier.fillMaxSize()) {
                // Debug log ekleyelim
                // Çizimlerin sayısını kontrol edelim
                println("Drawing count: ${drawings.size}")

                drawings.forEach { drawing ->
                    // Çizimlerin her biri için line çiziyoruz
                    for (i in 1 until drawing.path.size) {
                        drawLine(
                            color = drawing.color,
                            start = drawing.path[i - 1],
                            end = drawing.path[i],
                            strokeWidth = drawing.strokeWidth
                        )
                    }
                }
            }

            /*
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawings.forEach { drawing ->
                    for (i in 1 until drawing.path.size) {
                        drawLine(
                            color = drawing.color,
                            start = drawing.path[i - 1],
                            end = drawing.path[i],
                            strokeWidth = drawing.strokeWidth
                        )
                    }
                }
            }

             */
        }
    }
}


