package com.example.bursdagsassistent_s356228.ui.state

data class PreferencesUiState(
    val smsServiceEnabled: Boolean = false,
    val defaultSmsMessage: String = "",
    val isEditingDefaultSmsMessage: Boolean = false,
    val editableMessage: String = ""
)
