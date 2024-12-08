package com.sozge.taratornew.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import com.sozge.taratornew.models.FilterViewModel
import com.sozge.taratornew.models.ImageViewModel

@Composable
fun FilterImage(filterId: Int, imageViewModel: ImageViewModel, filterViewModel: FilterViewModel) {
    val colorFilter = when (filterId) {
        0 -> ColorFilter.colorMatrix(ColorMatrix()) // "None" filter
        1 -> ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }) // BlackAndWhite filter
        2 -> ColorFilter.colorMatrix(
            ColorMatrix(
                floatArrayOf(
                    0.393f, 0.769f, 0.189f, 0f, 0f,
                    0.349f, 0.686f, 0.168f, 0f, 0f,
                    0.272f, 0.534f, 0.131f, 0f, 0f,
                    0f, 0f, 0f, 1f, 0f
                )
            )
        ) // Sepia filter
        3 -> ColorFilter.colorMatrix(
            ColorMatrix(
                floatArrayOf(
                    1.2f, 0.3f, 0.1f, 0f, -30f,   // Kırmızı kanal (sıcak tonlar)
                    0.2f, 1.0f, 0.2f, 0f, -20f,   // Yeşil kanal
                    0.1f, 0.2f, 0.8f, 0f, -10f,   // Mavi kanal (hafif solukluk)
                    0f, 0f, 0f, 1f, 0f
                )
            )
        ) // Retro filter
        4 -> ColorFilter.colorMatrix(
            ColorMatrix(
                floatArrayOf(
                    1.1f, 0f, 0f, 0f, 20f,
                    0f, 1.2f, 0f, 0f, 20f,
                    0f, 0f, 1.4f, 0f, -30f,
                    0f, 0f, 0f, 1f, 0f
                )
            )
        ) // Frosted Glow filter
        5 -> ColorFilter.colorMatrix(
            ColorMatrix(
                floatArrayOf(
                    0.8f, 0f, 0f, 0f, 0f,    // Kırmızı kanalı azalt
                    0f, 0.8f, 0f, 0f, 0f,    // Yeşil kanalı azalt
                    0f, 0f, 1.5f, 0f, 0f,    // Mavi kanalı artır
                    0f, 0f, 0f, 1f, 0f
                )
            )
        ) // Moonbeam filter
        6 -> ColorFilter.colorMatrix(
            ColorMatrix(
                floatArrayOf(
                    1f, 0f, 0f, 0f, -30f, // Kırmızı kanal için hafif karartma
                    0f, 1f, 0f, 0f, -30f, // Yeşil kanal için hafif karartma
                    0f, 0f, 1f, 0f, -30f, // Mavi kanal için hafif karartma
                    0f, 0f, 0f, 1f, 0f
                )
            )
        ) // OceanBreeze filter
        7 -> ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(2f) }) // High Saturation filter
        8 -> ColorFilter.colorMatrix(
            ColorMatrix(
                floatArrayOf(
                    1.2f, 0f, 0f, 0f, 30f,   // Kırmızı kanal
                    0f, 1f, 0f, 0f, 10f,     // Yeşil kanal
                    0f, 0f, 1f, 0f, 0f,      // Mavi kanal
                    0f, 0f, 0f, 1f, 0f
                )
            )
        ) // Cinematic filter
        9 -> ColorFilter.colorMatrix(
            ColorMatrix(
                floatArrayOf(
                    0.8f, 0f, 0f, 0f, 0f,   // Kırmızı kanal
                    0f, 0.8f, 0f, 0f, 0f,   // Yeşil kanal
                    0f, 0f, 0.8f, 0f, 0f,   // Mavi kanal
                    0f, 0f, 0f, 1.2f, 0f
                )
            )
        ) // Vignette filter
        10 -> ColorFilter.colorMatrix(
            ColorMatrix(
                floatArrayOf(
                    -1f, 0f, 0f, 0f, 255f,
                    0f, -1f, 0f, 0f, 255f,
                    0f, 0f, -1f, 0f, 255f,
                    0f, 0f, 0f, 1f, 0f
                )
            )
        ) // Light Leak filter
        11 -> ColorFilter.colorMatrix(
            ColorMatrix(
                floatArrayOf(
                    1.2f, 0f, 0f, 0f, 50f,   // Kırmızı kanal (gün batımı etkisi)
                    0f, 0.8f, 0f, 0f, 20f,    // Yeşil kanal
                    0f, 0f, 0.6f, 0f, 0f,    // Mavi kanal
                    0f, 0f, 0f, 1f, 0f
                )
            )
        ) // Sunset filter
        else -> ColorFilter.colorMatrix(ColorMatrix())
    }

    CustomCardImage(viewModel = imageViewModel, colorFilter = colorFilter) {
        filterViewModel.updateFilter(colorFilter)
    }
}
