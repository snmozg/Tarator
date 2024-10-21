package com.sozge.tarator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Button

import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sozge.tarator.ui.theme.TaratorTheme

@Composable
fun FeedBackScreen() {
    val itemName = remember {
        mutableStateOf("")
    }

    Scaffold(topBar = { AppBar()}) { innerpadding ->
        println(innerpadding)
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "We value your thoughts. Share your thoughts with us!",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp))

        Spacer(modifier = Modifier.padding(bottom = 40.dp))

        Text(
            text = "TARATOR",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedTextField(value = itemName.value,
            placeholder = {
                Text(text = "Write here")
            },
            onValueChange = { itemName.value = it })

        Spacer(modifier = Modifier.padding(20.dp))

        Button(onClick = { /*TODO*/ },) {
            Text(text = "Send Feedback")
            
        }



    }


}


@Preview(showBackground = true)
@Composable
fun FeedBackPrewiew() {
    TaratorTheme {
        FeedBackScreen()
    }
}
