package com.sozge.taratornew.components.cropUtils

import BottomSheetViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FramePicker(
    selectedFrame: State<Float>,
    bottomSheetViewModel: BottomSheetViewModel,
    onFrameSelected: (Float) -> Unit
) {
    val frames = listOf(
        1f to "1:1",
        16f / 9f to "16:9",
        4f / 3f to "4:3",
        3f / 2f to "3:2"
    )

    LazyRow(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        items(frames) { (aspectRatio, label) ->
            Button(
                onClick = {
                    onFrameSelected(aspectRatio)
                    bottomSheetViewModel.closeCropSheet()
                },
                shape = CircleShape,
                modifier = Modifier
                    .padding(5.dp)
                    .size(60.dp)
            ) {
                Text(label)
            }
        }
    }
}
