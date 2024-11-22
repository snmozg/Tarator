package com.sozge.tarator.options

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.sozge.tarator.ImageViewModel
import com.sozge.tarator.R
import com.sozge.tarator.data.DataCardSection

val filterCards = listOf(
    DataCardSection(
        R.drawable.applicationicon,
        "BlackAndWhite"
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "Vibrant"
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "Retro"
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "Sunset"
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "OceanBreeze"
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "Cinematic"
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "Vignette"
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "High Saturation"
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "Light Leak"
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "Moonbeam"
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "Inverted Colors"
    )

)

@Composable
fun FilterSection(viewModel: ImageViewModel) {
    LazyRow {
        itemsIndexed(filterCards) { index, item ->
            FilterCardItem(index, item, viewModel)
        }
    }
}

@Composable
fun FilterCardItem(index: Int, item: DataCardSection, viewModel: ImageViewModel) {
    val context = LocalContext.current
    val image = viewModel.myImage.value
    val text = item.text

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
                image?.let {
                    if (item.text == "BlackAndWhite") {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(context).data(it).build()
                            ),
                            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(600.dp)
                                .padding(10.dp)
                                .clickable(
                                    enabled = true,
                                    onClickLabel = "Clickable Image",
                                    onClick = {
                                    }
                                )
                        )
                    }
                    if (item.text == "Vibrant") {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(context).data(it).build()
                            ),
                            colorFilter = ColorFilter.colorMatrix(ColorMatrix(
                                floatArrayOf(
                                    2f, 0f, 0f, 0f, 0f,    // Kırmızı kanal (doygunluğu artır)
                                    0f, 2f, 0f, 0f, 0f,    // Yeşil kanal (doygunluğu artır)
                                    0f, 0f, 2f, 0f, 0f,    // Mavi kanal (doygunluğu artır)
                                    0f, 0f, 0f, 1f, 0f     // Alpha (şeffaflık) değiştirme
                                )
                            )),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(600.dp)
                                .padding(10.dp)
                                .clickable(
                                    enabled = true,
                                    onClickLabel = "Clickable Image",
                                    onClick = {
                                    }
                                )
                        )
                    }
                    if (item.text == "Retro") {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(context).data(it).build()
                            ),
                            colorFilter = ColorFilter.colorMatrix(ColorMatrix(
                                floatArrayOf(
                                    1.5f, 0f, 0f, 0f, -20f, // Kırmızı kanal
                                    0f, 1.2f, 0f, 0f, 10f,   // Yeşil kanal
                                    0f, 0f, 1.3f, 0f, -10f,  // Mavi kanal
                                    0f, 0f, 0f, 1f, 0f       // Alpha (şeffaflık) değiştirme
                                )
                            )),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(600.dp)
                                .padding(10.dp)
                                .clickable(
                                    enabled = true,
                                    onClickLabel = "Clickable Image",
                                    onClick = {
                                    }
                                )
                        )
                    }
                    if (item.text == "Cinematic") {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(context).data(it).build()
                            ),
                            colorFilter = ColorFilter.colorMatrix(ColorMatrix(
                                floatArrayOf(
                                1.1f, 0f, 0f, 0f, 20f,   // Kırmızı kanal (altın tonları için)
                                0f, 1.2f, 0f, 0f, 20f,   // Yeşil kanal
                                0f, 0f, 1.4f, 0f, -30f,  // Mavi kanal (soğuk mavi tonları)
                                0f, 0f, 0f, 1f, 0f       // Alpha (şeffaflık)
                            )
                            )),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(600.dp)
                                .padding(10.dp)
                                .clickable(
                                    enabled = true,
                                    onClickLabel = "Clickable Image",
                                    onClick = {
                                    }
                                )
                        )
                    }
                    if (item.text == "OceanBreeze") {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(context).data(it).build()
                            ),
                            colorFilter = ColorFilter.colorMatrix(ColorMatrix(
                                floatArrayOf(
                                    0.8f, 0f, 0f, 0f, 0f,    // Kırmızı kanalı azalt
                                    0f, 0.8f, 0f, 0f, 0f,    // Yeşil kanalı azalt
                                    0f, 0f, 1.5f, 0f, 0f,    // Mavi kanalı artır
                                    0f, 0f, 0f, 1f, 0f       // Alpha (şeffaflık) değiştirme
                                )
                            )),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(600.dp)
                                .padding(10.dp)
                                .clickable(
                                    enabled = true,
                                    onClickLabel = "Clickable Image",
                                    onClick = {
                                    }
                                )
                        )
                    }
                    if (item.text == "Vignette") {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(context).data(it).build()
                            ),
                            colorFilter = ColorFilter.colorMatrix(ColorMatrix(
                                floatArrayOf(
                                    1f, 0f, 0f, 0f, -30f, // Kırmızı kanal için hafif karartma
                                    0f, 1f, 0f, 0f, -30f, // Yeşil kanal için hafif karartma
                                    0f, 0f, 1f, 0f, -30f, // Mavi kanal için hafif karartma
                                    0f, 0f, 0f, 1f, 0f     // Alpha (şeffaflık) değiştirme
                                )
                            )),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(600.dp)
                                .padding(10.dp)
                                .clickable(
                                    enabled = true,
                                    onClickLabel = "Clickable Image",
                                    onClick = {
                                    }
                                )
                        )
                    }
                    if (item.text == "High Saturation") {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(context).data(it).build()
                            ),
                            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(2f) } // Doygunluğu 2 kat artırır
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(600.dp)
                                .padding(10.dp)
                                .clickable(
                                    enabled = true,
                                    onClickLabel = "Clickable Image",
                                    onClick = {
                                    }
                                )
                        )
                    }
                    if (item.text == "Light Leak") {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(context).data(it).build()
                            ),
                            colorFilter = ColorFilter.colorMatrix(ColorMatrix(floatArrayOf(
                                1.2f, 0f, 0f, 0f, 30f,   // Kırmızı kanal
                                0f, 1f, 0f, 0f, 10f,     // Yeşil kanal
                                0f, 0f, 1f, 0f, 0f,      // Mavi kanal
                                0f, 0f, 0f, 1f, 0f       // Alpha (şeffaflık)
                            ))
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(600.dp)
                                .padding(10.dp)
                                .clickable(
                                    enabled = true,
                                    onClickLabel = "Clickable Image",
                                    onClick = {
                                    }
                                )
                        )
                    }
                    if (item.text == "Moonbeam") {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(context).data(it).build()
                            ),
                            colorFilter = ColorFilter.colorMatrix(ColorMatrix(
                                floatArrayOf(
                                    0.8f, 0f, 0f, 0f, 0f,   // Kırmızı kanal
                                    0f, 0.8f, 0f, 0f, 0f,   // Yeşil kanal
                                    0f, 0f, 0.8f, 0f, 0f,   // Mavi kanal
                                    0f, 0f, 0f, 1.2f, 0f    // Alpha (şeffaflık)
                                )
                            )),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(600.dp)
                                .padding(10.dp)
                                .clickable(
                                    enabled = true,
                                    onClickLabel = "Clickable Image",
                                    onClick = {
                                    }
                                )
                        )
                    }
                    if (item.text == "Inverted Colors") {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(context).data(it).build()
                            ),
                            colorFilter = ColorFilter.colorMatrix(ColorMatrix(
                                floatArrayOf(
                                    -1f, 0f, 0f, 0f, 255f,
                                    0f, -1f, 0f, 0f, 255f,
                                    0f, 0f, -1f, 0f, 255f,
                                    0f, 0f, 0f, 1f, 0f
                                )
                            )),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(600.dp)
                                .padding(10.dp)
                                .clickable(
                                    enabled = true,
                                    onClickLabel = "Clickable Image",
                                    onClick = {
                                    }
                                )
                        )
                    }
                    if (item.text == "Sunset") {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(context).data(it).build()
                            ),
                            colorFilter = ColorFilter.colorMatrix(ColorMatrix(
                                floatArrayOf(
                                    1.2f, 0f, 0f, 0f, 50f,   // Kırmızı kanal (gün batımı etkisi)
                                    0f, 0.8f, 0f, 0f, 20f,    // Yeşil kanal
                                    0f, 0f, 0.6f, 0f, 0f,    // Mavi kanal
                                    0f, 0f, 0f, 1f, 0f       // Alpha (şeffaflık)
                                )
                            )),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(600.dp)
                                .padding(10.dp)
                                .clickable(
                                    enabled = true,
                                    onClickLabel = "Clickable Image",
                                    onClick = {
                                    }
                                )
                        )
                    }


                } ?: Image(
                    painterResource(item.image),
                    contentDescription = "logos",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier

                )
            }
        }
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = text,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
        )
    }
}