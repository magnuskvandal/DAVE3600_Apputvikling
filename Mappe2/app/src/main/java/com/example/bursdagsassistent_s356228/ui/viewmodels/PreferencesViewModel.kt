package com.example.bursdagsassistent_s356228.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.bursdagsassistent_s356228.repositories.PreferencesRepository
import com.example.bursdagsassistent_s356228.ui.state.PreferencesUiState
import com.example.bursdagsassistent_s356228.workers.WorkManagerScheduler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PreferencesViewModel(val preferencesRepository: PreferencesRepository, application: Application) : AndroidViewModel(application) {
    private val workManagerScheduler: WorkManagerScheduler = WorkManagerScheduler(context = application.applicationContext)
    private val _uiState: MutableStateFlow<PreferencesUiState> = MutableStateFlow(value = PreferencesUiState())
    val uiState: StateFlow<PreferencesUiState> = _uiState.asStateFlow()

    init{
        val savedMessage = preferencesRepository.getDefaultSmsMessage()
        _uiState.update {
            currentState -> currentState.copy(
                smsServiceEnabled = preferencesRepository.getSmsService(),
                defaultSmsMessage = savedMessage,
                editableMessage = savedMessage
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
        if(enabled){
            workManagerScheduler.scheduleBirthdayCheck()
        }else{
            workManagerScheduler.cancelBirthdayCheck()
        }
    }

    fun startEditingMessage(){
        _uiState.update{
            currentState -> currentState.copy(
                isEditingDefaultSmsMessage = true,
                editableMessage = currentState.defaultSmsMessage
            )
        }
    }

    fun onEditableMessageChange(newMessage: String){
        _uiState.update{
            currentState -> currentState.copy(
                editableMessage = newMessage
            )
        }
    }

    fun cancelEditingMessage(){
        _uiState.update{
            currentState -> currentState.copy(
                isEditingDefaultSmsMessage = false
            )
        }
    }

    fun updateDefaultSmsMessage(){
        val newDefaultMessage = _uiState.value.editableMessage
        preferencesRepository.setDefaultSmsMessage(newDefaultMessage)
        _uiState.update{
            currentState -> currentState.copy(
            isEditingDefaultSmsMessage = false,
                defaultSmsMessage = newDefaultMessage
            )
        }
    }
}