package com.example.bursdagsassistent_s356228.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String = "" ,
    modifier: Modifier = Modifier,
    startIcon: ImageVector? = null,
    startIconContentDescription: String? = null,
    onStartIconClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Normal,
            )
        },
        modifier = modifier,
        navigationIcon = {
            if (startIcon != null) {
                IconButton(onClick = onStartIconClick) {
                    Icon(
                        imageVector = startIcon,
                        contentDescription = startIconContentDescription
                    )
                }
            }
        },
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
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
