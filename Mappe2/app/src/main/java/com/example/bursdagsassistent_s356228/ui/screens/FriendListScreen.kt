package com.example.bursdagsassistent_s356228.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bursdagsassistent_s356228.R
import com.example.bursdagsassistent_s356228.ui.components.DrawerContent
import com.example.bursdagsassistent_s356228.ui.components.FriendItem
import com.example.bursdagsassistent_s356228.ui.components.TopBar
import com.example.bursdagsassistent_s356228.ui.viewmodels.AppViewModelProvider
import com.example.bursdagsassistent_s356228.ui.viewmodels.FriendListViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FriendListScreen(
    modifier: Modifier = Modifier,
    onFriendClick: (Int) -> Unit,
    onAddFriendClick: () -> Unit,
    onPreferencesClick: () -> Unit
) {
    val viewModel: FriendListViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val groupedFriends by viewModel.groupedFriends.collectAsState()
    val collapsedGroups by viewModel.collapsedGroups.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onFriendListClick = { scope.launch { drawerState.close() } },
                onPreferencesClick = {
                    onPreferencesClick()
                    scope.launch { drawerState.close() }
               },
                isFriendListSelected = true,
                isPreferencesSelected = false
            )
        }
    ){
        Scaffold(
            topBar = {
                 TopBar(
                    title = stringResource(R.string.friend_list_title),
                    startIcon = Icons.Default.Menu,
                    startIconContentDescription = stringResource(R.string.menu_icon_description),
                    onStartIconClick = { scope.launch { drawerState.open() } }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = onAddFriendClick,
                    shape = CircleShape,
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 12.dp
                    )
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(R.string.add_friend_description))
                }
            }
        ) { innerPadding ->
            if (groupedFriends.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(R.string.friend_list_empty))
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),

                ) {
                    groupedFriends.forEach { (initial, friends) ->
                        val isExpanded = initial !in collapsedGroups

                        stickyHeader {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = MaterialTheme.colorScheme.surfaceVariant)
                                    .clickable { viewModel.toggleGroup(initial) }
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = initial.toString(),
                                    style = MaterialTheme.typography.titleSmall,
                                    modifier = Modifier.weight(1f)
                                )
                                val icon = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
                                Icon(
                                    imageVector = icon,
                                    contentDescription = if (isExpanded){
                                        stringResource(R.string.group_expanded_icon_description)
                                    }else{
                                        stringResource(R.string.group_collapsed_icon_description)

                                    }
                                )
                            }
                        }
                        item(key = "$initial") {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                AnimatedVisibility(isExpanded) {
                                    Column {
                                        friends.forEach { friend ->
                                            FriendItem(
                                                friend = friend,
                                                onClick = { onFriendClick(friend.id) }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
