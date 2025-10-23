package com.example.bursdagsassistent_s356228.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bursdagsassistent_s356228.R
import com.example.bursdagsassistent_s356228.ui.components.BirthdayCountdownCircle
import com.example.bursdagsassistent_s356228.ui.components.DeleteConfirmationDialog
import com.example.bursdagsassistent_s356228.ui.components.FriendDetailItem
import com.example.bursdagsassistent_s356228.ui.components.FriendDetailsActionMenu
import com.example.bursdagsassistent_s356228.ui.components.TopBar
import com.example.bursdagsassistent_s356228.ui.viewmodels.AppViewModelProvider
import com.example.bursdagsassistent_s356228.ui.viewmodels.FriendDetailsViewModel
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun FriendDetailsScreen(
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    onEditClick: (Int) -> Unit,
    viewModel: FriendDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val friend by viewModel.friend.collectAsState()
    var showDeleteConfirmationDialog by rememberSaveable { mutableStateOf(false) }


    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                startIcon = Icons.AutoMirrored.Filled.ArrowBack,
                startIconContentDescription = stringResource(R.string.arrow_icon_description),
                onStartIconClick = onNavigate,
                actions = {
                    FriendDetailsActionMenu(
                        onEditClick = { friend?.id?.let(onEditClick) },
                        onDeleteClick = {
                            showDeleteConfirmationDialog = true
                        }
                    )
                }
            )
        }
    ) { innerPadding ->
        val currentFriend = friend
        if (currentFriend == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.surface),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                BirthdayCountdownCircle(
                    birthDate = currentFriend.dateOfBirth,
                    size = 200.dp
                )
                Spacer(modifier = Modifier.height(32.dp))

                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    FriendDetailItem(
                        label = stringResource(R.string.details_name_label),
                        value = "${currentFriend.firstName} ${currentFriend.lastName}"
                    )
                    FriendDetailItem(
                        label = stringResource(R.string.details_phone_label),
                        value = currentFriend.phoneNumber
                    )
                    FriendDetailItem(
                        label = stringResource(R.string.details_date_of_birth_label),
                        value = currentFriend.dateOfBirth.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
                    )
                }
                if(showDeleteConfirmationDialog){
                    DeleteConfirmationDialog(
                        onDismiss = { showDeleteConfirmationDialog = false},
                        onConfirm = { 
                            showDeleteConfirmationDialog = false
                            viewModel.deleteFriend(currentFriend)
                            onNavigate()
                        },
                        friend = currentFriend
                    )
                }
            }
        }
    }
}



