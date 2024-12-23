package com.sozge.taratornew.utils.com.sozge.taratornew.components.tools

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sozge.taratornew.utils.com.sozge.taratornew.utils.cropToAspectRatio



@Composable
fun Crop(
    bitmap: Bitmap?,
    displayBitmap: Bitmap?,
    selectedCropType: CropType = CropType.Free,
    onCropApplied: (Bitmap?) -> Unit
) {

    var cropType by remember { mutableStateOf(selectedCropType) }


        Text(
            text = "Crop",
            fontSize = 20.sp,
            style = MaterialTheme.typography.bodyMedium
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(listOf(CropType.Free, CropType.Ratio1x1, CropType.Ratio16x9, CropType.Ratio4x3)) { type ->
                Button(
                    onClick = { cropType = type },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(text = type.name)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val croppedBitmap = bitmap?.cropToAspectRatio(
                    aspectRatio = cropType.aspectRatio,

                )
                onCropApplied(croppedBitmap)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(text = "Apply Crop")
        }
    }

enum class CropType(val aspectRatio: Float) {
    Free(0f),
    Ratio1x1(1f),
    Ratio16x9(16f / 9f),
    Ratio4x3(4f / 3f)
}

