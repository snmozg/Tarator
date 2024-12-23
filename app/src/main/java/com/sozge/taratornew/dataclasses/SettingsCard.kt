package com.sozge.taratornew.dataclasses

import androidx.compose.ui.graphics.vector.ImageVector

data class SettingsCard(
    val imageVector: ImageVector,
    val contentDescription: String,
    val itemText: String,
    val summary: String,
    val onClick:  () -> Unit
)
