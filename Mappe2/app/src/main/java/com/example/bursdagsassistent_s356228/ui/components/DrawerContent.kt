package com.example.bursdagsassistent_s356228.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bursdagsassistent_s356228.R
import com.example.bursdagsassistent_s356228.ui.theme.Bursdagsassistent_s356228Theme

@Composable
fun DrawerContent(
    modifier: Modifier = Modifier,
    onFriendListClick: () -> Unit,
    onPreferencesClick: () -> Unit,
    isFriendListSelected: Boolean,
    isPreferencesSelected: Boolean
) {
    val itemColors = NavigationDrawerItemDefaults.colors(
        selectedContainerColor = MaterialTheme.colorScheme.primary,
        unselectedContainerColor = MaterialTheme.colorScheme.background,
        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
        unselectedIconColor = MaterialTheme.colorScheme.onSurface,
        selectedTextColor = MaterialTheme.colorScheme.onPrimary,
        unselectedTextColor = MaterialTheme.colorScheme.onSurface
    )

    ModalDrawerSheet(
        modifier = modifier,
        drawerContainerColor = MaterialTheme.colorScheme.background,
        windowInsets = WindowInsets(0.dp),
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            DrawerHeader()
            Spacer(Modifier.height(12.dp))
            NavigationDrawerItem(
                label = { Text(stringResource(R.string.friend_list_title), style = MaterialTheme.typography.titleMedium) },
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.People,
                        contentDescription = stringResource(R.string.friend_list_title),
                        modifier = Modifier.size(28.dp)
                    )
                },
                selected = isFriendListSelected,
                onClick = onFriendListClick,
                modifier = Modifier.padding(horizontal = 12.dp),
                shape = RectangleShape,
                colors = itemColors
            )
            NavigationDrawerItem(
                label = { Text(stringResource(R.string.preferences_title), style = MaterialTheme.typography.titleMedium) },
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = stringResource(R.string.preferences_title),
                        modifier = Modifier.size(28.dp)
                    )
                },
                selected = isPreferencesSelected,
                onClick = onPreferencesClick,
                modifier = Modifier.padding(horizontal = 12.dp),
                shape = RectangleShape,
                colors = itemColors
            )
        }
    }
}

@Composable
fun DrawerHeader(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 70.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(height = 40.dp))
            Image(
                painter = painterResource(id = R.drawable.appikon),
                contentDescription = stringResource(R.string.app_logo_image_description),
                modifier = Modifier
                    .width(130.dp)
                    .height(80.dp),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.application_name),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DrawerHeaderPreview() {
    Bursdagsassistent_s356228Theme {
        DrawerHeader()
    }
}
