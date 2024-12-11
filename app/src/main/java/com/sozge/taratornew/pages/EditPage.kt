package com.sozge.taratornew.pages

import BottomSheetViewModel
import ToolsSection
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
import com.sozge.taratornew.components.EditPageImage
import com.sozge.taratornew.components.filters.FilterSection
import com.sozge.taratornew.components.HeaderBar
import com.sozge.taratornew.components.RowButtons
import com.sozge.taratornew.components.brushes.BrushSection
import com.sozge.taratornew.models.DrawingViewModel
import com.sozge.taratornew.models.FilterViewModel
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
    bottomSheetViewModel: BottomSheetViewModel,
    filterViewModel: FilterViewModel,
    drawingViewModel: DrawingViewModel
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
                drawingViewModel = drawingViewModel,
                filterViewModel = filterViewModel,
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
                    filterViewModel = filterViewModel,
                    drawingViewModel = drawingViewModel,
                    hasPermission = mutableStateOf(hasPermission),
                    galleryLauncher = { galleryLauncher.launch("image/*") },
                    permissionLauncher = {
                        permissionLauncher.launch(getRequiredPermission())
                    }
                )

                Spacer(modifier = Modifier.height(50.dp))

                RowButtons(
                    onFilterClick = { bottomSheetViewModel.openFilterSheet() },
                    onToolsClick = { bottomSheetViewModel.openToolsSheet() },
                    onBrushClick = { bottomSheetViewModel.openBrushSheet() }
                )

                // ModalBottomSheets controlled by ViewModel
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
                        ToolsSection(imageViewModel)
                    }
                }

                if (bottomSheetViewModel.isBrushSheetOpen.value) {
                    ModalBottomSheet(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.primary,
                        onDismissRequest = { bottomSheetViewModel.closeBrushSheet() },
                        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
                    ) {
                        BrushSection(
                            imageViewModel = imageViewModel,
                            filterViewModel = filterViewModel,
                            drawingViewModel = drawingViewModel
                        )
                    }
                }
            }
        }
    )
}