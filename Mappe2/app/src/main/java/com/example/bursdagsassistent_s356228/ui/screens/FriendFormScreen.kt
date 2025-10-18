package com.example.bursdagsassistent_s356228.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Cake
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.EditCalendar
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bursdagsassistent_s356228.R
import com.example.bursdagsassistent_s356228.ui.components.FriendFormDateDialog
import com.example.bursdagsassistent_s356228.ui.components.TopBar
import com.example.bursdagsassistent_s356228.ui.viewmodels.AppViewModelProvider
import com.example.bursdagsassistent_s356228.ui.viewmodels.FriendFormViewModel
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendFormScreen(
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: FriendFormViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()
    var showDatePicker by remember { mutableStateOf(value = false) }
    val textFieldErrorMsg = stringResource(R.string.form_field_required)

    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                title = if (uiState.id != 0) stringResource(R.string.form_title_edit_friend) else stringResource(R.string.form_title_add_friend),
                startIcon = Icons.AutoMirrored.Filled.ArrowBack,
                startIconContentDescription = stringResource(R.string.arrow_icon_description),
                onStartIconClick = onNavigate
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(vertical = 50.dp, horizontal = 20.dp),

        ) {
            OutlinedTextField(
                value = uiState.firstName,
                onValueChange = { input -> viewModel.updateFirstName(firstName = input) },
                singleLine = true,
                label = { Text(text = stringResource(R.string.form_label_firstname)) },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(imageVector = Icons.Outlined.Person, contentDescription = stringResource(R.string.form_label_firstname))},
                trailingIcon = {
                    if(uiState.firstName.isNotEmpty()){
                        IconButton(
                            onClick = { viewModel.updateFirstName(firstName = "") }
                        ) {
                            Icon(imageVector = Icons.Outlined.Cancel, contentDescription = stringResource(R.string.clear_field_icon_description))
                        }
                    }
                },
                isError = uiState.firstNameError,
                supportingText = {
                    if(uiState.firstNameError){
                        Text(text = textFieldErrorMsg, color = MaterialTheme.colorScheme.error)
                    }
                }
            )

            OutlinedTextField(
                value = uiState.lastName,
                onValueChange = { input -> viewModel.updateLastName(lastName = input) },
                singleLine = true,
                label = { Text(text = stringResource(R.string.form_label_lastname)) },
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                leadingIcon = { Icon(imageVector = Icons.Outlined.Person, contentDescription = stringResource(R.string.form_label_lastname))},
                trailingIcon = {
                    if(uiState.lastName.isNotEmpty()){
                        IconButton(
                            onClick = { viewModel.updateLastName(lastName = "") }
                        ) {
                            Icon(imageVector = Icons.Outlined.Cancel, contentDescription = stringResource(R.string.clear_field_icon_description))
                        }
                    }
                },
                isError = uiState.lastNameError,
                supportingText = {
                    if(uiState.lastNameError){
                        Text(text = textFieldErrorMsg, color = MaterialTheme.colorScheme.error)
                    }
                }
            )


            OutlinedTextField(
                value = uiState.phoneNumber,
                onValueChange = { input -> viewModel.updatePhoneNumber(phoneNumber = input) },
                label = { Text(text = stringResource(R.string.form_label_phone)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                leadingIcon = { Icon(imageVector = Icons.Outlined.Phone, contentDescription = stringResource(R.string.form_label_phone))},
                trailingIcon = {
                    if(uiState.phoneNumber.isNotEmpty()){
                        IconButton(
                            onClick = { viewModel.updatePhoneNumber(phoneNumber = "") }
                        ) {
                            Icon(imageVector = Icons.Outlined.Cancel, contentDescription = stringResource(R.string.clear_field_icon_description))
                        }
                    }
                },
                isError = uiState.phoneNumberError,
                supportingText = {
                    if(uiState.phoneNumberError){
                        Text(text = textFieldErrorMsg, color = MaterialTheme.colorScheme.error)
                    }
                }
            )


            OutlinedTextField(
                value = uiState.dateOfBirth?.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) ?: "",
                onValueChange = { },
                label = { Text(text = stringResource(R.string.form_label_birthdate)) },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .clickable { showDatePicker = true },
                enabled = false,
                leadingIcon = { Icon(imageVector = Icons.Outlined.Cake, contentDescription = null)},
                trailingIcon = { Icon(imageVector = Icons.Outlined.EditCalendar, contentDescription = stringResource(R.string.edit_calendar_icon_description)) },
                isError = uiState.dateOfBirthError,
                supportingText = {
                    if(uiState.dateOfBirthError){
                        Text(text = textFieldErrorMsg, color = MaterialTheme.colorScheme.error)
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledBorderColor = if(uiState.dateOfBirthError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.outline,
                    disabledLabelColor = if(uiState.dateOfBirthError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant,
                )
            )

            Button(
                onClick = { viewModel.saveFriend(onSuccess = onNavigate) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            ) {
                Text(text = if (uiState.id != 0) stringResource(R.string.form_button_update_friend) else stringResource(R.string.form_button_save_friend))
            }
        }

        if (showDatePicker) {
            FriendFormDateDialog(
                initialDate = uiState.dateOfBirth,
                onDateSelected = { selectedDate -> viewModel.updateDateOfBirth(dateOfBirth = selectedDate) },
                onDismiss = { showDatePicker = false }
            )
        }
    }
}
