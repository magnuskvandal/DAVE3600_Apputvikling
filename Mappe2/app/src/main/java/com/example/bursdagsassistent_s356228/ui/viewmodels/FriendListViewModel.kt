package com.example.bursdagsassistent_s356228.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bursdagsassistent_s356228.data.model.Friend
import com.example.bursdagsassistent_s356228.repositories.FriendRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FriendListViewModel(private val friendRepository: FriendRepository, application: Application) : AndroidViewModel(application){
    val allFriends: StateFlow<List<Friend>> = friendRepository.allFriends.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    val groupedFriends: StateFlow<Map<Char, List<Friend>>> = allFriends
        .map { friend ->
            friend.groupBy { friend -> friend.firstName.first().uppercaseChar() }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyMap()
        )

    private val _collapsedGroups: MutableStateFlow<Set<Char>> = MutableStateFlow<Set<Char>>(value = emptySet())
    val collapsedGroups: StateFlow<Set<Char>> = _collapsedGroups.asStateFlow()

    fun toggleGroup(initial: Char){
        val currentSet = _collapsedGroups.value.toMutableSet()
        if(currentSet.contains(element = initial)){
            currentSet.remove(element = initial)
        }else{
            currentSet.add(element = initial)
        }
        _collapsedGroups.value = currentSet.toSet()
    }
}