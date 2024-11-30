package com.sozge.tarator.pages


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sozge.tarator.DrawingViewModel
import com.sozge.tarator.FilterViewModel
import com.sozge.tarator.ImageViewModel
import com.sozge.tarator.bars.AppBar
import com.sozge.tarator.ui.theme.TaratorTheme

@Composable
fun FeedBackScreen(navController: NavController,
                   imageViewModel: ImageViewModel,
                   filterViewModel: FilterViewModel,
                   drawingViewModel: DrawingViewModel
) {
    val itemName = remember {
        mutableStateOf("")
    }

    Scaffold(topBar = {
        AppBar(
            navController,
            actionImageVector = Icons.Rounded.Logout,
            actionContentDescription = "Logout",
            isHomeScreen = false,
            viewModel = imageViewModel,
            onClick = {
                println("belirsiz")
            }
        )
    }) { innerpadding ->
        println(innerpadding)
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "We value your thoughts. Share your thoughts with us!",
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp)
        )

        Spacer(modifier = Modifier.padding(bottom = 40.dp))

        Text(
            text = "TARATOR",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedTextField(value = itemName.value,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp),
            placeholder = {
                Text(text = "Write here", color = Color.White)
            },
            onValueChange = { itemName.value = it })

        Spacer(modifier = Modifier.padding(5.dp))

        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Send Feedback",
                color = Color.White,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FeedBackPreview() {
    TaratorTheme {

    }
}
