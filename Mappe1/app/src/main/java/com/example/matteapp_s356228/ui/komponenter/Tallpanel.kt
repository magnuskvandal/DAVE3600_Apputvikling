package com.example.matteapp_s356228.ui.komponenter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.matteapp_s356228.R
import com.example.matteapp_s356228.ui.theme.Matteapp_s356228Theme

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
                textStyle = MaterialTheme.typography.titleMedium
            )
            Tallpanelknapp(tekst = stringResource(R.string.nummer_0), onClick = { onSifferKlikk("0") }, modifier = knappeModifier)
            Tallpanelknapp(
                tekst = stringResource(R.string.slettTall),
                onClick = onSlettKlikk,
                modifier = knappeModifier,
            )
        }
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