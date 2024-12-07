package com.sozge.taratornew.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    isBigButton: Boolean,

    ) {
    if (isBigButton) {
        Button(
            onClick = { onClick() },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 60.dp, end = 60.dp)
                .height(100.dp)
                .width(150.dp)
        ) {
            Text(
                text = text,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    } else {
        Button(
            onClick = { onClick() },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .height(100.dp)
                .width(150.dp)
                .padding(5.dp)
        ) {
            Text(
                text = text,
                fontSize = 20.sp,
                color = Color.White
            )

        }
    }
}