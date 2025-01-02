package com.sozge.taratornew.pages

import BottomSheetViewModel
import ToolsSection
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sozge.taratornew.components.EditPageImage
import androidx.compose.ui.graphics.asAndroidColorFilter

import com.sozge.taratornew.components.filters.FilterSection
import com.sozge.taratornew.components.HeaderBar
import com.sozge.taratornew.components.RowButtons
import com.sozge.taratornew.components.brushes.BrushSection
import com.sozge.taratornew.models.DrawingViewModel
import com.sozge.taratornew.models.FilterViewModel
import com.sozge.taratornew.models.ImageViewModel
import com.sozge.taratornew.utils.checkPermission
import com.sozge.taratornew.utils.com.sozge.taratornew.components.CustomAlertDialog
import com.sozge.taratornew.utils.com.sozge.taratornew.models.ToolsViewModel
import com.sozge.taratornew.utils.com.sozge.taratornew.utils.applyFilterToBitmap
import com.sozge.taratornew.utils.com.sozge.taratornew.utils.bitmapToUri
import com.sozge.taratornew.utils.com.sozge.taratornew.utils.bitmapWithDrawing
import com.sozge.taratornew.utils.com.sozge.taratornew.utils.saveBitmapToGallery
import com.sozge.taratornew.utils.getRequiredPermission
import com.sozge.taratornew.utils.rememberGalleryLauncher
import com.sozge.taratornew.utils.rememberPermissionLauncher
import com.sozge.taratornew.utils.toBitmap
import java.io.File
import java.io.FileOutputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPage(
    navController: NavController,
    imageViewModel: ImageViewModel,
    bottomSheetViewModel: BottomSheetViewModel,
    filterViewModel: FilterViewModel,
    drawingViewModel: DrawingViewModel,
    toolsViewModel: ToolsViewModel,
) {
    var hasPermission by remember { mutableStateOf(false) }
    val permissionLauncher = rememberPermissionLauncher(mutableStateOf(hasPermission))
    val galleryLauncher = rememberGalleryLauncher(imageViewModel)
    val context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf("") }
    var onConfirmAction by remember { mutableStateOf<(() -> Unit)?>(null) }


    if (showDialog) {
        CustomAlertDialog(
            title = dialogTitle,
            message = dialogMessage,
            onDismiss = { showDialog = false },
            onConfirm = {
                onConfirmAction?.invoke()
            }
        )
    }

    LaunchedEffect(Unit) {
        hasPermission = checkPermission(context, permission = getRequiredPermission())
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HeaderBar(
                navController,
                actionImageVector = Icons.Outlined.FileDownload,
                actionContentDescription = "save button",
                isBackButtonEnable = true,
                imageViewModel = imageViewModel,
                drawingViewModel = drawingViewModel,
                filterViewModel = filterViewModel,
                onClick = {
                    imageViewModel.myImage.value?.let { uri ->
                        val originalBitmap = uri.toBitmap(context)
                        val colorFilter = filterViewModel.filter.value?.asAndroidColorFilter()

                        if (originalBitmap != null) {
                            val filteredBitmap = applyFilterToBitmap(originalBitmap, colorFilter)
                            val drawingBitmap = drawingViewModel.getCurrentBitmap(
                                originalBitmap.width,
                                originalBitmap.height
                            )
                            if (filteredBitmap != null && drawingBitmap != null) {
                                val finalBitmap = bitmapWithDrawing(filteredBitmap, drawingBitmap)
                                val savedUri = saveBitmapToGallery(
                                    context,
                                    finalBitmap
                                )
                                savedUri?.let {
                                    showDialog = true
                                    dialogTitle = "Successfully saved to the gallery"
                                    dialogMessage = "Keep editing!"
                                    onConfirmAction = {
                                        navController.navigate("Homepage") {
                                            popUpTo("Homepage") { inclusive = true }
                                            imageViewModel.deleteImage()
                                        }
                                    }
                                } ?: run {
                                    showDialog = true
                                    dialogTitle = "There is a problem"
                                    dialogMessage = "Photo could not be saved!"
                                    onConfirmAction = null
                                }
                            } else {
                                showDialog = true
                                dialogTitle = "There is a problem"
                                dialogMessage = "try again!"
                                onConfirmAction = null
                            }
                        } else {
                            showDialog = true
                            dialogTitle = "Hata"
                            dialogMessage = "try again!!"
                            onConfirmAction = null
                        }
                    } ?: run {
                        showDialog = true
                        dialogTitle = "There is a problem"
                        dialogMessage = "No photo selected!"
                        onConfirmAction = null
                    }
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
                    filterViewModel = filterViewModel,
                    drawingViewModel = drawingViewModel,
                    toolsViewModel = toolsViewModel,
                    hasPermission = mutableStateOf(hasPermission),
                    galleryLauncher = { galleryLauncher.launch("image/*") },
                    permissionLauncher = {
                        permissionLauncher.launch(getRequiredPermission())
                    }
                )

                Spacer(modifier = Modifier.height(50.dp))

                RowButtons(
                    onFilterClick = {
                        if (imageViewModel.myImage.value != null) {
                            bottomSheetViewModel.openFilterSheet()
                        }
                    },
                    onToolsClick = {
                        if (imageViewModel.myImage.value != null) {
                            bottomSheetViewModel.openToolsSheet()
                        }
                    },
                    onBrushClick = {
                        if (imageViewModel.myImage.value != null) {
                            navController.navigate("BrushPage")
                        }
                    },
                    onCropClick = {
                        if (imageViewModel.myImage.value != null) {
                                navController.navigate("CropPage")
                        }
                    }
                )


                if (bottomSheetViewModel.isFilterSheetOpen.value) {
                    ModalBottomSheet(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.primary,
                        onDismissRequest = { bottomSheetViewModel.closeFilterSheet() },
                        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
                    ) {
                        FilterSection(imageViewModel, filterViewModel)
                    }
                }

                if (bottomSheetViewModel.isToolsSheetOpen.value) {
                    ModalBottomSheet(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.primary,
                        onDismissRequest = { bottomSheetViewModel.closeToolsSheet() },
                        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
                    ) {
                        ToolsSection(
                            imageViewModel,
                            toolsViewModel,
                            filterViewModel,
                            bottomSheetViewModel
                        )
                    }
                }
            }
        }
    )
}

