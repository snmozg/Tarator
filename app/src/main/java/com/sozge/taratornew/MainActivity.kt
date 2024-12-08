package com.sozge.taratornew

import BottomSheetViewModel
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
import com.sozge.taratornew.models.DrawingViewModel
import com.sozge.taratornew.models.FilterViewModel
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
            val filterViewmodel: FilterViewModel = viewModel()
            val bottomSheetViewModel: BottomSheetViewModel = viewModel()
            val drawingViewModel: DrawingViewModel = viewModel()

            TaratorNewTheme {
                NavHost(
                    navController = navController,
                    startDestination = "HomePage"
                ) {
                    composable(route = "HomePage") {
                        HomePage(navController, imageViewModel, drawingViewModel, filterViewmodel)
                    }
                    composable(route = "EditPage") {
                        EditPage(navController, imageViewModel, bottomSheetViewModel, filterViewmodel, drawingViewModel)
                    }
                    composable(route = "FeedbackPage") {
                        FeedbackPage(navController, imageViewModel, drawingViewModel, filterViewmodel)
                    }
                    composable(route = "SettingsPage") {
                        SettingsPage(navController, drawingViewModel, filterViewmodel, imageViewModel)
                    }
                }
            }
        }
    }
}