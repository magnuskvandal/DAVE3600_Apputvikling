package com.example.bursdagsassistent_s356228.ui.viewmodels

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bursdagsassistent_s356228.BirthdayAssistantApplication

object AppViewModelProvider {
    val Factory = viewModelFactory{
        initializer{
            val savedStateHandle = createSavedStateHandle()
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BirthdayAssistantApplication)
            FriendFormViewModel(
                friendRepository = application.friendRepository,
                savedStateHandle = savedStateHandle,
                application = application
            )
        }
        initializer{
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BirthdayAssistantApplication)
            FriendListViewModel(
                friendRepository = application.friendRepository,
                application = application
            )
        }
        initializer{
            val savedStateHandle = createSavedStateHandle()
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BirthdayAssistantApplication)
            FriendDetailsViewModel(
                friendRepository = application.friendRepository,
                savedStateHandle = savedStateHandle,
                application = application
            )
        }
    }
}