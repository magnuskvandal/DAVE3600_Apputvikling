package com.example.matteapp_s356228.ui.komponenter
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.matteapp_s356228.ui.theme.Matteapp_s356228Theme
import com.example.matteapp_s356228.R

@Composable
fun Spillpanel(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
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
            Display(oppgavetekst = "12 + 8", svartekst = "20", rettSvar = false, score = "3/5")
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
                enabled = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            GenerellKnapp(
                modifier = Modifier.fillMaxWidth(),
                tekst = stringResource(R.string.neste),
                onClick = { /* TODO: Logikk for å tømme svar */ },
                knappfarge = MaterialTheme.colorScheme.secondary,
                enabled = true
            )
        }
    }
}

@Composable
fun GenerellKnapp(
    modifier: Modifier = Modifier,
    tekst: String,
    onClick: () -> Unit,
    knappfarge: Color = MaterialTheme.colorScheme.surface,
    enabled: Boolean = true
){
    Button(
        onClick= onClick,
        modifier = modifier.sizeIn(minHeight = 48.dp),
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.buttonColors(
            containerColor = knappfarge,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation =  5.dp,
        ),

        border = BorderStroke(width = .5.dp, color = MaterialTheme.colorScheme.outline),
        enabled = enabled
    ){
        Text(
            text = tekst,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun Tallpanelknapp(
    modifier: Modifier = Modifier,
    tekst: String,
    onClick: () -> Unit,
    knappfarge: Color = MaterialTheme.colorScheme.surface,
){
    Button(
        onClick= onClick,
        modifier = modifier.sizeIn(minWidth = 65.dp, minHeight = 65.dp),
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.buttonColors(
            containerColor = knappfarge,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation =  20.dp,
            ),
        border = BorderStroke(width = .5.dp, color = MaterialTheme.colorScheme.outline),
    ){
        Text(
            text = tekst,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.ExtraBold
        )
    }
}


@Composable
fun Tallpanel(
    onSifferKlikk: (String) -> Unit,
    onTomKlikk: () -> Unit,
    onSlettKlikk: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        val knappeModifier = Modifier.weight(1f)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            modifier = Modifier.fillMaxWidth()
        ){
            Tallpanelknapp(tekst = stringResource(R.string.nummer_7), onClick = { onSifferKlikk("7") }, modifier = knappeModifier)
            Tallpanelknapp(tekst = stringResource(R.string.nummer_8), onClick = { onSifferKlikk("8") }, modifier = knappeModifier)
            Tallpanelknapp(tekst = stringResource(R.string.nummer_9), onClick = { onSifferKlikk("9") }, modifier = knappeModifier)
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            modifier = Modifier.fillMaxWidth()
        ){
            Tallpanelknapp(tekst = stringResource(R.string.nummer_4), onClick = { onSifferKlikk("4") }, modifier = knappeModifier)
            Tallpanelknapp(tekst = stringResource(R.string.nummer_5), onClick = { onSifferKlikk("5") }, modifier = knappeModifier)
            Tallpanelknapp(tekst = stringResource(R.string.nummer_6), onClick = { onSifferKlikk("6") }, modifier = knappeModifier)
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            modifier = Modifier.fillMaxWidth()
        ){
            Tallpanelknapp(tekst = stringResource(R.string.nummer_1), onClick = { onSifferKlikk("1") }, modifier = knappeModifier)
            Tallpanelknapp(tekst = stringResource(R.string.nummer_2), onClick = { onSifferKlikk("2") }, modifier = knappeModifier)
            Tallpanelknapp(tekst = stringResource(R.string.nummer_3), onClick = { onSifferKlikk("3") }, modifier = knappeModifier)
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            modifier = Modifier.fillMaxWidth()
        ){
            Tallpanelknapp(
                tekst = stringResource(R.string.tømSvar),
                onClick = onTomKlikk,
                modifier = knappeModifier,
            )
            Tallpanelknapp(tekst = "0", onClick = { onSifferKlikk("0") }, modifier = knappeModifier)
            Tallpanelknapp(
                tekst = stringResource(R.string.slettTall),
                onClick = onSlettKlikk,
                modifier = knappeModifier,
            )
        }
    }
}

@Composable
fun Display(
    modifier: Modifier = Modifier,
    oppgavetekst: String,
    svartekst: String,
    rettSvar: Boolean,
    score: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 170.dp)
            .border(BorderStroke(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.outline),
                shape = MaterialTheme.shapes.medium
            )
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = MaterialTheme.shapes.medium
            )
            .padding(vertical = 8.dp, horizontal = 12.dp),
    ) {
        Text(
            text = "$oppgavetekst = $svartekst",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 2,
            modifier = Modifier.align(Alignment.Center)
        )
        val tilbakemeldingsfarge = if (rettSvar) Color(0xFF4CAF50) else Color(0xFFF44336)
        val tilbakemeldingstekst = if (rettSvar) stringResource(R.string.rettSvar) else stringResource(R.string.feilSvar)
        // Denne betingelsen må endres senere for å vise tilbakemelding bare når brukeren har svart
        if(true){
            Text(
                text = tilbakemeldingstekst,
                style = MaterialTheme.typography.headlineMedium,
                color = tilbakemeldingsfarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.BottomStart)
            )
        }
        Text(
            text = score,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFC8E6C9)
@Composable
fun ProblemDisplayDesignPreview() {
    Matteapp_s356228Theme(dynamicColor = false) {
        Display(
            oppgavetekst = "123 x 45",
            svartekst = "5535",
            rettSvar = true,
            score = "5/5"
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFAF0E6)
@Composable
fun ProblemDisplayDesignOnBackgroundPreview() {
    Matteapp_s356228Theme(dynamicColor = false) {
        Box(modifier = Modifier.padding(16.dp)) { // For å se paddingen
            Display(
                oppgavetekst = "12 + 8",
                svartekst = "",
                rettSvar = false,
                score = "3/5"
            )
        }
    }
}

@Preview(showBackground = true, heightDp = 700, widthDp = 409)
@Composable
fun SpillpanelPreview() {
    Matteapp_s356228Theme(dynamicColor = false){
        Spillpanel()
    }
}

@Preview(showBackground = true)
@Composable
fun KalkulatorknappPreview() {
    Matteapp_s356228Theme(dynamicColor = false){
        GenerellKnapp(tekst = "1", onClick = { /*TODO*/ })
    }
}

@Preview(showBackground = true)
@Composable
fun TallpanelPreview() {
    Matteapp_s356228Theme(dynamicColor = false){
        Tallpanel(
            onSifferKlikk = {},
            onTomKlikk = {},
            onSlettKlikk = {},
        )
    }
}