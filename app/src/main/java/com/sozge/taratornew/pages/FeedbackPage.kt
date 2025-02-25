package com.sozge.taratornew.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sozge.taratornew.R
import com.sozge.taratornew.components.CustomAlertDialog
import com.sozge.taratornew.components.HeaderBar
import com.sozge.taratornew.models.DrawingViewModel
import com.sozge.taratornew.models.FilterViewModel
import com.sozge.taratornew.models.ImageViewModel
import com.sozge.taratornew.utils.com.sozge.taratornew.components.CustomTextInput
import com.sozge.taratornew.utils.com.sozge.taratornew.utils.CustomExtendedFAB
import com.sozge.taratornew.utils.com.sozge.taratornew.utils.myFont

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FeedBackPage(
    navController: NavController,
    imageViewModel: ImageViewModel,
    drawingViewModel: DrawingViewModel,
    filterViewModel: FilterViewModel
) {
    val email = remember { mutableStateOf("") }
    val message = remember { mutableStateOf("") }
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

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            HeaderBar(
                navController = navController,
                actionImageVector = Icons.Outlined.Menu,
                actionContentDescription = "menu button",
                isBackButtonEnable = true,
                imageViewModel = imageViewModel,
                drawingViewModel = drawingViewModel,
                filterViewModel = filterViewModel,
                onClick = {
                    navController.navigate("SettingsPage")
                }
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .imeNestedScroll(),
        floatingActionButton = {
            CustomExtendedFAB(
                 MaterialTheme.colorScheme.primary,
                text = "Send",
                onClick = {
                    showDialog = true
                    dialogTitle = "Thanks for your feedback!"
                    dialogMessage = "We will get back to you as soon as possible."
                    onConfirmAction = null
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween, // Alanı dikeyde düzenler
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logod),
                    contentDescription = "app logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(120.dp)
                )
            }
            Text(
                text = "How can we help you?",
                fontSize = 18.sp,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )
            CustomTextInput(
                title = "Email",
                label = "Email",
                text = email.value,
                onValueChange = { email.value = it },
                isSingleLine = true,
                isVisual = true,
                keyboardType = KeyboardType.Email,
                isBigCanvas = false
            )
            CustomTextInput(
                title = "Message",
                label = "Enter Message",
                text = message.value,
                onValueChange = { message.value = it },
                isSingleLine = false,
                isVisual = true,
                keyboardType = KeyboardType.Text,
                isBigCanvas = true
            )
        }
    }
        Text(
            text = "Tarator ⓒ",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = myFont,
            color = Color.LightGray,
            modifier = Modifier
                .padding(bottom = 16.dp)

        )
}}




