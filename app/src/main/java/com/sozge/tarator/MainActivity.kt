package com.sozge.tarator

import android.os.Bundle
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
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    Scaffold(topBar = { AppBar() },
        modifier = Modifier.fillMaxSize()) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            //AppBar()
            Spacer(modifier = Modifier.height(8.dp))
            TaratorIcon()
            Spacer(modifier = Modifier.height(60.dp))
            LetsEditButton()
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
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
fun TaratorIcon() {
    Image(
        painter = painterResource(id = R.drawable.applicationicon),
        contentDescription = stringResource(id = R.string.application_icon),
        alignment = Alignment.Center,
        modifier = Modifier.padding(start = 10.dp, end = 10.dp)
    )
}

@Composable
fun LetsEditButton() {
    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 50.dp, end = 50.dp)
            .height(80.dp)
            .width(200.dp)
    ) {
        Text(text = "Edit")

    }
}

@Composable
fun FeedBackButton() {
    Button(
        onClick = { },
        modifier = Modifier
            .height(80.dp)
            .width(150.dp).padding(5.dp)
    ) {
        Text(text = "Feed Back")

    }
}

@Composable
fun SettingsButton() {
    Button(
        onClick = { },
        modifier = Modifier
            .height(80.dp)
            .width(150.dp).padding(5.dp)
    ) {
        Text(text = "Settings")

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaratorTheme {
        MainScreen()
    }
}