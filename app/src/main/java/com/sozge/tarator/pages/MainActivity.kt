package com.sozge.tarator.pages

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material.icons.rounded.Accessibility
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sozge.tarator.R
import com.sozge.tarator.bars.AppBar
import com.sozge.tarator.bars.BottomNavigationBar
import com.sozge.tarator.ui.theme.TaratorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaratorTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            AppBar(
                actionImageVector = Icons.Rounded.Logout,
                actionContentDescription = "logout",
                isHomeScreen = true
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
            LetsEditButton()
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                FeedBackButton()
                SettingsButton()
            }
        }
    }
}

@Composable
fun damn(){

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
fun LetsEditButton() {
    Button(
        onClick = { },
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
fun FeedBackButton() {
    Button(
        onClick = { },
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaratorTheme {
        MainScreen()
    }
}