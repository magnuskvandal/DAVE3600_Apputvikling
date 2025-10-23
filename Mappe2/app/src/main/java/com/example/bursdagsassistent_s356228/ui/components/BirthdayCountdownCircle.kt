package com.example.bursdagsassistent_s356228.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.bursdagsassistent_s356228.utils.getBirthdayCountdownCircleInfo
import java.time.LocalDate

@Composable
fun BirthdayCountdownCircle(
    modifier: Modifier = Modifier,
    birthDate: LocalDate,
    size: Dp
) {
    val countdownInfo = getBirthdayCountdownCircleInfo(birthDate, context = LocalContext.current)

    Box(
        modifier = modifier
            .size(size)
            .clip(shape = CircleShape)
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (countdownInfo.topText == null) {
                Icon(
                    imageVector = Icons.Filled.Cake,
                    contentDescription = "Bursdagskake",
                    modifier = Modifier.size(size / 2.5f),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = countdownInfo.bottomText,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text(
                    text = countdownInfo.topText,
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
                Text(
                    text = countdownInfo.bottomText,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayCountdownCirclePreviewToday() {
    BirthdayCountdownCircle(
        birthDate = LocalDate.now(),
        size = 200.dp
    )
}

@Preview(showBackground = true)
@Composable
fun BirthdayCountdownCirclePreviewTomorrow() {
    BirthdayCountdownCircle(
        birthDate = LocalDate.now().plusDays(1),
        size = 200.dp
    )
}

@Preview(showBackground = true)
@Composable
fun BirthdayCountdownCirclePreviewManyDays() {
    BirthdayCountdownCircle(
        birthDate = LocalDate.now().plusDays(150),
        size = 200.dp
    )
}
