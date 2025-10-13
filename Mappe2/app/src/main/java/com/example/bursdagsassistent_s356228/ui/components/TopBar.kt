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

/**
 * En generell, gjenbrukbar TopAppBar for appen.
 *
 * @param title Tittelen som skal vises i midten av TopAppBar.
 * @param navigationIcon Ikonet som skal vises for navigasjon (f.eks. meny eller tilbake-pil).
 * @param navigationIconContentDescription Beskrivelse for skjermlesere.
 * @param onNavigationIconClick Lambda som kalles når navigasjonsikonet trykkes.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    navigationIcon: ImageVector,
    navigationIconContentDescription: String,
    onNavigationIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold
            )
        },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = navigationIconContentDescription
                )
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
    BirthdayAppTopBar(
        title = "Venneliste",
        navigationIcon = Icons.Filled.Menu,
        navigationIconContentDescription = "Åpne navigasjonsmeny",
        onNavigationIconClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarWithBackActionPreview() {
    BirthdayAppTopBar(
        title = "Ny venn",
        navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
        navigationIconContentDescription = "Tilbake",
        onNavigationIconClick = {}
    )
}
