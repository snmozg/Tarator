package com.sozge.taratornew.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import com.sozge.taratornew.models.ImageViewModel

@Composable
fun EditPageImage(
    imageViewModel: ImageViewModel,
    hasPermission: MutableState<Boolean>,
    galleryLauncher: () -> Unit,
    permissionLauncher: () -> Unit
) {
    val context = LocalContext.current

    imageViewModel.myImage.value?.let {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(context).data(it).build()
            ),
            //colorFilter = imageViewModel.filter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .padding(10.dp)
                .clickable {}
        )
    } ?: Image(
        imageVector = Icons.Rounded.AddCircleOutline,
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