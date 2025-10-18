package com.example.bursdagsassistent_s356228.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.bursdagsassistent_s356228.repositories.PreferencesRepository
import com.example.bursdagsassistent_s356228.ui.state.PreferencesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PreferencesViewModel(val preferencesRepository: PreferencesRepository, application: Application) : AndroidViewModel(application) {
    private val _uiState: MutableStateFlow<PreferencesUiState> = MutableStateFlow(value = PreferencesUiState())
    val uiState: StateFlow<PreferencesUiState> = _uiState.asStateFlow()

    init{
        _uiState.update {
            currentState -> currentState.copy(
                smsServiceEnabled = preferencesRepository.getSmsService(),
                defaultSmsMessage = preferencesRepository.getDefaultSmsMessage()
            )
        }
    }

    fun updateSmsService(enabled: Boolean){
        preferencesRepository.setSmsService(enabled = enabled)
        _uiState.update {
            currentState -> currentState.copy(
                smsServiceEnabled = enabled
            )
        }
    }

    fun updateDefaultSmsMessage(message: String){
        preferencesRepository.setDefaultSmsMessage(message = message)
        _uiState.update{
            currentState -> currentState.copy(
                defaultSmsMessage = message
            )
        }
    }
}