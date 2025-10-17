package com.example.bursdagsassistent_s356228.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InitialAvatar(
    modifier: Modifier = Modifier,
    firstName: String,
    lastName: String,
    size: Dp
) {
    val initials = "${firstName.firstOrNull() ?: ""}${lastName.firstOrNull() ?: ""}".uppercase()

    Box(
        modifier = modifier
            .size(size)
            .clip(shape = CircleShape)
            .background(Color(0xFFFFB74D))
        ,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = (size.value / 2.5).sp,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun InitialAvatarPreview() {
    InitialAvatar(
        firstName = "Magnus",
        lastName = "Kvandal",
        size = 64.dp
    )
}
