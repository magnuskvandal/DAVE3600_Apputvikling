package com.example.bursdagsassistent_s356228.ui.state

import java.time.LocalDate

data class FriendFormUiState(
    val id: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val dateOfBirth: LocalDate? = null,
    val firstNameError: Boolean = false,
    val lastNameError: Boolean = false,
    val phoneNumberError: Boolean = false,
    val dateOfBirthError: Boolean = false
)
