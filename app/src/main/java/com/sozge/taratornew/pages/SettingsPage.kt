package com.sozge.taratornew.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Feedback
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.WebAssetOff
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.Translate
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sozge.taratornew.components.HeaderBar
import com.sozge.taratornew.components.SettingsCard
import com.sozge.taratornew.datas.settingsDataCard
import com.sozge.taratornew.models.DrawingViewModel
import com.sozge.taratornew.models.FilterViewModel
import com.sozge.taratornew.models.ImageViewModel

@Composable
fun SettingsPage(
    navController: NavController,
    imageViewModel: ImageViewModel,
    filterViewModel: FilterViewModel,
    drawingViewModel: DrawingViewModel
) {
    val settingsList = listOf<settingsDataCard>(
        settingsDataCard(
            imageVector = Icons.Rounded.Translate,
            contentDescription = "Language",
            itemText = "Language",
            onClick = { println("Language Clicked") }
        ),
        settingsDataCard(
            imageVector = Icons.Outlined.DarkMode,
            contentDescription = "DarkMode",
            itemText = "DarkMode",
            onClick = { println("DarkModeClicked") }
        ),
        settingsDataCard(
            imageVector = Icons.Outlined.Feedback,
            contentDescription = "Feedback",
            itemText = "FeedBack",
            onClick = { navController.navigate("FeedBackPage") }
        ),
        settingsDataCard(
            imageVector = Icons.Outlined.WebAssetOff,
            contentDescription = "ClarWeb",
            itemText = "Clear Cash",
            onClick = { println("clear") }
        ),

        settingsDataCard(
            imageVector = Icons.Outlined.Security,
            contentDescription = "privacy policy",
            itemText = "Privacy Policy",
            onClick = { println("SecClicked") }
        ),
        settingsDataCard(
            imageVector = Icons.Outlined.Description,
            contentDescription = "terms of use",
            itemText = "Terms of use",
            onClick = { println("UseClicked") }
        ),
        settingsDataCard(
            imageVector = Icons.Outlined.Info,
            contentDescription = "Info",
            itemText = "Version information",
            onClick = { println("InfoClicked") }
        ),
    )
    Scaffold(topBar = {
        HeaderBar(
            navController,
            actionImageVector = Icons.Rounded.Logout,
            actionContentDescription = "Logout",
            isBackButtonEnable = false,
            imageViewModel = imageViewModel,
            filterViewModel = filterViewModel,
            drawingViewModel = drawingViewModel,
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