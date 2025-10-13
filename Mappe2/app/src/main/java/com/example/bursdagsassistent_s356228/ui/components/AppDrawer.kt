package com.example.bursdagsassistent_s356228.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bursdagsassistent_s356228.R

@Composable
fun AppDrawer(
    modifier: Modifier = Modifier,
    onFriendListClick: () -> Unit,
    onPreferencesClick: () -> Unit,
    isFriendListSelected: Boolean,
    isPreferencesSelected: Boolean
) {
    ModalDrawerSheet(
        modifier = modifier,
        windowInsets = WindowInsets(0.dp)
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            DrawerHeader()
            Spacer(Modifier.height(12.dp))
            NavigationDrawerItem(
                label = { Text(stringResource(R.string.friend_list_title)) },
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.People,
                        contentDescription = stringResource(R.string.friend_list_title)
                    )
                },
                selected = isFriendListSelected,
                onClick = onFriendListClick,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            NavigationDrawerItem(
                label = { Text(stringResource(R.string.preferences_title))},
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = stringResource(R.string.preferences_title)
                    )
                },
                selected = isPreferencesSelected,
                onClick = onPreferencesClick,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
    }
}