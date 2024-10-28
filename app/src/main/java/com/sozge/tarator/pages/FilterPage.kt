package com.sozge.tarator.pages

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircleOutline
import androidx.compose.material.icons.rounded.Brush
import androidx.compose.material.icons.rounded.Crop
import androidx.compose.material.icons.rounded.FilterAlt
import androidx.compose.material.icons.rounded.SaveAlt
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.sozge.tarator.CustomButton
import com.sozge.tarator.bars.AppBar
import com.sozge.tarator.ui.theme.TaratorTheme

@Composable
fun FilterPageScreen(imageUri: String,navController: NavController) {
    val context = LocalContext.current
    val uri = Uri.parse(imageUri)
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        AppBar(actionImageVector = Icons.Rounded.SaveAlt,
            actionContentDescription = "save button",
            isHomeScreen = false,
            onClick = {
                println("download the photo")
            })
    }, content = { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(context).data(uri).build()
                ), contentDescription = null, modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .background(Color.Blue),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CustomButton(Icons.Rounded.FilterAlt, "Filter Button1", "filtre1", onClick = {
                println("filter button clicked")
            })
            CustomButton(Icons.Rounded.FilterAlt, "Filter Button1", "filtre2", onClick = {
                println("tools button clicked")
            })
            CustomButton(Icons.Rounded.FilterAlt, "Filter Button3", "filtre3", onClick = {
                println("brush button clicked")
            })
        }
    })
}
