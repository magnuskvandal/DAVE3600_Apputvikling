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

@Composable
fun AntallOppgaver(
    modifier: Modifier = Modifier,
    valgtAntall: Int,
    tilgjengeligeAntall: List<Int>,
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
            tilgjengeligeAntall.forEach{ antall ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .selectable(
                            selected = (antall == valgtAntall),
                            onClick = { onAntallValgt(antall) },
                            role = Role.RadioButton
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    RadioButton(
                        selected = (antall == valgtAntall),
                        onClick = { onAntallValgt(antall) },
                        modifier = Modifier
                            .size(size = 50.dp)
                            .scale(scale = 1.3f)
                    )
                    Spacer(modifier = Modifier.width(width = 8.dp))
                    Text(
                        text = antall.toString(),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}






@Preview(showBackground = true, heightDp = 450, widthDp = 400)
@Composable
fun AntallOppgaverPreview() {
    AntallOppgaver(
        valgtAntall = 10,
        tilgjengeligeAntall = listOf(5, 10, 20, 30, 40, 50),
        onAntallValgt = {}
    )
}