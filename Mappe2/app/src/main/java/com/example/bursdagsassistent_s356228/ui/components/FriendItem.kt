package com.example.bursdagsassistent_s356228.ui.components

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bursdagsassistent_s356228.R
import com.example.bursdagsassistent_s356228.data.model.Friend
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Composable
fun FriendItem(
    friend: Friend,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(width = 0.3.dp, color = Color.Gray)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            InitialAvatar(
                modifier = Modifier.padding(15.dp),
                firstName = friend.firstName,
                lastName = friend.lastName,
                size = 50.dp
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 15.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.friend_full_name, friend.firstName, friend.lastName),
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = getBirthdayStatus(birthDate = friend.dateOfBirth, context = LocalContext.current),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline
                )
            }
            Icon(
                modifier = modifier.padding(end = 15.dp),
                imageVector = Icons.Default.ChevronRight,
                contentDescription = stringResource(id = R.string.view_details_icon_description),

            )
        }
    }
}

fun getBirthdayStatus(birthDate: LocalDate, context: Context): String {
    val today = LocalDate.now()
    val nextBirthday = birthDate.withYear(today.year)
    val finalNextBirthday = if (nextBirthday.isBefore(today)) {
        nextBirthday.plusYears(1)
    } else {
        nextBirthday
    }
    val daysUntil = ChronoUnit.DAYS.between(today, finalNextBirthday)

    return when (daysUntil) {
        0L -> context.getString(R.string.friend_card_birthday_today)
        1L -> context.getString(R.string.friend_card_birthday_tomorrow)
        else -> context.getString(R.string.friend_card_birthday_in_days, daysUntil)
    }
}

@Preview(showBackground = true)
@Composable
fun FriendCardPreview() {
    FriendItem(
        friend = Friend(
            id = 1,
            firstName = "Magnus",
            lastName = "Kvandal",
            phoneNumber = "12345678",
            dateOfBirth = LocalDate.of(1990, 5, 15)
        ),
        onClick = {},
    )
}
