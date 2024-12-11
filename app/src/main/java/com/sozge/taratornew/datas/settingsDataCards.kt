package com.sozge.taratornew.datas

import androidx.compose.ui.graphics.vector.ImageVector

data class settingsDataCard(
    val imageVector: ImageVector,
    val contentDescription: String,
    val itemText: String,
    val onClick: () -> Unit
)