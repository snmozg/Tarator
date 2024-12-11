import android.graphics.Bitmap
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Brightness6
import androidx.compose.material.icons.outlined.Crop
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.sozge.taratornew.components.tools.Brightness
import com.sozge.taratornew.components.tools.Contrast
import com.sozge.taratornew.components.tools.Crop
import com.sozge.taratornew.components.CustomToolButton
import com.sozge.taratornew.models.ImageViewModel
import com.sozge.taratornew.utils.toBitmap


@Composable
fun ToolsSection(
    imageViewModel: ImageViewModel,
) {
    val context = LocalContext.current
    val imageUri = imageViewModel.myImage.value
    val bitmap = imageUri?.toBitmap(context)
    val imageBitmap = bitmap?.asImageBitmap()

    var displayBitmap by remember { mutableStateOf<Bitmap?>(bitmap) }

    var selectedTool by remember { mutableStateOf<ToolType?>(null) }
    var croppedImage by remember { mutableStateOf<ImageBitmap?>(null) }

    var brightness by remember { mutableFloatStateOf(1f) }
    var contrast by remember { mutableStateOf(1f) }
    var shadow by remember { mutableStateOf(0f) }

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
                    modifier = Modifier
                        .aspectRatio(it.width.toFloat() / it.height.toFloat())
                        .graphicsLayer {
                            alpha = brightness.coerceIn(0f, 4f)
                            contrast = contrast.coerceIn(0f, 10f)
                            shadowElevation = shadow.coerceIn(0f, 10f)
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
            Text(
                text = "Tools",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomToolButton(icon = Icons.Outlined.Crop, description = "Crop") {
                    selectedTool = ToolType.Crop
                }
                CustomToolButton(icon = Icons.Outlined.Brightness6, description = "Brightness") {
                    selectedTool = ToolType.Brightness
                }
                CustomToolButton(icon = Icons.Outlined.Tune, description = "Contrast") {
                    selectedTool = ToolType.Contrast
                }
                CustomToolButton(icon = Icons.Outlined.WbSunny, description = "Shadow") {
                    selectedTool = ToolType.Shadow
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            when (selectedTool) {
                ToolType.Crop -> Crop()

                ToolType.Brightness -> {
                    Brightness(
                        brightness = brightness,
                        displayBitmap = displayBitmap,
                        bitmap = bitmap
                    ) { newBrightness, newBitmap ->
                        brightness = newBrightness
                        displayBitmap = newBitmap
                    }
                }

                ToolType.Contrast -> Contrast()
                ToolType.Shadow -> {
                    Text(text = "Shadow", style = MaterialTheme.typography.bodyMedium)
                    Slider(
                        value = shadow,
                        onValueChange = { shadow = it },
                        valueRange = 0f..4f,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                else -> Text("Select a tool to start editing.")
            }
        }
    }


}

enum class ToolType {
    Crop, Brightness, Contrast, Shadow
}


