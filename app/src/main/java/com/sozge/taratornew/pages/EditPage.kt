package com.sozge.taratornew.pages

import BottomSheetViewModel
import ToolsSection
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
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
import com.sozge.taratornew.components.CustomAlertDialog
import com.sozge.taratornew.models.TextViewModel
import com.sozge.taratornew.utils.com.sozge.taratornew.models.ToolsViewModel
import com.sozge.taratornew.utils.com.sozge.taratornew.utils.applyFilterToBitmap
import com.sozge.taratornew.utils.com.sozge.taratornew.utils.bitmapToUri
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
    textViewModel: TextViewModel
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
                navController = navController,
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
                            // Eğer filtre seçilmişse, orijinal resim üzerinden filtrelenmiş bitmap oluştur
                            val filteredBitmap = if (colorFilter != null) {
                                applyFilterToBitmap(originalBitmap, colorFilter)
                            } else {
                                originalBitmap.copy(originalBitmap.config!!, true)
                            }

                            // Filtre uygulandıktan sonra, diğer işlemleri (çizim ve metin) filtrelenmiş bitmap üzerinden yapalım.
                            val drawingBitmap = drawingViewModel.getCurrentBitmap(
                                filteredBitmap!!.width,
                                filteredBitmap.height
                            )
                            // Önemli: textViewModel’e de orijinal yerine filtrelenmiş bitmap referansı gönderiyoruz.
                            val textBitmap = textViewModel.textOnBitmap(
                                context = context,
                                originalBitmap = filteredBitmap
                            )

                            if (filteredBitmap != null && drawingBitmap != null && textBitmap != null) {
                                val combinedBitmap = combineBitmaps(filteredBitmap, drawingBitmap, textBitmap)

                                val savedUri = saveBitmapToGallery(
                                    context,
                                    combinedBitmap
                                )
                                savedUri?.let {
                                    showDialog = true
                                    dialogTitle = "Successfully saved"
                                    dialogMessage = "Photo saved to gallery!"
                                    onConfirmAction = {
                                        navController.navigate("Homepage") {
                                            popUpTo("Homepage") { inclusive = true }
                                        }
                                        imageViewModel.deleteImage()
                                    }
                                } ?: run {
                                    showDialog = true
                                    dialogTitle = "WARNING"
                                    dialogMessage = "Photo could not be saved!"
                                    onConfirmAction = null
                                }
                            } else {
                                showDialog = true
                                dialogTitle = "WARNING"
                                dialogMessage = "Try again!"
                                onConfirmAction = null
                            }
                        } else {
                            showDialog = true
                            dialogTitle = "WARNING"
                            dialogMessage = "Try again!"
                            onConfirmAction = null
                        }
                    } ?: run {
                        showDialog = true
                        dialogTitle = "WARNING"
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
                verticalArrangement = Arrangement.SpaceBetween,
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
                    onTextClick = {
                        if (imageViewModel.myImage.value != null) {
                            navController.navigate("TextPage")
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
fun combineBitmaps(vararg bitmaps: Bitmap): Bitmap {
    val width = bitmaps[0].width
    val height = bitmaps[0].height
    val combinedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(combinedBitmap)

    bitmaps.forEach { bitmap ->
        canvas.drawBitmap(bitmap, 0f, 0f, null)
    }

    return combinedBitmap
}

