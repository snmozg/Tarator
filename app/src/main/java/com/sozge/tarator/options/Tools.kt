package com.sozge.tarator.options

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sozge.tarator.R
import com.sozge.tarator.data.DataCardSection

val toolsCards = listOf(
    DataCardSection(
        R.drawable.applicationicon,
        "Dog", id = 1
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "Boar", id = 2
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "Camel", id = 3
    ),
    DataCardSection(
        R.drawable.applicationicon,
        "Cat", id = 4
    )
)
@Composable
fun ToolsSection() {
    LazyRow {
        items(toolsCards.size) { index ->
            ToolsCardItem(index)
        }
    }
}

@Composable
fun ToolsCardItem(index: Int) {
    val card = toolsCards[index]
    val image = card.image
    val text = card.text

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
                Image(
                    painterResource(image),
                    contentDescription = "logos",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clickable(
                            enabled = true,
                            onClickLabel = "Clickable Image",
                            onClick = {
                                if (text == "Bear") {
                                    println("Bear Clicked")
                                } else if (text == "Boar") {
                                    println("Boar Clicked")
                                } else if (text == "Camel") {
                                    println("Camel Clicked")
                                } else if (text == "Cat") {
                                    println("Cat Clicked")
                                }


                            }
                        )
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