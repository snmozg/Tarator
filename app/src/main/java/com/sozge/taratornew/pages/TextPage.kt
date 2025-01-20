package com.sozge.taratornew.pages

import BottomSheetViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material.icons.outlined.FontDownload
import androidx.compose.material.icons.outlined.TextFields
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sozge.taratornew.components.CustomOptionsButton
import com.sozge.taratornew.components.HeaderBar
import com.sozge.taratornew.components.brushes.BrushColorPicker
import com.sozge.taratornew.models.DrawingViewModel
import com.sozge.taratornew.models.FilterViewModel
import com.sozge.taratornew.models.ImageViewModel
import com.sozge.taratornew.models.TextViewModel
import com.sozge.taratornew.text.DraggableTextItem
import com.sozge.taratornew.utils.com.sozge.taratornew.utils.bitmapToUri
import com.sozge.taratornew.utils.toBitmap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextPage(
    navController: NavController,
    imageViewModel: ImageViewModel,
    drawingViewModel: DrawingViewModel,
    filterViewModel: FilterViewModel,
    bottomSheetViewModel: BottomSheetViewModel,
    textViewModel: TextViewModel,
) {
    val context = LocalContext.current
    var textColor by remember { mutableStateOf(Color.Black) }
    var textSize by remember { mutableStateOf(16.sp) }
    var showTextField by remember { mutableStateOf(false) }
    var inputText by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    val imageUri = imageViewModel.myImage.value
    val bitmap = imageUri?.toBitmap(context)
    val updatedBitmap = imageUri?.toBitmap(context)
    val imageBitmap = bitmap?.asImageBitmap()

    val textList by textViewModel.textList.collectAsState()

    LaunchedEffect(showTextField) {
        if (showTextField) focusRequester.requestFocus()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HeaderBar(
                navController = navController,
                actionImageVector = Icons.Outlined.Check,
                actionContentDescription = "Check",
                isBackButtonEnable = true,
                imageViewModel = imageViewModel,
                drawingViewModel = drawingViewModel,
                filterViewModel = filterViewModel,
                onClick = {
                    navController.popBackStack()
                    val bitmap = imageBitmap?.asAndroidBitmap()
                    bitmap?.let { updatedBitmap ->

                        val updatedBitmapWithText = textViewModel.textOnBitmap(context, updatedBitmap)
                        val updatedUri = bitmapToUri(context, updatedBitmapWithText)
                        updatedUri?.let {
                            imageViewModel.updateImage(it)
                        }
                    }
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                if (imageBitmap != null) {
                    Image(
                        painter = BitmapPainter(imageBitmap),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize(),
                        colorFilter = filterViewModel.filter.value
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    textList.forEachIndexed { index, textData ->
                        DraggableTextItem(
                            textData = textData,
                            onPositionChange = { newPosition ->
                                textViewModel.updateTextPosition(index, newPosition)
                            }
                        )
                    }
                }
            }

            if (showTextField) {
                TextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .focusRequester(focusRequester),
                    placeholder = { Text("Enter your text") },
                    textStyle = TextStyle(color = textColor, fontSize = textSize),
                    singleLine = true
                )
                Button(
                    onClick = {
                        val text = if (inputText.isBlank()) "Your Text Here" else inputText
                        textViewModel.addText(
                            text = text,
                            position = Offset(50f, 50f),
                            color = textColor,
                            size = textSize
                        )
                        showTextField = false
                        inputText = ""
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 8.dp)
                ) {
                    Text("Done")
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomOptionsButton(
                    Icons.Outlined.TextFields,
                    "Add Text",
                    "TEXT",
                    onClick = { showTextField = true }
                )
                CustomOptionsButton(
                    Icons.Outlined.ColorLens,
                    "Text Color",
                    "TEXT COLOR",
                    onClick = {
                        bottomSheetViewModel.openColorSheet()
                    }
                )
                CustomOptionsButton(
                    Icons.Outlined.FontDownload,
                    "Text Size",
                    "SIZE",
                    onClick = {
                        bottomSheetViewModel.openWidthSheet()
                    }
                )
            }

            if (bottomSheetViewModel.isColorSheetOpen.value) {
                ModalBottomSheet(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.primary,
                    onDismissRequest = { bottomSheetViewModel.closeColorSheet() },
                    sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
                ) {
                    BrushColorPicker(
                        selectedColor = remember { mutableStateOf(textColor) },
                        bottomSheetViewModel = bottomSheetViewModel
                    ) { color ->
                        textColor = color
                    }
                }
            }

            if (bottomSheetViewModel.isWidthSheetOpen.value) {
                ModalBottomSheet(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.primary,
                    onDismissRequest = { bottomSheetViewModel.closeWidthSheet() },
                    sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
                ) {
                    Text(
                        text = "Set the font size.",
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Slider(
                        value = textSize.value,
                        onValueChange = { textSize = it.sp },
                        valueRange = 10f..100f,
                        steps = 90,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}



