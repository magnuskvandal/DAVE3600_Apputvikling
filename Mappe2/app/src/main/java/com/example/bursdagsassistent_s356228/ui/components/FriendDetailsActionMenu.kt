package com.example.bursdagsassistent_s356228.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.bursdagsassistent_s356228.R

@Composable
fun FriendDetailsActionMenu(
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
    ){
    var expanded by rememberSaveable { mutableStateOf(value = false) }

    Box(
        modifier = modifier
    ){
        IconButton(
            onClick = { expanded = true },
            ){
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = stringResource(id = R.string.more_options_icon_description)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ){
            DropdownMenuItem(
                text = { Text(text = "Endre") },
                leadingIcon = { Icon(imageVector = Icons.Default.Edit, contentDescription = stringResource(id = R.string.edit_icon_description)) },
                onClick = {
                    onEditClick()
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text(text = "Slett") },
                leadingIcon = { Icon(imageVector = Icons.Default.Delete, contentDescription = stringResource(id = R.string.delete_friend_description)) },
                onClick = {
                    onDeleteClick()
                    expanded = false
                }
            )
        }
    }
}