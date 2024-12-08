package com.sozge.taratornew.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RowButtons(
    onFilterClick: () -> Unit,
    onToolsClick: () -> Unit,
    onBrushClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CustomOptionsButton(
            Icons.Rounded.FilterAlt,
            "Filter Button",
            "FILTERS",
            onClick = { onFilterClick() }
        )
        CustomOptionsButton(
            Icons.Rounded.Crop,
            "Tools Button",
            "TOOLS",
            onClick = { onToolsClick() }
        )
        CustomOptionsButton(
            Icons.Rounded.Brush,
            "Brush Button",
            "BRUSH",
            onClick = { onBrushClick() }
        )
    }
}
