package com.example.bursdagsassistent_s356228.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bursdagsassistent_s356228.data.model.Friend
import com.example.bursdagsassistent_s356228.repositories.FriendRepository
import kotlinx.coroutines.launch
import java.time.LocalDate

class FriendFormViewModel(
    private val friendRepository: FriendRepository,
    application: Application): AndroidViewModel(application) {

        fun addFriend(firstName: String, lastName: String, phoneNumber: String, dateOfBirth: LocalDate){
            viewModelScope.launch{
                friendRepository.insertFriend(Friend(firstName = firstName,lastName = lastName, phoneNumber = phoneNumber, dateOfBirth = dateOfBirth)
           )
       }
    }
}