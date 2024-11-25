package com.sozge.tarator.options

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sozge.tarator.R
import com.sozge.tarator.data.DataCardSection

val brushCards = listOf(
    DataCardSection(
        R.drawable.orangebrush,
        "Brush", id = 1
    ),
    DataCardSection(
        R.drawable.orangeeraser,
        "Eraser", id = 2
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrushSection(index: Int) {
    val card = brushCards[index]
    val text = card.text

    var selectedCardId by remember { mutableStateOf<Int?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true) // Tek bir state burada tanımlanır

    LazyRow {
        items(brushCards.size) { index ->
            BrushCardItem(
                card = brushCards[index],
                isSelected = brushCards[index].id == selectedCardId,
                onCardClick = { clickedCardId ->
                    selectedCardId = if (clickedCardId == selectedCardId) null else clickedCardId
                }
            )
        }
    }

    // Tek ModalBottomSheet burada kullanılır
    selectedCardId?.let { id ->
        ModalBottomSheet(
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.primary,
            onDismissRequest = {
                selectedCardId = null
            },
        ) {
            when (id) {
                1 -> ToolsSection() // Brush içeriği
                2 -> BrushSection(index) // Eraser içeriği
            }
        }
    }
}

@Composable
fun BrushCardItem(
    card: DataCardSection,
    isSelected: Boolean,
    onCardClick: (Int) -> Unit
) {
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
                    painter = painterResource(card.image),
                    contentDescription = card.text,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(25.dp)
                        .clickable(
                            enabled = true,
                            onClickLabel = "Clickable Image",
                            onClick = {
                                onCardClick(card.id)
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



