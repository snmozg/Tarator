package com.sozge.taratornew.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sozge.taratornew.components.CustomOptionsButton
import com.sozge.taratornew.components.EditPageImage
import com.sozge.taratornew.components.HeaderBar
import com.sozge.taratornew.models.ImageViewModel
import com.sozge.taratornew.utils.checkPermission
import com.sozge.taratornew.utils.getRequiredPermission
import com.sozge.taratornew.utils.rememberGalleryLauncher
import com.sozge.taratornew.utils.rememberPermissionLauncher

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPage(
    navController: NavController,
    imageViewModel: ImageViewModel,
) {
    var hasPermission by remember { mutableStateOf(false) }
    val permissionLauncher = rememberPermissionLauncher(mutableStateOf(hasPermission))
    val galleryLauncher = rememberGalleryLauncher(imageViewModel)
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        hasPermission = checkPermission(context, permission = getRequiredPermission())
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HeaderBar(
                navController,
                actionImageVector = Icons.Rounded.SaveAlt,
                actionContentDescription = "save button",
                isBackButtonEnable = true,
                imageViewModel = imageViewModel,
                onClick = {
                    println("download the photo")
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EditPageImage(
                    imageViewModel = imageViewModel,
                    hasPermission = mutableStateOf(hasPermission),
                    galleryLauncher = { galleryLauncher.launch("image/*") },
                    permissionLauncher = {
                        permissionLauncher.launch(getRequiredPermission())
                    }
                )

                Spacer(modifier = Modifier.height(50.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CustomOptionsButton(
                        Icons.Rounded.FilterAlt,
                        "Filter Button",
                        "FILTERS",
                        onClick = { /* Your code for opening filters */ }
                    )
                    CustomOptionsButton(
                        Icons.Rounded.Crop,
                        "Tools Button",
                        "TOOLS",
                        onClick = { /* Your code for opening tools */ }
                    )
                    CustomOptionsButton(
                        Icons.Rounded.Brush,
                        "Brush Button",
                        "BRUSH",
                        onClick = { /* Your code for opening brush */ }
                    )
                }
            }
        }
    )
}