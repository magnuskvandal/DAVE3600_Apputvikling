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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.matteapp_s356228.R
import com.example.matteapp_s356228.ui.modeller.Spillstatus
import com.example.matteapp_s356228.ui.theme.Matteapp_s356228Theme

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
            .size(height = 200.dp, width = 300.dp)
            .border(
                BorderStroke(
                    width = 0.5.dp,
                    color = MaterialTheme.colorScheme.outline
                ),
                shape = MaterialTheme.shapes.medium,
            )
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = MaterialTheme.shapes.medium
            )
            .padding(vertical = 8.dp, horizontal = 12.dp),
    ) {
        if(score > 0 && spillStatus != Spillstatus.FERDIG){
            Text(
                text = stringResource(R.string.riktigeSvar, score),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                modifier = Modifier.align(Alignment.TopStart)
            )
        }
        when {
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp)
                    )
                    Text(
                        text = stringResource(R.string.spillFerdigTekst, score, antallOppgaver),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onBackground.copy(.8f),
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                    )
                }
            }
            !svarSjekket -> {
                Text(
                    text = "$oppgavetekst = $svartekst",
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 2,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 75.dp)
                )
            }
            else -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .align(Alignment.Center)
                ){
                    Text(
                        text = if(rettSvar) stringResource(R.string.rettSvar) else stringResource(R.string.feilSvar),
                        style = MaterialTheme.typography.headlineMedium,
                        color = if (rettSvar) Color(0xFF4CAF50).copy(alpha = .8f) else Color(0xFFF44336).copy(alpha = .8f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp, bottom = 10.dp)
                    )
                    if(!rettSvar && korrektSvar != null){
                        Text(
                            text = stringResource(R.string.korrektSvar, korrektSvar),
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onBackground.copy(.8f),
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp, horizontal = 20.dp)
                        )
                    }
                    Text(
                        text = stringResource(R.string.trykkNeste),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onBackground.copy(.8f),
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp, horizontal = 20.dp)
                    )
                }
            }
        }
        Text(
            text = stringResource(R.string.fremdrift, fremdrift, antallOppgaver),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFAF0E6)
@Composable
fun DisplayPreview() {
    Matteapp_s356228Theme(dynamicColor = false) {
        Box(modifier = Modifier.padding(16.dp)) {
            Display(
                oppgavetekst = "12 + 8",
                svartekst = "",
                rettSvar = false,
                fremdrift = 1,
                antallOppgaver = 5,
                svarSjekket = false,
                score = 0,
                spillStatus = Spillstatus.PÅGÅR
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFC8E6C9)
@Composable
fun DisplayPreview2() {
    Matteapp_s356228Theme(dynamicColor = false) {
        Display(
            oppgavetekst = "123 x 45",
            svartekst = "5535",
            rettSvar = false,
            fremdrift = 4,
            antallOppgaver = 10,
            svarSjekket = true,
            score = 3,
            spillStatus = Spillstatus.PÅGÅR,
            korrektSvar = "22"
        )
    }
}