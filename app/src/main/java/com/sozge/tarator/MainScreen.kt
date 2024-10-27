package com.sozge.tarator

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sozge.tarator.bars.AppBar
import com.sozge.tarator.bars.BottomNavigationBar

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = {
            AppBar(
                actionImageVector = Icons.Rounded.Logout,
                actionContentDescription = "logout",
                isHomeScreen = true,
                onClick = {

                }
            )
        },
        bottomBar = { BottomNavigationBar() },
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
            TaratorIcon()
            Spacer(modifier = Modifier.height(24.dp))

            LetsEditButton(navController)

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                FeedBackButton(navController)
                SettingsButton()
            }
        }
    }
}

@Composable
fun TaratorIcon() {
    Image(
        painter = painterResource(id = R.drawable.applicationicon),
        contentDescription = stringResource(id = R.string.application_icon),
        alignment = Alignment.Center,
        contentScale = ContentScale.Fit,
        modifier = Modifier.padding()
    )
}

@Composable
fun LetsEditButton(navController: NavController) {
    Button(
        onClick = {
            navController.navigate("EditPageScreen")
        },
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .padding(start = 50.dp, end = 50.dp)
            .height(100.dp)
            .width(200.dp)
    ) {
        Text(
            text = "Let's edit!",
            fontSize = 18.sp
        )
    }
}

@Composable
fun FeedBackButton(navController: NavController) {
    Button(
        onClick = {
            navController.navigate(("FeedBackScreen"))
        },
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .height(100.dp)
            .width(150.dp)
            .padding(5.dp)
    ) {
        Text(
            text = "Feed Back",
            fontSize = 16.sp
        )

    }
}

@Composable
fun SettingsButton() {
    Button(
        onClick = { },
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .height(100.dp)
            .width(150.dp)
            .padding(5.dp)
    ) {
        Text(
            text = "Settings",
            fontSize = 16.sp
        )

    }
}
