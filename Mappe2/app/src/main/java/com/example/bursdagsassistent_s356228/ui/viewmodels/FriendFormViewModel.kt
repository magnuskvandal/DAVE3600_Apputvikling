package com.example.bursdagsassistent_s356228.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentComposer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bursdagsassistent_s356228.data.model.Friend
import com.example.bursdagsassistent_s356228.repositories.FriendRepository
import com.example.bursdagsassistent_s356228.ui.navigation.AppDestinations
import com.example.bursdagsassistent_s356228.ui.state.FriendFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate


class FriendFormViewModel(
    private val friendRepository: FriendRepository,
    savedStateHandle: SavedStateHandle,
    application: Application): AndroidViewModel(application) {

    private val friendId: Int? = savedStateHandle[AppDestinations.FRIEND_ID]

    private val _uiState: MutableStateFlow<FriendFormUiState> = MutableStateFlow(value = FriendFormUiState())
    val uiState: StateFlow<FriendFormUiState> = _uiState.asStateFlow()

    init{
        if(friendId != null){
            viewModelScope.launch {
                val friend: Friend = friendRepository.getFriendById(friendId).filterNotNull().first()
                _uiState.update {
                    currentState -> currentState.copy(
                        id = friendId,
                        firstName = friend.firstName,
                        lastName = friend.lastName,
                        phoneNumber = friend.phoneNumber,
                        dateOfBirth = friend.dateOfBirth
                    )
                }
            }
        }
    }

    fun updateFirstName(firstName: String){
        if(firstName.all{ fName -> fName.isLetter() || fName.isWhitespace() }){
            _uiState.update {
                currentState -> currentState.copy(
                    firstName = firstName,
                    firstNameError = false
            )}
        }
    }

    fun updateLastName(lastName: String){
        if(lastName.all{ lName -> lName.isLetter() || lName.isWhitespace() }){
            _uiState.update{
                currentState -> currentState.copy(
                    lastName = lastName,
                    lastNameError = false
                )
            }
        }

    }

    fun updatePhoneNumber(phoneNumber: String){
        if(phoneNumber.all{phoneNum -> phoneNum.isDigit() }){
            _uiState.update{
                currentState -> currentState.copy(
                    phoneNumber = phoneNumber,
                    phoneNumberError = false
                )
            }
        }
    }

    fun updateDateOfBirth(dateOfBirth: LocalDate?){
        if(dateOfBirth != null){
            _uiState.update {
                currentState -> currentState.copy(
                    dateOfBirth = dateOfBirth,
                    dateOfBirthError = false
                )
            }
        }

    }


    fun saveFriend(onSuccess: () -> Unit){
        val currentState = _uiState.value
        val firstNameBlank = currentState.firstName.isBlank()
        val lastNameBlank = currentState.lastName.isBlank()
        val phoneNumberBlank = currentState.phoneNumber.isBlank()
        val dateOfBirthNull = currentState.dateOfBirth == null

        if(firstNameBlank || lastNameBlank || phoneNumberBlank || dateOfBirthNull){
            _uiState.update{
                current -> current.copy(
                    firstNameError = firstNameBlank,
                    lastNameError = lastNameBlank,
                    phoneNumberError = phoneNumberBlank,
                    dateOfBirthError = dateOfBirthNull
                )
            }
            return
        }

        viewModelScope.launch {
            val friendToSave = Friend(
                id = currentState.id,
                firstName = currentState.firstName.trim(),
                lastName = currentState.lastName.trim(),
                phoneNumber = currentState.phoneNumber,
                dateOfBirth = currentState.dateOfBirth
            )
            if(currentState.id == 0){
                friendRepository.insertFriend(friend = friendToSave)
            }else{
                friendRepository.updateFriend(friend = friendToSave)
            }
            onSuccess()
        }
    }

}