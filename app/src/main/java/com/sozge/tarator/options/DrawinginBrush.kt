package com.sozge.tarator.options

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
                        Log.d("DrawingSection", "Brush selected: $newBrushType")
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
    val pathPoints = remember { mutableStateListOf<Offset>() }
    val context = LocalContext.current

    // -----CHAT------Image URI'den bitmap oluşturuluyor
    val bitmap = remember(imageViewModel.myImage.value) {
        try {
            imageViewModel.myImage.value?.let { uri ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    val source = ImageDecoder.createSource(context.contentResolver, uri)
                    ImageDecoder.decodeBitmap(source)
                } else {
                    @Suppress("DEPRECATION")
                    MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                }
            }
        } catch (e: Exception) {
            Log.e("DrawingCanvas", "Error decoding image: ${e.message}")
            null
        }
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
            brushSize = 6f
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        pathPoints.add(offset) // Başlangıç noktası
                    },
                    onDrag = { change, _ ->
                        pathPoints.add(change.position) // Çizim noktası
                    }
                )
            }
    ) {

        bitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

//çizim
        Canvas(modifier = Modifier.fillMaxSize()) {
            for (i in 1 until pathPoints.size) {
                drawLine(
                    color = brushColor,
                    start = pathPoints[i - 1],
                    end = pathPoints[i],
                    strokeWidth = brushSize
                )
            }
        }
    }
}












