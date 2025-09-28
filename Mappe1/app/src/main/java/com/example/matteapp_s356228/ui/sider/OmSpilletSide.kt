package com.example.matteapp_s356228.ui.sider

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.matteapp_s356228.ui.komponenter.TopBar
import com.example.matteapp_s356228.ui.theme.Matteapp_s356228Theme
import com.example.matteapp_s356228.R

// Siden som viser informasjon om spillet
@Composable
fun OmSpilletSide(
    modifier: Modifier = Modifier,
    onNavigerTilbake: () -> Unit
){
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                tittel = stringResource(R.string.omSpillet),
                onNavigerTilbake = onNavigerTilbake
            )
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(all = 40.dp)
        ){
            Text(
                text = stringResource(R.string.omSpilletOverskrift),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = stringResource(R.string.omSpilletTekst),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Normal,
                lineHeight = 25.sp


            )
        }
    }
}
