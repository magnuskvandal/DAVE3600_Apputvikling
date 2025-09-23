package com.example.matteapp_s356228.ui.sider

import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.matteapp_s356228.ui.komponenter.TopBar
import com.example.matteapp_s356228.ui.theme.Matteapp_s356228Theme
import com.example.matteapp_s356228.R
import com.example.matteapp_s356228.ui.komponenter.Dialog
import com.example.matteapp_s356228.ui.komponenter.Display
import com.example.matteapp_s356228.ui.komponenter.GenerellKnapp
import com.example.matteapp_s356228.ui.komponenter.Tallpanel
import com.example.matteapp_s356228.ui.modeller.SpillUiState
import com.example.matteapp_s356228.ui.modeller.Spillstatus
import com.example.matteapp_s356228.ui.viewmodels.SpillViewModel

@Composable
fun Spillside(
    modifier: Modifier = Modifier,
    viewModel: SpillViewModel = viewModel(),
    onNavigerTilbake: () -> Unit
){
    val uiState: SpillUiState by viewModel.uiState.collectAsState()
    val spillFerdigDialog: Boolean by viewModel.spillFerdigDialog.collectAsState()
    val avbryteSpillDialog: Boolean by viewModel.avbryteSpillDialog.collectAsState()
    val spillPaagaar = uiState.spillstatus == Spillstatus.PÅGÅR
    val spilletFerdig = uiState.spillstatus == Spillstatus.FERDIG
    val sisteOppgave = uiState.nåværendeOppgave == uiState.antallOppgaver
    val handleNavigerTilbake = {
        if(spillPaagaar){
            viewModel.visAvbryteSpillDialog()
        }else{
            onNavigerTilbake()
        }
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
            tittel = stringResource(R.string.tittel),
            onNavigerTilbake = handleNavigerTilbake
            )
        }
    ){ innerPadding ->
        Card(
            modifier = Modifier
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
                    oppgavetekst = uiState.oppgavetekst,
                    svartekst = uiState.brukersvar,
                    rettSvar = uiState.rettSvar,
                    fremdrift = uiState.nåværendeOppgave,
                    antallOppgaver = uiState.antallOppgaver,
                    modifier = Modifier,
                    svarSjekket = uiState.svarSjekket,
                    score = uiState.score
                )
                Spacer(modifier = Modifier.height(8.dp))
                Tallpanel(
                    onSifferKlikk = { siffer -> viewModel.velgSiffer(tall = siffer) },
                    onTomKlikk = { viewModel.tømBrukersvar() },
                    onSlettKlikk = { viewModel.slettSisteSiffer() },
                )
                Spacer(modifier = Modifier.height(16.dp))
                GenerellKnapp(
                    modifier = Modifier.fillMaxWidth(),
                    tekst = stringResource(R.string.sjekkSvar),
                    onClick = {
                        viewModel.sjekkSvar()
                    },
                    knappfarge = MaterialTheme.colorScheme.primary,
                    enabled = !uiState.rettSvar && !spilletFerdig
                )
                Spacer(modifier = Modifier.height(12.dp))
                if(sisteOppgave){
                    GenerellKnapp(
                        modifier = Modifier.fillMaxWidth(),
                        tekst = if (spilletFerdig) stringResource(R.string.startNyttSpill)  else stringResource(R.string.avsluttRunde),
                        onClick = { if (spilletFerdig){
                            viewModel.startNyttSpill()
                        } else{
                            viewModel.avsluttSpill()
                            viewModel.visSpillFerdigDialog()
                        } },
                        knappfarge = MaterialTheme.colorScheme.secondary,
                        enabled = true
                    )
                }else{
                    GenerellKnapp(
                        modifier = Modifier.fillMaxWidth(),
                        tekst = stringResource(R.string.neste),
                        onClick = { viewModel.nesteOppgave() },
                        knappfarge = MaterialTheme.colorScheme.secondary,
                        enabled = true
                    )
                }
                if(spillFerdigDialog){
                    Dialog(
                        onBekreft = {
                            viewModel.lukkSpillFerdigDialog()
                            viewModel.startNyttSpill()
                            },
                        onAvbryt = {
                            viewModel.lukkSpillFerdigDialog()
                        },
                        dialogtittel = stringResource(R.string.spillFerdigTittel),
                        dialogtekst = stringResource(R.string.spillFerdigTekst, uiState.score, uiState.antallOppgaver),
                        bekreftTekst = stringResource(R.string.ja),
                        avbrytTekst = stringResource(R.string.nei)
                    )
                }
                if(avbryteSpillDialog){
                    Dialog(
                        onBekreft = {
                            viewModel.lukkAvbryteSpillDialog()
                            viewModel.avsluttSpill()
                            onNavigerTilbake()
                        },
                        onAvbryt = {
                            viewModel.lukkAvbryteSpillDialog()
                        },
                        dialogtittel = stringResource(R.string.avbryteSpillTittel),
                        dialogtekst = stringResource(R.string.avbryteSpillTekst),
                        bekreftTekst = stringResource(R.string.ja),
                        avbrytTekst = stringResource(R.string.nei)
                    )
                }
                BackHandler{
                    handleNavigerTilbake()
                }
            }
        }

    }
}
