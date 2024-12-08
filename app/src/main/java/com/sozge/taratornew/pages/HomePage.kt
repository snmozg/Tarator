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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sozge.taratornew.R
import com.sozge.taratornew.components.CustomButton
import com.sozge.taratornew.components.HeaderBar
import com.sozge.taratornew.models.ImageViewModel

@Composable
fun HomePage(
    navController: NavController,
    imageViewModel: ImageViewModel,
) {
    val activity = (LocalContext.current as? Activity)
    Scaffold(
        topBar = {
            HeaderBar(
                navController,
                actionImageVector = Icons.Rounded.Logout,
                actionContentDescription = "logout",
                isBackButtonEnable = false,
                imageViewModel = imageViewModel,
                onClick = {
                    activity?.finish()
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(top = 24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.applicationicon),
                contentDescription = "icon",
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit,
                modifier = Modifier.padding()
            )
            Spacer(modifier = Modifier.height(24.dp))

            CustomButton(
                text = "Let's Edit!",
                onClick = { navController.navigate("EditPage") },
                isBigButton = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CustomButton(
                    text = "Feedback",
                    onClick = { navController.navigate("FeedbackPage") },
                    isBigButton = false
                )
                CustomButton(
                    text = "Settings",
                    onClick = { navController.navigate("SettingsPage") },
                    isBigButton = false
                )
            }
        }
    }
}