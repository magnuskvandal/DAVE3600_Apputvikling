package com.example.bursdagsassistent_s356228.utils

import android.content.Context
import com.example.bursdagsassistent_s356228.R
import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class BirthdayCountdownCircleInfo(
    val topText: String?,
    val bottomText: String
)

fun calculateDaysUntilNextBirthday(birthDate: LocalDate): Long {
    val today = LocalDate.now()
    val nextBirthday = birthDate.withYear(today.year).let {
        if (it.isBefore(today)) it.plusYears(1) else it
    }
    return ChronoUnit.DAYS.between(today, nextBirthday)
}

fun getBirthdayStatusForListItem(birthDate: LocalDate, context: Context): String {
    val daysUntil = calculateDaysUntilNextBirthday(birthDate)
    return when (daysUntil) {
        0L -> context.getString(R.string.birthday_today)
        1L -> context.getString(R.string.friend_list_birthday_tomorrow, daysUntil)
        else -> context.getString(R.string.friend_list_birthday_in_days, daysUntil)
    }
}

fun getBirthdayCountdownCircleInfo(birthDate: LocalDate, context: Context): BirthdayCountdownCircleInfo {
    val daysUntil = calculateDaysUntilNextBirthday(birthDate)
    return when (daysUntil) {
        0L -> BirthdayCountdownCircleInfo(
            topText = null,
            bottomText = context.getString(R.string.birthday_today)
        )
        1L -> BirthdayCountdownCircleInfo(
            topText = daysUntil.toString(),
            bottomText = context.getString(R.string.countdown_day_to_birthday)
        )
        else -> BirthdayCountdownCircleInfo(
            topText = daysUntil.toString(),
            bottomText = context.getString(R.string.countdown_days_to_birthday)
        )
    }
}
