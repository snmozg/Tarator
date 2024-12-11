package com.sozge.taratornew.dataclasses

import androidx.compose.ui.graphics.vector.ImageVector

data class dataSettingsCard(
    val imageVector: ImageVector,
    val contentDescription: String,
    val itemText: String,
    val onClick: () -> Unit
)
