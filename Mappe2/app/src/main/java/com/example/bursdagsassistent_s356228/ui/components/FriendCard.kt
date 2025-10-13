package com.example.bursdagsassistent_s356228.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bursdagsassistent_s356228.R
import com.example.bursdagsassistent_s356228.data.model.Friend
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Composable
fun FriendCard(
    friend: Friend,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val daysUntilBirthday = calculateDaysUntilNextBirthday(birthDate = friend.dateOfBirth)

    val birthdayText = when{
        daysUntilBirthday == 0L -> stringResource(R.string.friend_card_birthday_today)
        daysUntilBirthday == 1L -> stringResource(R.string.friend_card_birthday_tomorrow)
        else -> stringResource(id = R.string.friend_card_birthday_in_days, daysUntilBirthday)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(width = 0.3.dp, color = Color.Gray)
            .clickable(onClick = onClick),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = stringResource(R.string.person_icon_description),
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(50.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 12.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.friend_full_name, friend.firstName, friend.lastName),
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = birthdayText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline
                )
            }
            IconButton(
                onClick = onDelete,
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = stringResource(R.string.delete_friend_description))
            }
        }
    }
}

private fun calculateDaysUntilNextBirthday(birthDate: LocalDate): Long {
    val today = LocalDate.now()
    var nextBirthday = birthDate.withYear(today.year)

    if (nextBirthday.isBefore(today)) {
        nextBirthday = nextBirthday.plusYears(1)
    }

    return ChronoUnit.DAYS.between(today, nextBirthday)
}

@Preview(showBackground = true)
@Composable
fun FriendCardPreview() {
    FriendCard(
        friend = Friend(id = 1, firstName = "Magnus", lastName = "Magnussen", phoneNumber = "12345678", dateOfBirth = LocalDate.of(2001, 1, 1)),
        onClick = {},
        onDelete = {}
    )
}
