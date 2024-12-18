package com.sozge.taratornew.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.rounded.AddCircleOutline
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.sozge.taratornew.R
import com.sozge.taratornew.models.DrawingViewModel
import com.sozge.taratornew.models.FilterViewModel
import com.sozge.taratornew.models.ImageViewModel
import com.sozge.taratornew.utils.com.sozge.taratornew.animations.LottieAnimation
import com.sozge.taratornew.utils.com.sozge.taratornew.models.ToolsViewModel
import com.sozge.taratornew.utils.toImageBitmap

@Composable
fun EditPageImage(
    imageViewModel: ImageViewModel,
    filterViewModel: FilterViewModel,
    drawingViewModel: DrawingViewModel,
    toolsViewModel: ToolsViewModel,
    hasPermission: MutableState<Boolean>,
    galleryLauncher: () -> Unit,
    permissionLauncher: () -> Unit
) {
    val context = LocalContext.current
    val imageBitmap = imageViewModel.myImage.value?.toImageBitmap(context)


    if (imageBitmap != null) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .padding(10.dp)
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
                    .height(600.dp)
            )

            // Çizimleri eklemek için Canvas
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(imageBitmap.width.toFloat() / imageBitmap.height.toFloat())
            ) {
                drawingViewModel.lines.forEach { line ->
                    drawLine(
                        color = line.color,
                        start = line.start,
                        end = line.end,
                        strokeWidth = line.strokeWith.toPx()
                    )
                }
            }
        }
    } else {
        Image(
            imageVector = Icons.Outlined.AddCircleOutline,
            contentDescription = "add icon",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .clickable {
                    if (hasPermission.value) {
                        galleryLauncher()
                    } else {
                        permissionLauncher()
                    }
                }
                .fillMaxWidth()
                .height(600.dp)
                .padding(10.dp)
        )
    }
}