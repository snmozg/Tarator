package com.sozge.tarator.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Brightness4
import androidx.compose.material.icons.outlined.CleaningServices
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Feedback
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LayersClear
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.WebAssetOff
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.Translate
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sozge.tarator.DrawingViewModel
import com.sozge.tarator.FilterViewModel
import com.sozge.tarator.ImageViewModel
import com.sozge.tarator.bars.AppBar
import com.sozge.tarator.data.DataSettingsCard
import com.sozge.tarator.data.SettingsCard

@Composable
fun SettingsScreen(
    navController: NavController,
    imageViewModel: ImageViewModel,
) {
    val settingsList = listOf<DataSettingsCard>(
        DataSettingsCard(
            imageVector = Icons.Rounded.Translate,
            contentDescription = "Language",
            itemText = "Language",
            onClick = { println("Language Clicked") }
        ),
        DataSettingsCard(
            imageVector = Icons.Outlined.DarkMode,
            contentDescription = "DarkMode",
            itemText = "DarkMode",
            onClick = { println("DarkModeClicked") }
        ),
        DataSettingsCard(
            imageVector = Icons.Outlined.Feedback,
            contentDescription = "Feedback",
            itemText = "FeedBack",
            onClick = { navController.navigate("FeedBackScreen") }
        ),
        DataSettingsCard(
            imageVector = Icons.Outlined.WebAssetOff,
            contentDescription = "ClarWeb",
            itemText = "Clear Cash",
            onClick = { println("clear") }
        ),

        DataSettingsCard(
            imageVector = Icons.Outlined.Security,
            contentDescription = "privacy policy",
            itemText = "Privacy Policy",
            onClick = { println("SecClicked") }
        ),
        DataSettingsCard(
            imageVector = Icons.Outlined.Description,
            contentDescription = "terms of use",
            itemText = "Terms of use",
            onClick = { println("UseClicked") }
        ),
        DataSettingsCard(
            imageVector = Icons.Outlined.Info,
            contentDescription = "Info",
            itemText = "Version information",
            onClick = { println("InfoClicked") }
        ),
    )
    Scaffold(topBar = {
        AppBar(
            navController,
            actionImageVector = Icons.Rounded.Logout,
            actionContentDescription = "Logout",
            isHomeScreen = false,
            viewModel = imageViewModel,
            onClick = {
                println("belirsiz")
            }
        )
    }) { innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Top
            ) {
                items(settingsList.size) { index ->
                    SettingsCard(
                        imageVector = settingsList[index].imageVector,
                        contentDescription = settingsList[index].contentDescription,
                        itemText = settingsList[index].itemText,
                        onClick = settingsList[index].onClick
                    )
                }
            }
        }
    }
}








