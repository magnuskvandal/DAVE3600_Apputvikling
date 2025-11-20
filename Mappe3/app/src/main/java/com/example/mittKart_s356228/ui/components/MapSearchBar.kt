package com.example.mittKart_s356228.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.mittKart_s356228.R


@Composable
fun MapSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    searchZoomLevel: Float,
    onZoomLevelChange: (Float) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val chipColors = FilterChipDefaults.elevatedFilterChipColors(
        containerColor = MaterialTheme.colorScheme.surface,
        labelColor = MaterialTheme.colorScheme.primary,
        iconColor = MaterialTheme.colorScheme.primary,
        selectedContainerColor = MaterialTheme.colorScheme.primary,
        selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
        selectedLeadingIconColor = MaterialTheme.colorScheme.onPrimary
    )
    val chipElevation = FilterChipDefaults.elevatedFilterChipElevation(elevation = 8.dp)

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(30.dp),
            shadowElevation = 8.dp
        ) {
            TextField(
                value = query,
                onValueChange = onQueryChange,
                placeholder = {
                    val placeholderText = when(searchZoomLevel){
                        5f -> stringResource(R.string.search_bar_placeholder_country)
                        10f -> stringResource(R.string.search_bar_placeholder_city)
                        else -> stringResource(R.string.search_bar_placeholder_place_or_address)
                    }
                    Text(
                        text = placeholderText,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_icon_description),
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearch()
                        keyboardController?.hide()
                    }
                ),

                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ElevatedFilterChip(
                selected = searchZoomLevel == 5f,
                onClick = { onZoomLevelChange(5f) },
                label = {
                    Text(
                        text = stringResource(R.string.country_label)
                    )
                        },
                leadingIcon = if (searchZoomLevel == 5f) {
                    {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = stringResource(R.string.filter_check_icon_description)
                        )
                    }
                } else null,
                colors = chipColors,
                elevation = chipElevation,
            )
            ElevatedFilterChip(
                selected = searchZoomLevel == 10f,
                onClick = { onZoomLevelChange(10f) },
                label = {
                    Text(
                        text = stringResource(R.string.city_label)
                    )
                },
                leadingIcon = if (searchZoomLevel == 10f) {
                    {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = stringResource(R.string.filter_check_icon_description)
                        )
                    }
                } else null,
                colors = chipColors,
                elevation = chipElevation,
            )
            ElevatedFilterChip(
                selected = searchZoomLevel == 17f,
                onClick = { onZoomLevelChange(17f) },
                label = {
                    Text(
                        text = stringResource(R.string.place_label)
                    )
                },
                leadingIcon = if (searchZoomLevel == 17f) {
                    {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = stringResource(R.string.filter_check_icon_description)
                        )
                    }
                } else null,
                colors = chipColors,
                elevation = chipElevation,
            )
        }
    }
}