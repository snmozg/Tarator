package com.sozge.tarator.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sozge.tarator.DrawingViewModel
import com.sozge.tarator.FilterViewModel
import com.sozge.tarator.ImageViewModel
import com.sozge.tarator.ui.theme.TaratorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val imageViewModel: ImageViewModel = viewModel()
            val filterViewModel: FilterViewModel = viewModel()
            val drawingViewModel: DrawingViewModel = viewModel()
            val navController = rememberNavController()

            TaratorTheme {
                NavHost(
                    navController = navController,
                    startDestination = "MainScreen"
                ) {
                    composable(route = "MainScreen") {
                        MainScreen(
                            navController,
                            imageViewModel= imageViewModel,
                            filterViewModel=filterViewModel,
                            drawingViewModel=drawingViewModel

                            )
                    }
                    composable(route = "EditPageScreen") {
                        EditPageScreen(navController,
                            imageViewModel= imageViewModel,
                            filterViewModel=filterViewModel,
                            drawingViewModel=drawingViewModel
                        )
                    }
                    composable(route = "FeedBackScreen") {
                        FeedBackScreen(navController,
                            imageViewModel= imageViewModel,
                            filterViewModel=filterViewModel,
                            drawingViewModel=drawingViewModel
                        )
                    }
                }
            }


        }
    }
}
/*
@Composable
fun NavController() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "MainScreen"
    ) {
        composable(route = "MainScreen") {
            MainScreen(navController)
        }
        composable(route = "EditPageScreen") {
            EditPageScreen(navController,)
        }
        composable(route = "FeedBackScreen") {
            FeedBackScreen(navController)
        }
        composable("editPageScreen") {
            EditPageScreen(navController,)
        }
    }
}

 */


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaratorTheme {
    }
}