package com.sozge.tarator.pages

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Adb
import androidx.compose.material.icons.rounded.AddCircleOutline
import androidx.compose.material.icons.rounded.Brush
import androidx.compose.material.icons.rounded.Crop
import androidx.compose.material.icons.rounded.FilterAlt
import androidx.compose.material.icons.rounded.SaveAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.sozge.tarator.CustomButton
import com.sozge.tarator.bars.AppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPageScreen(navController: NavController) {
    var hasPermission by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    //izin isteme işlemi için launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) {
        isGranted ->
        hasPermission = isGranted
    }

    //galeriden görsel seçmek için launcher
    val galleryLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
        uri: Uri? -> imageUri = uri
    }

    LaunchedEffect(Unit) {
        val permission: String;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permission = Manifest.permission.READ_MEDIA_IMAGES;
        } else {
            permission = Manifest.permission.READ_EXTERNAL_STORAGE;
        }
        hasPermission = ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(
                actionImageVector = Icons.Rounded.SaveAlt,
                actionContentDescription = "save button",
                isHomeScreen = false,
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
                imageUri?.let {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(context).data(it).build()
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(600.dp)
                            .padding(10.dp)
                            .clickable {
                            }
                    )
                } ?: Image(
                    imageVector = Icons.Rounded.AddCircleOutline,
                    contentDescription = "add icon",
                    modifier = Modifier
                        .clickable {
                            if (hasPermission) {
                                galleryLauncher.launch("image/*")
                            } else {
                                val permission: String;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                    permission = Manifest.permission.READ_MEDIA_IMAGES;
                                } else {
                                    permission = Manifest.permission.READ_EXTERNAL_STORAGE;
                                }
                                permissionLauncher.launch(permission)
                            }
                        }
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth()
                        .height(600.dp)
                        .padding(10.dp)
                        .background(Color.Magenta)
                )

                Spacer(modifier = Modifier.height(50.dp))
                val sheetState = rememberModalBottomSheetState()
                var isSheetOpen by rememberSaveable {
                    mutableStateOf(false)
                }
                if(isSheetOpen) {
                    ModalBottomSheet(
                        sheetState = sheetState,
                        onDismissRequest = {
                            isSheetOpen = false },
                    ) {
                       LazyRow() {
                           items(1){
                               Icon(imageVector = Icons.Rounded.Adb, contentDescription = "")
                               Icon(imageVector = Icons.Rounded.Adb, contentDescription = "")
                               Icon(imageVector = Icons.Rounded.Adb, contentDescription = "")
                               Icon(imageVector = Icons.Rounded.Adb, contentDescription = "")
                               Icon(imageVector = Icons.Rounded.Adb, contentDescription = "")
                               Icon(imageVector = Icons.Rounded.Adb, contentDescription = "")
                               Icon(imageVector = Icons.Rounded.Adb, contentDescription = "")
                               Icon(imageVector = Icons.Rounded.Adb, contentDescription = "")
                               Icon(imageVector = Icons.Rounded.Adb, contentDescription = "")
                               Icon(imageVector = Icons.Rounded.Adb, contentDescription = "")
                               Icon(imageVector = Icons.Rounded.Adb, contentDescription = "")
                               Icon(imageVector = Icons.Rounded.Adb, contentDescription = "")
                           }
                       }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                        .background(Color.Blue),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CustomButton(
                        Icons.Rounded.FilterAlt,
                        "Filter Button",
                        "FILTERS",
                        onClick = { isSheetOpen=true}
                    )
                    CustomButton(
                        Icons.Rounded.Crop,
                        "Tools Button",
                        "TOOLS",
                        onClick = {
                            println("tools button clicked")
                        }
                    )
                    CustomButton(
                        Icons.Rounded.Brush,
                        "Brush Button",
                        "BRUSH",
                        onClick = {
                            println("brush button clicked")
                        }
                    )
                }
            }
        }
    )
}

