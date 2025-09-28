package com.example.matteapp_s356228.ui.komponenter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.matteapp_s356228.R
import com.example.matteapp_s356228.ui.modeller.Spillstatus
import com.example.matteapp_s356228.ui.theme.Matteapp_s356228Theme
import com.example.matteapp_s356228.ui.theme.spillDisplay


// Displayet til spillet som viser oppgave, svar, fremdrift, score og tilbakemelding
@Composable
fun Display(
    modifier: Modifier = Modifier,
    oppgavetekst: String,
    svartekst: String,
    rettSvar: Boolean,
    fremdrift: Int,
    antallOppgaver: Int,
    svarSjekket: Boolean,
    score: Int,
    korrektSvar: String? = null,
    spillStatus: Spillstatus,
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .size(height = 220.dp, width = 300.dp)
            .border(
                BorderStroke(
                    width = 0.5.dp,
                    color = MaterialTheme.colorScheme.outline
                ),
                shape = MaterialTheme.shapes.medium,
            )
            .background(
                color = spillDisplay,
                shape = MaterialTheme.shapes.medium
            )
            .padding(vertical = 6.dp, horizontal = 12.dp),
    ) {
        // Viser score om man har minst ett riktig svar og spillet ikke er ferdig
        if(score > 0 && spillStatus != Spillstatus.FERDIG){
            Text(
                text = stringResource(R.string.riktigeSvar, score),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                modifier = Modifier.align(Alignment.TopStart)
            )
        }
        // Viser forskjellig innhold basert på spillstatus og om svaret er sjekket
        when {
            // Hvis spillet er ferdig vises total score
            spillStatus == Spillstatus.FERDIG -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(vertical = 30.dp)
                ) {
                    Text(
                        text = stringResource(R.string.spillFerdigTittel, score, antallOppgaver),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground.copy(.8f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp)
                    )
                    Text(
                        text = stringResource(R.string.spillFerdigTekst, score, antallOppgaver),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground.copy(.8f),
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier
                            .padding(vertical = 2.dp)
                    )
                }
            }
            // Viser oppgave og svar når svaret ikke er sjekket
            !svarSjekket -> {
                Text(
                    text = "$oppgavetekst = $svartekst",
                    style = MaterialTheme.typography.displaySmall,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 2,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 75.dp)
                )
            }
            // Viser tilbakemelding etter svaret er sjekket
            else -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .align(Alignment.Center)
                ){
                    Text(
                        text = if(rettSvar) stringResource(R.string.rettSvar) else stringResource(R.string.feilSvar), // Forskjellig tekst basert på om svaret var rett eller galt
                        style = MaterialTheme.typography.headlineMedium,
                        color = if (rettSvar) Color(0xFF4CAF50).copy(alpha = .8f) else Color(0xFFF44336).copy(alpha = .8f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp, bottom = 10.dp)
                    )
                    // Viser korrekt svar om svaret var feil
                    if(!rettSvar && korrektSvar != null){
                        Text(
                            text = stringResource(R.string.korrektSvar, korrektSvar),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground.copy(.8f),
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Start,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp, horizontal = 20.dp)
                        )
                    }
                    Text(
                        text = stringResource(R.string.trykkNeste),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground.copy(.8f),
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start,
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp, horizontal = 20.dp)
                    )
                }
            }
        }
        // Viser fremdrift øverst til høyre
        Text(
            text = stringResource(R.string.fremdrift, fremdrift, antallOppgaver),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}