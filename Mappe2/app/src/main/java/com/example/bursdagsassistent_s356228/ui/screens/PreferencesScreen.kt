package com.example.bursdagsassistent_s356228.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.bursdagsassistent_s356228.R
import com.example.bursdagsassistent_s356228.ui.components.DrawerContent
import com.example.bursdagsassistent_s356228.ui.components.TopBar
import kotlinx.coroutines.launch

@Composable
fun PreferencesScreen(
    modifier: Modifier = Modifier,
    onFriendListClick: () -> Unit
){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
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
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Preferanser")
            }
        }
    }
}
