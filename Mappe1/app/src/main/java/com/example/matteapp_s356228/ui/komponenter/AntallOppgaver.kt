package com.example.matteapp_s356228.ui.komponenter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.matteapp_s356228.R
import com.example.matteapp_s356228.ui.modeller.AntallOppgaverValg


// Komponent for valg av antall oppgaver per spillrunde.
@Composable
fun AntallOppgaver(
    modifier: Modifier = Modifier,
    valgtAntall: Int,
    tilgjengeligeAntall: List<AntallOppgaverValg>,
    onAntallValgt: (Int) -> Unit
    ){
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = stringResource(R.string.antallOppgaver),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.Start)
        )
        Column(
            modifier = Modifier.selectableGroup()
        ){
            tilgjengeligeAntall.forEach{ valg ->
                // hele raden gjøres klikkbar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .selectable(
                            selected = (valg.verdi == valgtAntall),
                            onClick = { onAntallValgt(valg.verdi) },
                            role = Role.RadioButton
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    RadioButton(
                        selected = (valg.verdi == valgtAntall),
                        onClick = { onAntallValgt(valg.verdi) },
                        modifier = Modifier
                            .size(size = 50.dp)
                            .scale(scale = 1.3f)
                    )
                    Spacer(modifier = Modifier.width(width = 8.dp))
                    Text(
                        text = stringResource(id = valg.visningsnavnResId),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}