package com.sozge.tarator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    icon: ImageVector,
    contentD: String,
    text: String,
    onClick: () -> Unit,
) {

    Column(
        modifier = Modifier
            .padding(2.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(
            onClick = onClick,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentD,
            )
        }
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}