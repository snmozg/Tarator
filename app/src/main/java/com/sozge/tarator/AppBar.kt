@file:Suppress("UNUSED_EXPRESSION")

package com.sozge.tarator

import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sozge.tarator.ui.theme.TaratorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Box(
                modifier = Modifier.fillMaxWidth().padding(start = 100.dp)


            ) { Text("TARATOR") }

        },
        navigationIcon = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Rounded.Logout,
                    contentDescription = "Logout"
                )
            }
        },
    )

}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    TaratorTheme {
        AppBar()
    }
}