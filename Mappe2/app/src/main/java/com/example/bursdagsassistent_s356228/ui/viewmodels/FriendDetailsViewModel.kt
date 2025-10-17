package com.example.bursdagsassistent_s356228.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.bursdagsassistent_s356228.data.model.Friend
import com.example.bursdagsassistent_s356228.repositories.FriendRepository
import com.example.bursdagsassistent_s356228.ui.navigation.AppDestinations
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FriendDetailsViewModel(private val friendRepository: FriendRepository, savedStateHandle: SavedStateHandle, application: Application): AndroidViewModel(application) {
    private val friendId: Int = checkNotNull(savedStateHandle[AppDestinations.FRIEND_ID])
        val friend: StateFlow<Friend?> = friendRepository.getFriendById(id = friendId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = null
            )

    fun deleteFriend(friend: Friend) {
        viewModelScope.launch {
            friendRepository.deleteFriend(friend)
        }
    }


}