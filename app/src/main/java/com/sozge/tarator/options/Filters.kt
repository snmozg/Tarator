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
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.sozge.tarator.ImageViewModel
import com.sozge.tarator.R
import com.sozge.tarator.data.DataCardSection
import java.util.zip.DataFormatException

val filterCards = listOf(
    DataCardSection(
        R.drawable.applicationicon,
        "Bear"
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "Boar"
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "Camel"
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "Cat"
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

                viewModel.myImage.value.let {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(context).data(it).build()
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(600.dp)
                            .padding(10.dp)
                            .clickable {
                            }
                    )
                }
                /*
                Image(
                    painterResource(image),
                    contentDescription = "logos",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clickable(
                            enabled = true,
                            onClickLabel = "Clickable Image",
                            onClick = {
                                println(item.text)
                            }
                        )
                )

                 */
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