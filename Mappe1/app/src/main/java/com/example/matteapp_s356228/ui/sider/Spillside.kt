package com.example.matteapp_s356228.ui.sider

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.matteapp_s356228.ui.komponenter.TopBar
import com.example.matteapp_s356228.ui.theme.Matteapp_s356228Theme
import com.example.matteapp_s356228.R
import com.example.matteapp_s356228.ui.komponenter.Display
import com.example.matteapp_s356228.ui.komponenter.GenerellKnapp
import com.example.matteapp_s356228.ui.komponenter.Spillpanel
import com.example.matteapp_s356228.ui.komponenter.Tallpanel
import com.example.matteapp_s356228.ui.viewmodels.SpillViewModel

@Composable
fun Spillside(
    modifier: Modifier = Modifier,
    //viewModel: SpillViewModel = viewModel()
){
    Scaffold(
        modifier = modifier,
        topBar = { TopBar(tittel = stringResource(R.string.tittel), onNavigateBack = { /*TODO*/ }) }
    ){ innerPadding ->
        Card(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 16.dp),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            border = BorderStroke(width = .5.dp, color = MaterialTheme.colorScheme.outline),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Display(
                    oppgavetekst = "12 + 8", //fra state
                    svartekst = "20", //fra state
                    rettSvar = false, //fra state
                    fremdrift = 1, //fra state
                    antallOppgaver = 5, //fra state
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(8.dp))
                Tallpanel(
                    onSifferKlikk = { /*TODO*/ },
                    onTomKlikk = { /*TODO*/ },
                    onSlettKlikk = { /*TODO*/ },
                )
                Spacer(modifier = Modifier.height(16.dp))
                GenerellKnapp(
                    modifier = Modifier.fillMaxWidth(),
                    tekst = stringResource(R.string.sjekkSvar),
                    onClick = { /* TODO: Logikk for å avgi svar */ },
                    knappfarge = MaterialTheme.colorScheme.primary,
                    enabled = true //fra state
                )
                Spacer(modifier = Modifier.height(8.dp))
                GenerellKnapp(
                    modifier = Modifier.fillMaxWidth(),
                    tekst = stringResource(R.string.neste),
                    onClick = { /* TODO: Logikk for å tømme svar */ },
                    knappfarge = MaterialTheme.colorScheme.secondary,
                    enabled = true // fra state
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun SpillsidePreview() {
    Matteapp_s356228Theme(dynamicColor = false) {
        Spillside()
    }
}