package com.sozge.taratornew.pages

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Feedback
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.InsertDriveFile
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.PrivacyTip
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sozge.taratornew.components.HeaderBar
import com.sozge.taratornew.components.SettingsCard
import com.sozge.taratornew.datas.settingsDataCard
import com.sozge.taratornew.models.DrawingViewModel
import com.sozge.taratornew.models.FilterViewModel
import com.sozge.taratornew.models.ImageViewModel
import com.sozge.taratornew.components.OptionsCard
import com.sozge.taratornew.utils.com.sozge.taratornew.CustomOptionsCard
import com.sozge.taratornew.utils.com.sozge.taratornew.utils.myFont

@Composable
fun SettingsPage(
    navController: NavController,
    imageViewModel: ImageViewModel,
    drawingViewModel: DrawingViewModel,
    filterViewModel: FilterViewModel
) {
    Scaffold(
        topBar = {
            HeaderBar(navController,
                actionImageVector = Icons.Outlined.Menu,
                actionContentDescription = "save button",
                isBackButtonEnable = true,
                imageViewModel = imageViewModel,
                drawingViewModel = drawingViewModel,
                filterViewModel = filterViewModel,
                onClick = {
                    navController.navigate("SettingsPage")
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val optionsList = listOf<OptionsCard>(
               /* OptionsCard(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "User Information",
                    itemText = "User Information",
                    summary = "View and edit your personal information",
                    onClick = { navController.navigate("UserInformation") }
                ),

                */
                OptionsCard(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "Feedback, Contact",
                    itemText = "Contact",
                    summary = "Send feedback or contact us",
                    onClick = { navController.navigate("FeedbackPage") }
                ),
                OptionsCard(
                    imageVector = Icons.Outlined.PrivacyTip,
                    contentDescription = "Privacy Policy",
                    itemText = "Privacy Policy",
                    summary = "View our privacy policy",
                    onClick = { println("privacy policy") }
                ),
                OptionsCard(
                    imageVector = Icons.Outlined.InsertDriveFile,
                    contentDescription = "Terms of Use",
                    itemText = "Terms of Use",
                    summary = "View our terms of use",
                    onClick = { println("term of use") }
                )
            )

            LazyColumn(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                itemsIndexed(optionsList) { index, item ->
                    CustomOptionsCard(
                        navController = navController,
                        imageVector = optionsList[index].imageVector,
                        contentDescription = optionsList[index].contentDescription,
                        itemText = optionsList[index].itemText,
                        summary = optionsList[index].summary,
                        onClick = optionsList[index].onClick
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Tarator",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = myFont,
                    color = Color.LightGray
                )
            }
        }
    }
}