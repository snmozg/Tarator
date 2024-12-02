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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircleOutline
import androidx.compose.material.icons.rounded.Brush
import androidx.compose.material.icons.rounded.Crop
import androidx.compose.material.icons.rounded.FilterAlt
import androidx.compose.material.icons.rounded.SaveAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.sozge.tarator.DrawingViewModel
import com.sozge.tarator.FilterViewModel
import com.sozge.tarator.ImageViewModel
import com.sozge.tarator.data.CustomButton
import com.sozge.tarator.options.FilterSection
import com.sozge.tarator.bars.AppBar
import com.sozge.tarator.helpers.Drawing
import com.sozge.tarator.options.BrushSection
import com.sozge.tarator.options.ToolsSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPageScreen(
    navController: NavController,
    imageViewModel: ImageViewModel,
    filterViewModel: FilterViewModel,
    drawingViewModel: DrawingViewModel
) {
    var hasPermission by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var isBrushSheetOpen by remember { mutableStateOf(false) }
    var isFilterSheetOpen by remember { mutableStateOf(false) }
    var isToolsSheetOpen by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )


    //izin isteme işlemi için launcher
    val permissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->
            hasPermission = isGranted
        }

    //galeriden görsel seçmek için launcher
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageViewModel.updateImage(uri!!,imageViewModel.drawings.value)
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
                navController,
                actionImageVector = Icons.Rounded.SaveAlt,
                actionContentDescription = "save button",
                isHomeScreen = false,
                viewModel = imageViewModel,
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
                imageViewModel.myImage.value?.let {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(context).data(it).build()
                        ),
                        colorFilter = filterViewModel.filter.value,
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
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
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
                )

                Spacer(modifier = Modifier.height(50.dp))

                if (isFilterSheetOpen) {
                    ModalBottomSheet(
                        sheetState = sheetState,
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.primary,
                        onDismissRequest = {
                            isFilterSheetOpen = false
                        },
                    ) {
                        Row() {
                            FilterSection(imageViewModel, filterViewModel)
                        }
                    }
                }
                if (isToolsSheetOpen) {
                    ModalBottomSheet(
                        sheetState = sheetState,
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.primary,
                        onDismissRequest = {
                            isToolsSheetOpen = false
                        },
                    ) {
                        Row {
                            ToolsSection()
                        }
                    }
                }
                if (isBrushSheetOpen) {
                    ModalBottomSheet(
                        sheetState = sheetState,
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.primary,
                        onDismissRequest = {
                            isBrushSheetOpen = false
                        },
                    ) {
                        Row {
                            BrushSection(0,
                                imageViewModel= imageViewModel,
                                drawingViewModel = drawingViewModel)
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CustomButton(
                        Icons.Rounded.FilterAlt,
                        "Filter Button",
                        "FILTERS",
                        onClick = { isFilterSheetOpen = true }
                    )
                    CustomButton(
                        Icons.Rounded.Crop,
                        "Tools Button",
                        "TOOLS",
                        onClick = { isToolsSheetOpen = true }
                    )
                    CustomButton(
                        Icons.Rounded.Brush,
                        "Brush Button",
                        "BRUSH",
                        onClick = { isBrushSheetOpen = true }
                    )
                }
            }
        }
    )
}