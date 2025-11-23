package com.example.mittKart_s356228.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mittKart_s356228.R
import com.example.mittKart_s356228.ui.theme.MittKart_s356228Theme

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
                contentDescription = stringResource(R.string.delete_dialog_warning_icon_description),
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        },
        title = {
            Text(
                text = stringResource(R.string.delete_dialog_title),
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        text = {
            Text(
                text = stringResource(R.string.delete_dialog_message, placeName),
                color = MaterialTheme.colorScheme.onSurface,
            )
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirm){
                Text(
                    text = stringResource(R.string.delete_dialog_confirm_button),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss){
                Text(
                    text = stringResource(R.string.delete_dialog_dismiss_button),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,

                )
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun DeleteConfirmationDialogPreview(){
    MittKart_s356228Theme {
        DeleteConfirmationDialog(
            placeName = "Test Place",
            onConfirm = {},
            onDismiss = {}
        )
    }
}