package com.sozge.tarator.data

import androidx.compose.ui.graphics.vector.ImageVector

data class DataSettingsCard(
    val imageVector: ImageVector,
    val contentDescription: String,
    val itemText: String,
    val onClick: () -> Unit
)