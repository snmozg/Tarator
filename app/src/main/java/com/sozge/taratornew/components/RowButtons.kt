package com.sozge.taratornew.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Brush
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.TextFields
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RowButtons(
    onFilterClick: () -> Unit,
    onToolsClick: () -> Unit,
    onBrushClick: () -> Unit,
    onTextClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CustomOptionsButton(
            Icons.Outlined.FilterAlt,
            "Filter Button",
            "FILTERS",
            onClick = { onFilterClick() }
        )
        CustomOptionsButton(
            Icons.Outlined.Tune,
            "Tools Button",
            "TOOLS",
            onClick = { onToolsClick() }
        )
        CustomOptionsButton(
            Icons.Outlined.Brush,
            "Brush Button",
            "BRUSH",
            onClick = { onBrushClick() }
        )

        CustomOptionsButton(
            Icons.Outlined.TextFields,
            "Text Button",
            "TEXT",
            onClick = { onTextClick() }
        )
    }
}
