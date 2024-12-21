package com.sozge.taratornew.pages

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sozge.taratornew.R
import com.sozge.taratornew.components.CustomButton
import com.sozge.taratornew.components.HeaderBar
import com.sozge.taratornew.models.DrawingViewModel
import com.sozge.taratornew.models.FilterViewModel
import com.sozge.taratornew.models.ImageViewModel
import com.sozge.taratornew.utils.com.sozge.taratornew.animations.LottieAnimation
import com.sozge.taratornew.utils.com.sozge.taratornew.utils.myFont

@Composable
fun HomePage(
    navController: NavController,
    imageViewModel: ImageViewModel,
    drawingViewModel: DrawingViewModel,
    filterViewModel: FilterViewModel,
) {
    val activity = (LocalContext.current as? Activity)
    Scaffold(
        topBar = {
            HeaderBar(
                navController,
                actionImageVector = Icons.Outlined.Menu,
                actionContentDescription = "Settings",
                isBackButtonEnable = false,
                imageViewModel = imageViewModel,
                drawingViewModel = drawingViewModel,
                filterViewModel = filterViewModel,
                onClick = {
                    navController.navigate("SettingsPage")
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.padding(start = 40.dp, end = 40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                LottieAnimation(
                    animation = R.raw.lottiecamera,
                    onClick = {  }
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth().padding(2.dp),
                    textAlign = TextAlign.Center,
                    text = "Welcome to Tarator!",
                    fontSize = 38.sp,
                    fontFamily = myFont,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth().padding(2.dp),
                    textAlign = TextAlign.Center,
                    text = "It's time to shape your memories together!",
                    fontSize = 15.sp,
                    fontFamily = myFont,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                CustomButton(
                    containerColor = MaterialTheme.colorScheme.primary,
                    onClick = {
                        navController.navigate("EditPage")
                    },
                    text = "Let's Edit!"
                )
            }
        }
    }
}