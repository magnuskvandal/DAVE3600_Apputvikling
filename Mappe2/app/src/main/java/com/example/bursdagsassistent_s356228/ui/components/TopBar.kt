package com.example.bursdagsassistent_s356228.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    startIcon: ImageVector,
    startIconContentDescription: String,
    onStartIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    endIcon: ImageVector? = null, // Valgfritt ikon
    endIconContentDescription: String? = null, // Valgfri beskrivelse
    onEndIconClick: (() -> Unit)? = null // Valgfri klikk-håndterer
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Normal
            )
        },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onStartIconClick) {
                Icon(
                    imageVector = startIcon,
                    contentDescription = startIconContentDescription
                )
            }
        },
        actions = {
            if (endIcon != null && onEndIconClick != null) {
                IconButton(onClick = onEndIconClick) {
                    Icon(
                        imageVector = endIcon,
                        contentDescription = endIconContentDescription
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarWithMenuPreview() {
    TopBar(
        title = "Venneliste",
        startIcon = Icons.Filled.Menu,
        startIconContentDescription = "Åpne navigasjonsmeny",
        onStartIconClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarWithBackActionPreview() {
    TopBar(
        title = "Ny venn",
        startIcon = Icons.AutoMirrored.Filled.ArrowBack,
        startIconContentDescription = "Tilbake",
        onStartIconClick = {}
    )
}
