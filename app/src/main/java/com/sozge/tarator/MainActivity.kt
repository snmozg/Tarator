package com.sozge.tarator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
    Scaffold(topBar = { AppBar() }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            //AppBar()
            Spacer(modifier = Modifier.height(8.dp))
            TaratorIcon()
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaratorTheme {
        MainScreen()
    }
}