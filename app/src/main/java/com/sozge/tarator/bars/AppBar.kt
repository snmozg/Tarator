@file:Suppress("UNUSED_EXPRESSION")

package com.sozge.tarator.bars


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.WbSunny
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
import androidx.compose.ui.tooling.preview.Preview
import com.sozge.tarator.ui.theme.TaratorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    actionImageVector: ImageVector,
    actionContentDescription: String,
    isHomeScreen: Boolean
) {
    TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
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
        if (!isHomeScreen) {
            IconButton(onClick = { println("clicked") }) {
                Icon(Icons.Rounded.ArrowBack, contentDescription = "Back")
            }
        }

    }, actions = {
        IconButton(onClick = {  }) {
            Icon(
                imageVector = actionImageVector, contentDescription = actionContentDescription
            )
        }
    })
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    TaratorTheme {

        AppBar(
            actionImageVector = Icons.Rounded.Star,
            actionContentDescription = "star",
            isHomeScreen = false
        )
    }
}