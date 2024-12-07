package com.sozge.taratornew

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sozge.taratornew.models.ImageViewModel
import com.sozge.taratornew.pages.EditPage
import com.sozge.taratornew.pages.FeedbackPage
import com.sozge.taratornew.pages.HomePage
import com.sozge.taratornew.pages.SettingsPage
import com.sozge.taratornew.ui.theme.TaratorNewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val imageViewModel: ImageViewModel = viewModel()

            TaratorNewTheme {
                NavHost(
                    navController = navController,
                    startDestination = "HomePage"
                ) {
                    composable(route = "HomePage") {
                        HomePage(navController, imageViewModel)
                    }
                    composable(route = "EditPage") {
                        EditPage(navController)
                    }
                    composable(route = "FeedbackPage") {
                        FeedbackPage(navController)
                    }
                    composable(route = "SettingsPage") {
                        SettingsPage(navController)
                    }
                }
            }
        }
    }
}