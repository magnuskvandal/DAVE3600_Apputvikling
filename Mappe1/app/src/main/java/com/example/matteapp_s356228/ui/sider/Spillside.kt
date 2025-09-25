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
import androidx.compose.ui.graphics.Color
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
                containerColor = MaterialTheme.colorScheme.tertiary),
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
                    score = uiState.score,
                    spillStatus = uiState.spillstatus,
                    korrektSvar = uiState.korrektSvar
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
                    tekst = if(sisteOppgave && !spilletFerdig){
                        stringResource(R.string.sjekkSvar)
                    }else if(spilletFerdig){
                        stringResource(R.string.ja)
                    }else{
                        stringResource(R.string.sjekkSvar)
                    },
                    onClick = {
                        if(sisteOppgave && !spilletFerdig){
                            viewModel.sjekkSvar()
                        }else if(spilletFerdig){
                            viewModel.startNyttSpill()
                        }else{
                            viewModel.sjekkSvar()
                        }
                    },
                    enabled = if (spillPaagaar){
                        if(uiState.svarSjekket) false else true
                    }else{
                        true
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
                GenerellKnapp(
                    modifier = Modifier.fillMaxWidth(),
                   if(spilletFerdig){
                        stringResource(R.string.nei)
                    }else{
                        if(uiState.svarSjekket) stringResource(R.string.neste) else stringResource(R.string.hoppOver)
                    },
                    onClick = {
                        if(sisteOppgave && !spilletFerdig){
                            viewModel.avsluttSpill()
                        }else if(spilletFerdig){
                            onNavigerTilbake()
                        }else{
                            viewModel.nesteOppgave()
                        }
                    },
                    enabled = true
                )
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
