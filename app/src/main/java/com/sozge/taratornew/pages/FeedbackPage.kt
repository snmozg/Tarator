package com.sozge.taratornew.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.sozge.taratornew.components.CustomButton
import com.sozge.taratornew.components.HeaderBar
import com.sozge.taratornew.models.DrawingViewModel
import com.sozge.taratornew.models.FilterViewModel
import com.sozge.taratornew.models.ImageViewModel
import com.sozge.taratornew.utils.com.sozge.taratornew.components.CustomTextInput
import com.sozge.taratornew.utils.com.sozge.taratornew.utils.myFont

@Composable
fun FeedBackPage
            (navController: NavController,
             imageViewModel: ImageViewModel,
             filterViewModel: FilterViewModel,
             drawingViewModel: DrawingViewModel
) {
    val itemName = remember {
        mutableStateOf("")
    }
    val email = remember { mutableStateOf("") }
    val subject = remember { mutableStateOf("") }
    val message = remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Scaffold(topBar = {
        HeaderBar(
            navController,
            actionImageVector = Icons.Rounded.Logout,
            actionContentDescription = "Logout",
            isBackButtonEnable = true,
            imageViewModel=imageViewModel,
            filterViewModel = filterViewModel,
            drawingViewModel = drawingViewModel,
            onClick = {
                println("belirsiz")
            }
        )
    },
        floatingActionButton = {
            CustomButton(
                containerColor = MaterialTheme.colorScheme.primary,
                text = "Send"
            ) {

            }
        },
        floatingActionButtonPosition = FabPosition.Center

    ) { innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.applicationicon),
                contentDescription = "",
                contentScale = ContentScale.Fit
            )
            Text(
                modifier = Modifier.padding(5.dp),
                text = "How can we help you?",
                fontFamily = myFont,
                fontSize = 22.sp
            )
            Column(
                modifier = Modifier
                    .imePadding()
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomTextInput(
                    title = "Email",
                    label = "Email",
                    text = email.value,
                    onValueChange = { email.value = it },
                    isSingleLine = true,
                    isVisual = true,
                    keyboardType = KeyboardType.Email
                )
                CustomTextInput(
                    title = "Subject",
                    label = "Enter Subject",
                    text = subject.value,
                    onValueChange = { subject.value = it },
                    isSingleLine = true,
                    isVisual = true,
                    keyboardType = KeyboardType.Text
                )
                CustomTextInput(
                    title = "Message",
                    label = "Enter Message",
                    text = message.value,
                    onValueChange = { message.value = it },
                    isSingleLine = true,
                    isVisual = true,
                    keyboardType = KeyboardType.Text
                )
            }

        }
    }
}