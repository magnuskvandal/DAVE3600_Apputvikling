package com.example.mittKart_s356228.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mittKart_s356228.R
import com.example.mittKart_s356228.ui.theme.MittKart_s356228Theme

@Composable
fun AddPlaceSheet(
    name: String,
    description: String,
    address: String,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onDismiss: () -> Unit,
    hasAttemptedSave: Boolean,
    modifier: Modifier = Modifier,
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .background(MaterialTheme.colorScheme.surface)
    ){
        TextButton(
            onClick = onDismiss,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ){
            Text(
                text = stringResource(R.string.close_sheet_button),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Text(
            text = stringResource(R.string.add_place_title),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ){
            Spacer(modifier = Modifier.weight(0.3f))

            if(address.isNotEmpty()){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.add_place_selected_address),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = address,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                label = {
                    Text(
                        text = stringResource(R.string.add_place_name_label)
                    )
                        },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = stringResource(R.string.add_place_location_icon_description),
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                trailingIcon = {
                    if(name.isNotEmpty()){
                        IconButton(onClick = { onNameChange("") }){ 
                            Icon(
                                imageVector = Icons.Outlined.Cancel,
                                contentDescription = stringResource(R.string.add_place_cancel_icon_description),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                },
                singleLine = true,
                isError = hasAttemptedSave && name.isBlank(),
                supportingText = {
                    if(hasAttemptedSave && name.isBlank()){
                        Text(
                            text = stringResource(R.string.add_place_required_field),
                            color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = description,
                onValueChange = onDescriptionChange,
                label = { Text(stringResource(R.string.description_label)) },
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Info,
                        contentDescription = stringResource(R.string.add_place_info_icon_description),
                        tint = MaterialTheme.colorScheme.primary
                    )
                              },
                trailingIcon = {
                    if(description.isNotEmpty()){
                        IconButton(onClick = { onDescriptionChange("") }) {
                            Icon(
                                imageVector = Icons.Outlined.Cancel,
                                contentDescription = stringResource(R.string.add_place_cancel_icon_description),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                },
                minLines = 3,
                isError = hasAttemptedSave && description.isBlank(),
                supportingText = {
                    if(hasAttemptedSave && description.isBlank()){
                        Text(
                            text = stringResource(R.string.add_place_required_field),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onSaveClick,
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ){
                Text(
                    text = stringResource(R.string.add_place_save_button),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddPlaceSheetPreview() {
    MittKart_s356228Theme{
        AddPlaceSheet(
            name = "dfsdf",
            description = "sdfsf",
            address = "Sverres gate 9, 0652 Oslo",
            onNameChange = {},
            onDescriptionChange = {},
            onSaveClick = {},
            onDismiss = {},
            hasAttemptedSave = true
        )
    }
}
