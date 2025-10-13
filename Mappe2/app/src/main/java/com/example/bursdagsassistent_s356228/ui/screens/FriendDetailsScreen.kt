package com.example.bursdagsassistent_s356228.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bursdagsassistent_s356228.R
import com.example.bursdagsassistent_s356228.ui.components.FriendDetailItem
import com.example.bursdagsassistent_s356228.ui.components.TopBar
import com.example.bursdagsassistent_s356228.ui.viewmodels.AppViewModelProvider
import com.example.bursdagsassistent_s356228.ui.viewmodels.FriendDetailsViewModel
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun FriendDetailsScreen(
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    onEditClick: (Int) -> Unit
) {
    val viewModel: FriendDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val friend by viewModel.friend.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                title = friend?.let { f -> stringResource(id = R.string.friend_full_name, f.firstName, f.lastName) } ?: "",
                startIcon = Icons.AutoMirrored.Filled.ArrowBack,
                startIconContentDescription = stringResource(R.string.arrow_icon_description),
                onStartIconClick = onNavigate,
                endIcon = Icons.Default.Edit,
                endIconContentDescription = stringResource(R.string.edit_icon_description),
                onEndIconClick = { friend?.id?.let(onEditClick) }
            )
        }
    ) { innerPadding ->
        val currentFriend = friend
        if (currentFriend == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
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
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = stringResource(R.string.person_icon_description),
                    modifier = Modifier.size(150.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(32.dp))

                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    FriendDetailItem(
                        label = stringResource(R.string.details_phone_label),
                        value = currentFriend.phoneNumber
                    )
                    FriendDetailItem(
                        label = stringResource(R.string.details_date_of_birth_label),
                        value = currentFriend.dateOfBirth.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
                    )
                }
            }
        }
    }
}
