package com.sozge.taratornew.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.navigation.NavController
import com.sozge.taratornew.components.HeaderBar
import com.sozge.taratornew.models.DrawingViewModel
import com.sozge.taratornew.models.FilterViewModel
import com.sozge.taratornew.models.ImageViewModel
import com.sozge.taratornew.components.cropUtils.CropFrame
import com.sozge.taratornew.components.cropUtils.CropUtils
import com.sozge.taratornew.utils.toBitmap

@Composable
fun CropPage(
    navController: NavController,
    imageViewModel: ImageViewModel,
    filterViewModel: FilterViewModel,
    drawingViewModel: DrawingViewModel
) {
    val context = LocalContext.current
    val imageUri = imageViewModel.myImage.value
    val bitmap = imageUri?.toBitmap(context)
    val imageBitmap = bitmap?.asImageBitmap()

    val density = LocalDensity.current.density
    var cropRect by remember { mutableStateOf<Rect?>(null) }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val boxWidth = constraints.maxWidth.toFloat()
        val boxHeight = constraints.maxHeight.toFloat()

        val scaleFactorX = bitmap?.let { it.width.toFloat() / boxWidth } ?: 1f
        val scaleFactorY = bitmap?.let { it.height.toFloat() / boxHeight } ?: 1f

        Scaffold(
            topBar = {
                HeaderBar(
                    navController = navController,
                    actionImageVector = Icons.Outlined.Check,
                    actionContentDescription = "Check",
                    isBackButtonEnable = true,
                    imageViewModel = imageViewModel,
                    filterViewModel = filterViewModel,
                    drawingViewModel = drawingViewModel,
                    onClick = {
                        cropRect?.let { rect ->
                            bitmap?.let { bmp ->
                                val imageWidth = bmp.width
                                val imageHeight = bmp.height

                                val displayMetrics = context.resources.displayMetrics
                                val density = displayMetrics.density

                                val screenWidth = imageWidth / density
                                val screenHeight = imageHeight / density


                                val scaleFactorX = imageWidth / screenWidth
                                val scaleFactorY = imageHeight / screenHeight

                                val croppedBitmap = CropUtils.cropBitmap(
                                    bmp,
                                    android.graphics.Rect(
                                        (rect.left * scaleFactorX).toInt(),
                                        (rect.top * scaleFactorY).toInt(),
                                        (rect.right * scaleFactorX).toInt(),
                                        (rect.bottom * scaleFactorY).toInt()
                                    )
                                )

                                val croppedUri = CropUtils.saveBitmapToUri(context, croppedBitmap)
                                imageViewModel.updateCroppedImage(croppedUri)
                            }
                        }
                        navController.popBackStack()

                    }
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                imageBitmap?.let {
                    Image(
                        bitmap = it,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }

                CropFrame(
                    aspectRatio = 1f,
                    onCropRectChange = { rect ->
                        cropRect = rect
                    }
                )
            }
        }
    }
}










