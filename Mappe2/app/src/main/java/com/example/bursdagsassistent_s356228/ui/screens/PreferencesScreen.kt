package com.example.bursdagsassistent_s356228.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bursdagsassistent_s356228.R
import com.example.bursdagsassistent_s356228.ui.components.DrawerContent
import com.example.bursdagsassistent_s356228.ui.components.TopBar
import com.example.bursdagsassistent_s356228.ui.theme.Bursdagsassistent_s356228Theme
import com.example.bursdagsassistent_s356228.ui.viewmodels.AppViewModelProvider
import com.example.bursdagsassistent_s356228.ui.viewmodels.PreferencesViewModel
import kotlinx.coroutines.launch

@Composable
fun PreferencesScreen(
    modifier: Modifier = Modifier,
    onFriendListClick: () -> Unit,
    viewModel: PreferencesViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()

    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onFriendListClick = {
                    onFriendListClick()
                    scope.launch { drawerState.close() }
                },
                onPreferencesClick = { scope.launch { drawerState.close() } },
                isFriendListSelected = false,
                isPreferencesSelected = true
            )
        }
    ){
        Scaffold(
            topBar = {
                TopBar(
                    title = stringResource(R.string.preferences_title),
                    startIcon = Icons.Default.Menu,
                    startIconContentDescription = stringResource(R.string.menu_icon_description),
                    onStartIconClick = { scope.launch { drawerState.open() } }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(vertical = 40.dp, horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(space = 20.dp)
            ){
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Aktiver SMS-tjeneste",
                        modifier = Modifier.weight(1f)
                    )
                    Switch(
                        checked = uiState.smsServiceEnabled,
                        onCheckedChange = { viewModel.updateSmsService(enabled = it) },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = MaterialTheme.colorScheme.primary,
                            checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                            uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                            uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                        )
                    )
                }

                AnimatedVisibility(visible = uiState.smsServiceEnabled) {
                    OutlinedTextField(
                        value = uiState.defaultSmsMessage,
                        onValueChange = { input -> viewModel.updateDefaultSmsMessage(message = input) },
                        label = { Text("Standard SMS-melding") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}