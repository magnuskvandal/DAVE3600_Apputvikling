package com.example.mittKart_s356228.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DeleteConfirmationDialog(
    placeName: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
){
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Outlined.Warning,
                contentDescription = "Advarsel",
                modifier = Modifier.size(32.dp)
            )
        },
        title = {
            Text(text = "Slette sted?")
        },
        text = {
            Text(text = "Er du sikker på at du vil slette $placeName")
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirm){
                Text(text = "Slett")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss){
                Text(text = "Avbryt")
            }
        }
    )
}