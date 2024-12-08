package com.sozge.taratornew.components.filters

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.sozge.taratornew.models.ImageViewModel

@Composable
fun FilterCardImage(
    viewModel: ImageViewModel,
    colorFilter: ColorFilter,
    onClick: () -> Unit,
) {
    val image = viewModel.myImage.value
    val context = LocalContext.current

    image?.let { img ->
        Image(
            contentDescription = "image",
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(context).data(img).build()
            ),
            colorFilter = colorFilter,
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .padding(10.dp)
                .clickable(
                    enabled = true,
                    onClickLabel = "Clickable Image",
                    onClick = { onClick()
                    }
                )
        )
    }
}