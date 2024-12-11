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
import androidx.compose.material.icons.filled.Brightness6
import androidx.compose.material.icons.filled.Crop
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import com.sozge.taratornew.components.tools.BrightnessToolView
import com.sozge.taratornew.components.tools.ContrastToolView
import com.sozge.taratornew.components.tools.CropToolView
import com.sozge.taratornew.components.tools.ShadowToolView
import com.sozge.taratornew.components.tools.toolButton
import com.sozge.taratornew.models.ImageViewModel
import com.sozge.taratornew.utils.toBitmap

@Composable
fun ToolsSection(
    imageViewModel: ImageViewModel
) {
    val context = LocalContext.current
    val imageUri = imageViewModel.myImage.value
    val bitmap = imageUri?.toBitmap(context)
    val imageBitmap = bitmap?.asImageBitmap()

    var selectedTool by remember { mutableStateOf<ToolType?>(null) }
    var croppedImage by remember { mutableStateOf<ImageBitmap?>(null) }

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
            val displayImage = croppedImage ?: imageBitmap
            displayImage?.let {
                Image(
                    bitmap = it,
                    contentDescription = null,
                    modifier = Modifier.aspectRatio(it.width.toFloat() / it.height.toFloat())
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
                toolButton(icon = Icons.Default.Crop, description = "Crop") {
                    selectedTool = ToolType.Crop
                }
                toolButton(icon = Icons.Default.Brightness6, description = "Brightness") {
                    selectedTool = ToolType.Brightness
                }
                toolButton(icon = Icons.Default.Tune, description = "Contrast") {
                    selectedTool = ToolType.Contrast
                }
                toolButton(icon = Icons.Default.WbSunny, description = "Shadow") {
                    selectedTool = ToolType.Shadow
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            when (selectedTool) {
                ToolType.Crop -> {
                    CropToolView(
                        imageBitmap = imageBitmap,
                        onCrop = { croppedBitmap ->
                            croppedImage = croppedBitmap
                        }
                    )
                }
                ToolType.Brightness -> BrightnessToolView()
                ToolType.Contrast -> ContrastToolView()
                ToolType.Shadow -> ShadowToolView()
                else -> Text("Select a tool to start editing.")
            }
        }
    }


}
enum class ToolType {
    Crop, Brightness, Contrast, Shadow
}
