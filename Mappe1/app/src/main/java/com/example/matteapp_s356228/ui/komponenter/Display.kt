package com.example.matteapp_s356228.ui.komponenter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.matteapp_s356228.R
import com.example.matteapp_s356228.ui.theme.Matteapp_s356228Theme

@Composable
fun Display(
    modifier: Modifier = Modifier,
    oppgavetekst: String,
    svartekst: String,
    rettSvar: Boolean,
    fremdrift: Int,
    antallOppgaver: Int
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
            text = stringResource(R.string.fremdrift, fremdrift, antallOppgaver), // denne må endres til å hente fra strings.xml senere
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
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
                antallOppgaver = 5
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
            rettSvar = true,
            fremdrift = 4,
            antallOppgaver = 10
        )
    }
}