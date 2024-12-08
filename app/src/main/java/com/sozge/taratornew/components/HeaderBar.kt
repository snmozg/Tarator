package com.sozge.taratornew.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.sozge.taratornew.models.DrawingViewModel
import com.sozge.taratornew.models.FilterViewModel
import com.sozge.taratornew.models.ImageViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderBar(
    navController: NavController,
    actionImageVector: ImageVector,
    actionContentDescription: String,
    isBackButtonEnable: Boolean,
    imageViewModel: ImageViewModel,
    drawingViewModel: DrawingViewModel,
    filterViewModel: FilterViewModel,
    onClick: () -> Unit,
) {
    TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.background,
        titleContentColor = MaterialTheme.colorScheme.primary,
    ), title = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(), contentAlignment = Alignment.Center
        ) {
            Text(
                "TARATOR", textAlign = TextAlign.Center
            )
        }
    }, navigationIcon = {
        if (isBackButtonEnable) {
            IconButton(onClick = {
                navController.popBackStack()
                imageViewModel.deleteImage()
                filterViewModel.deleteFilter()
                drawingViewModel.clearDrawing()

            }) {
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }, actions = {
        IconButton(
            onClick = onClick
        ) {
            Icon(
                imageVector = actionImageVector,
                contentDescription = actionContentDescription,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    })
}