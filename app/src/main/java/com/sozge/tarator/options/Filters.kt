package com.sozge.tarator.options

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.sozge.tarator.FilterViewModel
import com.sozge.tarator.ImageViewModel
import com.sozge.tarator.R
import com.sozge.tarator.data.CustomImage
import com.sozge.tarator.data.DataCardSection

val filterCards = listOf(

    DataCardSection(
        R.drawable.applicationicon,
        "None", 0
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "BlackAndWhite", 1
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "High Saturation", 9
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "Sepia", 2
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "Retro", 3
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "Frosted Glow", 4
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "Moonbeam", 5
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "OceanBreeze", 6
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "Cinematic", 7
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "Vignette", 8
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "Sunset", 11
    ),
    DataCardSection(
        R.drawable.applicationicon,
    "Light Leak", 10
)

)

@Composable
fun FilterSection(imageViewModel: ImageViewModel, filterViewModel: FilterViewModel) {
    LazyRow {
        itemsIndexed(filterCards) { index, item ->
            FilterCardItem(index, item, imageViewModel, filterViewModel)
        }
    }
}

@Composable
fun FilterCardItem(index: Int, item: DataCardSection, imageViewModel: ImageViewModel, filterViewModel: FilterViewModel) {
    val context = LocalContext.current
    val image = imageViewModel.myImage.value
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
                    if (item.id == 0) {
                        CustomImage(
                            viewModel = imageViewModel,
                            colorFilter = ColorFilter.colorMatrix(ColorMatrix())
                        )
                        {
                            filterViewModel.deleteFilter()
                        }
                    }
                    if (item.id == 1) {
                        CustomImage(
                            viewModel = imageViewModel,
                            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                                setToSaturation(
                                    0f
                                )
                            })
                        )
                        {
                            filterViewModel.updateFilter(
                                ColorFilter.colorMatrix(ColorMatrix().apply {
                                    setToSaturation(
                                        0f
                                    )
                                })
                            )
                        }
                    }
                    if (item.id == 2) {
                        CustomImage(
                            viewModel = imageViewModel,
                            colorFilter = ColorFilter.colorMatrix(
                                ColorMatrix(
                                    floatArrayOf(
                                        0.393f, 0.769f, 0.189f, 0f, 0f,
                                        0.349f, 0.686f, 0.168f, 0f, 0f,
                                        0.272f, 0.534f, 0.131f, 0f, 0f,
                                        0f, 0f, 0f, 1f, 0f
                                    )
                                )
                            )
                        ) {
                            filterViewModel.updateFilter(ColorFilter.colorMatrix(
                                ColorMatrix(floatArrayOf(
                                    0.393f, 0.769f, 0.189f, 0f, 0f,
                                    0.349f, 0.686f, 0.168f, 0f, 0f,
                                    0.272f, 0.534f, 0.131f, 0f, 0f,
                                    0f, 0f, 0f, 1f, 0f
                                )
                                )
                            ))
                        }
                    }
                    if (item.id == 3) {
                        CustomImage(
                            viewModel = imageViewModel,
                            colorFilter = ColorFilter.colorMatrix(
                                ColorMatrix(
                                    floatArrayOf(
                                        1.2f, 0.3f, 0.1f, 0f, -30f,   // Kırmızı kanal (sıcak tonlar)
                                        0.2f, 1.0f, 0.2f, 0f, -20f,   // Yeşil kanal
                                        0.1f, 0.2f, 0.8f, 0f, -10f,   // Mavi kanal (hafif solukluk)
                                        0f, 0f, 0f, 1f, 0f            // Alpha (şeffaflık)
                                    )


                                )
                            )
                        ) {
                            filterViewModel.updateFilter(ColorFilter.colorMatrix(
                                ColorMatrix(floatArrayOf(
                                    1.2f, 0.3f, 0.1f, 0f, -30f,   // Kırmızı kanal (sıcak tonlar)
                                    0.2f, 1.0f, 0.2f, 0f, -20f,   // Yeşil kanal
                                    0.1f, 0.2f, 0.8f, 0f, -10f,   // Mavi kanal (hafif solukluk)
                                    0f, 0f, 0f, 1f, 0f            // Alpha (şeffaflık)
                                )


                                )
                            ))
                        }
                    }
                    if (item.id == 4) {
                        CustomImage(
                            viewModel = imageViewModel,
                            colorFilter = ColorFilter.colorMatrix(
                                ColorMatrix(
                                    floatArrayOf(
                                        1.1f, 0f, 0f, 0f, 20f,
                                        0f, 1.2f, 0f, 0f, 20f,
                                        0f, 0f, 1.4f, 0f, -30f,
                                        0f, 0f, 0f, 1f, 0f
                                    )
                                )
                            )
                        ) {
                            filterViewModel.updateFilter(ColorFilter.colorMatrix(
                                    ColorMatrix(
                                        floatArrayOf(
                                            1.1f, 0f, 0f, 0f, 20f,
                                            0f, 1.2f, 0f, 0f, 20f,
                                            0f, 0f, 1.4f, 0f, -30f,
                                            0f, 0f, 0f, 1f, 0f
                                        )
                                    )
                                    ))
                        }
                    }
                    if (item.id == 5) {
                        CustomImage(
                            viewModel = imageViewModel,
                            colorFilter = ColorFilter.colorMatrix(
                                ColorMatrix(
                                    floatArrayOf(
                                        0.8f, 0f, 0f, 0f, 0f,    // Kırmızı kanalı azalt
                                        0f, 0.8f, 0f, 0f, 0f,    // Yeşil kanalı azalt
                                        0f, 0f, 1.5f, 0f, 0f,    // Mavi kanalı artır
                                        0f, 0f, 0f, 1f, 0f       // Alpha (şeffaflık) değiştirme
                                    )
                                )
                            )
                        ) {
                            filterViewModel.updateFilter(ColorFilter.colorMatrix(
                                ColorMatrix(
                                    floatArrayOf(
                                        0.8f, 0f, 0f, 0f, 0f,    // Kırmızı kanalı azalt
                                        0f, 0.8f, 0f, 0f, 0f,    // Yeşil kanalı azalt
                                        0f, 0f, 1.5f, 0f, 0f,    // Mavi kanalı artır
                                        0f, 0f, 0f, 1f, 0f       // Alpha (şeffaflık) değiştirme
                                    )
                                )
                            ))
                        }
                    }
                    if (item.id == 6) {
                        CustomImage(
                            viewModel = imageViewModel,
                            colorFilter = ColorFilter.colorMatrix(
                                ColorMatrix(
                                    floatArrayOf(
                                        1f, 0f, 0f, 0f, -30f, // Kırmızı kanal için hafif karartma
                                        0f, 1f, 0f, 0f, -30f, // Yeşil kanal için hafif karartma
                                        0f, 0f, 1f, 0f, -30f, // Mavi kanal için hafif karartma
                                        0f, 0f, 0f, 1f, 0f     // Alpha (şeffaflık) değiştirme
                                    )
                                )
                            )
                        ) {
                            filterViewModel.updateFilter(ColorFilter.colorMatrix(
                                ColorMatrix(
                                    floatArrayOf(
                                        1f, 0f, 0f, 0f, -30f, // Kırmızı kanal için hafif karartma
                                        0f, 1f, 0f, 0f, -30f, // Yeşil kanal için hafif karartma
                                        0f, 0f, 1f, 0f, -30f, // Mavi kanal için hafif karartma
                                        0f, 0f, 0f, 1f, 0f     // Alpha (şeffaflık) değiştirme
                                    )
                                )
                            ))
                        }
                    }
                    if (item.id == 7) {
                        CustomImage(
                            viewModel = imageViewModel,
                            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                                setToSaturation(
                                    2f
                                )
                            } // Doygunluğu 2 kat artırır
                            )) {
                            filterViewModel.updateFilter(ColorFilter.colorMatrix(ColorMatrix().apply {
                                setToSaturation(
                                    2f
                                )
                            } // Doygunluğu 2 kat artırır
                            ))
                        }
                    }
                    if (item.id == 8) {
                        CustomImage(viewModel = imageViewModel, colorFilter = ColorFilter.colorMatrix(
                            ColorMatrix(
                                floatArrayOf(
                                    1.2f, 0f, 0f, 0f, 30f,   // Kırmızı kanal
                                    0f, 1f, 0f, 0f, 10f,     // Yeşil kanal
                                    0f, 0f, 1f, 0f, 0f,      // Mavi kanal
                                    0f, 0f, 0f, 1f, 0f       // Alpha (şeffaflık)
                                )
                            )
                        )) {filterViewModel.updateFilter(ColorFilter.colorMatrix(
                            ColorMatrix(
                                floatArrayOf(
                                    1.2f, 0f, 0f, 0f, 30f,   // Kırmızı kanal
                                    0f, 1f, 0f, 0f, 10f,     // Yeşil kanal
                                    0f, 0f, 1f, 0f, 0f,      // Mavi kanal
                                    0f, 0f, 0f, 1f, 0f       // Alpha (şeffaflık)
                                )
                            )
                        ))
                        }
                    }
                    if (item.id == 9) {
                        CustomImage(viewModel = imageViewModel,
                            colorFilter =ColorFilter.colorMatrix(
                            ColorMatrix(
                                floatArrayOf(
                                    0.8f, 0f, 0f, 0f, 0f,   // Kırmızı kanal
                                    0f, 0.8f, 0f, 0f, 0f,   // Yeşil kanal
                                    0f, 0f, 0.8f, 0f, 0f,   // Mavi kanal
                                    0f, 0f, 0f, 1.2f, 0f    // Alpha (şeffaflık)
                                )
                            )
                        ) ) {filterViewModel.updateFilter(ColorFilter.colorMatrix(
                            ColorMatrix(
                                floatArrayOf(
                                    0.8f, 0f, 0f, 0f, 0f,   // Kırmızı kanal
                                    0f, 0.8f, 0f, 0f, 0f,   // Yeşil kanal
                                    0f, 0f, 0.8f, 0f, 0f,   // Mavi kanal
                                    0f, 0f, 0f, 1.2f, 0f    // Alpha (şeffaflık)
                                )
                            )
                        ))
                        }
                    }
                    if (item.id == 10) {
                        CustomImage(viewModel = imageViewModel, colorFilter =ColorFilter.colorMatrix(
                            ColorMatrix(
                                floatArrayOf(
                                    -1f, 0f, 0f, 0f, 255f,
                                    0f, -1f, 0f, 0f, 255f,
                                    0f, 0f, -1f, 0f, 255f,
                                    0f, 0f, 0f, 1f, 0f
                                )
                            )
                        ) ) {filterViewModel.updateFilter(ColorFilter.colorMatrix(
                            ColorMatrix(
                                floatArrayOf(
                                    -1f, 0f, 0f, 0f, 255f,
                                    0f, -1f, 0f, 0f, 255f,
                                    0f, 0f, -1f, 0f, 255f,
                                    0f, 0f, 0f, 1f, 0f
                                )
                            )
                        ))
                        }
                    }
                    if (item.id == 11) {
                        CustomImage(viewModel = imageViewModel,
                            colorFilter = ColorFilter.colorMatrix(
                            ColorMatrix(
                                floatArrayOf(
                                    1.2f, 0f, 0f, 0f, 50f,   // Kırmızı kanal (gün batımı etkisi)
                                    0f, 0.8f, 0f, 0f, 20f,    // Yeşil kanal
                                    0f, 0f, 0.6f, 0f, 0f,    // Mavi kanal
                                    0f, 0f, 0f, 1f, 0f       // Alpha (şeffaflık)
                                )
                            )
                        )) {filterViewModel.updateFilter(ColorFilter.colorMatrix(
                            ColorMatrix(
                                floatArrayOf(
                                    1.2f, 0f, 0f, 0f, 50f,   // Kırmızı kanal (gün batımı etkisi)
                                    0f, 0.8f, 0f, 0f, 20f,    // Yeşil kanal
                                    0f, 0f, 0.6f, 0f, 0f,    // Mavi kanal
                                    0f, 0f, 0f, 1f, 0f       // Alpha (şeffaflık)
                                )
                            )
                        ))

                        }
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