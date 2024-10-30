package com.sozge.tarator.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sozge.tarator.ui.theme.TaratorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavController()
        }
    }
}

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
            EditPageScreen(navController)
        }
        composable(route = "FeedBackScreen") {
            FeedBackScreen(navController)
        }
        composable("editPageScreen") {
            EditPageScreen(navController)
        }
        composable(
            "FilterPageScreen" + "?imageUri={imageUri}",
            arguments = listOf(
                navArgument("imageUri") {
                    type = NavType.StringType
                    defaultValue = "0000"
                }
            )
        ){
            val imageUri = it.arguments?.getString("imageUri")!!
            FilterPageScreen(imageUri, navController)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaratorTheme {
    }
}