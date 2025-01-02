import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.HdrEnhancedSelect
import androidx.compose.material.icons.filled.Vignette
import androidx.compose.material.icons.outlined.Brightness6
import androidx.compose.material.icons.outlined.Crop
import androidx.compose.material.icons.outlined.Rotate90DegreesCw
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sozge.taratornew.components.tools.Brightness
import com.sozge.taratornew.components.tools.Contrast
import com.sozge.taratornew.components.CustomToolButton
import com.sozge.taratornew.components.tools.Details
import com.sozge.taratornew.components.tools.Rotate
import com.sozge.taratornew.components.tools.Shadow
import com.sozge.taratornew.components.tools.Vignette
import com.sozge.taratornew.models.FilterViewModel
import com.sozge.taratornew.models.ImageViewModel
import com.sozge.taratornew.utils.com.sozge.taratornew.models.ToolsViewModel
import com.sozge.taratornew.utils.com.sozge.taratornew.utils.bitmapToUri
import com.sozge.taratornew.utils.toBitmap


@Composable
fun ToolsSection(
    imageViewModel: ImageViewModel,
    toolsViewModel: ToolsViewModel,
    filterViewModel: FilterViewModel,
    bottomSheetViewModel: BottomSheetViewModel,
) {
    val context = LocalContext.current
    val imageUri = imageViewModel.myImage.value
    val bitmap = imageUri?.toBitmap(context)
    val imageBitmap = bitmap?.asImageBitmap()

    var displayBitmap by remember { mutableStateOf<Bitmap?>(bitmap) }

    var selectedTool by remember { mutableStateOf<ToolType?>(null) }

    val brightness = toolsViewModel.brightness.value
    val contrast = toolsViewModel.contrast.value
    val shadow = toolsViewModel.shadow.value
    val rotationAngle = toolsViewModel.rotationAngle.value
    val vignetteIntensity = toolsViewModel.vignetteIntensity.value
    val detail = toolsViewModel.detail.value

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            val displayImage = displayBitmap?.asImageBitmap()
            displayImage?.let {
                Image(
                    bitmap = it,
                    contentDescription = null,
                    colorFilter = filterViewModel.filter.value,
                    modifier = Modifier
                        .aspectRatio(it.width.toFloat() / it.height.toFloat())
                        .graphicsLayer {
                            alpha = brightness.coerceIn(0f, 4f)
                            //contrast = contrast.coerceIn(0f, 10f)
                            shadowElevation = shadow.coerceIn(0f, 10f)
                            rotationZ = rotationAngle

                        }
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (selectedTool == null) {
                Text(
                    text = "Save Changes",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            displayBitmap?.let { updatedBitmap ->
                                val updatedUri = bitmapToUri(context, updatedBitmap)
                                updatedUri?.let {
                                    imageViewModel.updateImage(it)
                                }
                            }
                            bottomSheetViewModel.closeToolsSheet()
                        }
                        .padding(bottom = 16.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .horizontalScroll(rememberScrollState())
                ) {

                    CustomToolButton(
                        icon = Icons.Outlined.Brightness6,
                        description = "Brightness"
                    ) {
                        selectedTool = ToolType.Brightness
                    }
                    CustomToolButton(icon = Icons.Outlined.Tune, description = "Contrast") {
                        selectedTool = ToolType.Contrast
                    }
                    CustomToolButton(icon = Icons.Outlined.WbSunny, description = "Shadow") {
                        selectedTool = ToolType.Shadow
                    }
                    CustomToolButton(
                        icon = Icons.Outlined.Rotate90DegreesCw,
                        description = "Rotate"
                    ) {
                        selectedTool = ToolType.Rotate
                    }
                    CustomToolButton(icon = Icons.Filled.Vignette, description = "Vignette") {
                        selectedTool = ToolType.Vignette
                    }
                    CustomToolButton(icon = Icons.Filled.Details, description = "Details") {
                        selectedTool = ToolType.Details
                    }
                }
            } else {
                Spacer(modifier = Modifier.height(16.dp))
                when (selectedTool) {

                    ToolType.Brightness -> {
                        Brightness(
                            brightness = brightness,
                            displayBitmap = displayBitmap,
                            bitmap = bitmap,
                            onBrightnessChanged = { newBrightness, newBitmap ->
                                toolsViewModel.updateBrightness(newBrightness)
                                displayBitmap = newBitmap
                            },
                        )
                    }

                    ToolType.Contrast -> {
                        Contrast(
                            contrast = contrast,
                            displayBitmap = displayBitmap,
                            bitmap = bitmap
                        ) { newContrast, newBitmap ->
                            toolsViewModel.updateContrast(newContrast)
                            displayBitmap = newBitmap
                        }
                    }

                    ToolType.Shadow -> {
                        Shadow(
                            shadow = shadow,
                            displayBitmap = displayBitmap,
                            bitmap = bitmap
                        ) { newShadow, newBitmap ->
                            toolsViewModel.updateShadow(newShadow)
                            displayBitmap = newBitmap
                        }
                    }

                    ToolType.Rotate -> {
                        Rotate(
                            rotationAngle = rotationAngle,
                            displayBitmap = displayBitmap,
                            bitmap = bitmap
                        ) { newAngle, newBitmap ->
                            toolsViewModel.updateRotationAngle(newAngle)
                            displayBitmap = newBitmap
                        }
                    }

                    ToolType.Details -> {
                        Details(
                            detail = detail,
                            displayBitmap = displayBitmap,
                            bitmap = bitmap
                        ) { newDetail, newBitmap ->
                            toolsViewModel.updateDetail(newDetail)
                            displayBitmap = newBitmap
                        }
                    }

                    ToolType.Vignette -> {
                        Vignette(
                            vignetteIntensity = vignetteIntensity,
                            displayBitmap = displayBitmap,
                            bitmap = bitmap
                        ) { newVignette, newBitmap ->
                            toolsViewModel.updateVignetteIntensity(newVignette)
                            displayBitmap = newBitmap
                        }
                    }

                    else -> Text("Select a tool to start editing.")
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    IconButton(
                        onClick = {
                            /* displayBitmap?.let { updatedBitmap ->
                                 val updatedUri = bitmapToUri(context, updatedBitmap)
                                 updatedUri?.let {
                                     imageViewModel.updateImage(it)
                                 }
                             }

                             */
                            selectedTool = null
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Check,
                            contentDescription = "Save",
                            tint = Color(0xFFFC6310)
                        )
                    }
                }
            }

        }

    }
}

enum class ToolType {
    Brightness, Contrast, Shadow, Rotate, Vignette, Details
}
