package com.example.matteapp_s356228.ui.sider

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.matteapp_s356228.R
import com.example.matteapp_s356228.ui.komponenter.Navigasjonskort
import com.example.matteapp_s356228.ui.navigasjon.Ruter


@Composable
fun Startside(
    modifier: Modifier = Modifier,
    onNavigerTil: (String) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            ,
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(all = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Spacer (modifier = Modifier.height(80.dp))
            Image(
                painter = painterResource(id = R.drawable.app_ikon),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
            )
            Spacer (modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.tittel),
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(50.dp))

            Navigasjonskort(
                tekst = stringResource(R.string.startSpill),
                ikon = Icons.Filled.Calculate,
                onClick = { onNavigerTil(Ruter.Spillside.name)}
            )

            Spacer(modifier = Modifier.height(20.dp))

            Navigasjonskort(
                tekst = stringResource(R.string.omSpillet),
                ikon = Icons.Filled.Info,
                onClick = { onNavigerTil(Ruter.OmSpillet.name) }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Navigasjonskort(
                tekst = stringResource(R.string.preferanser),
                ikon = Icons.Filled.Settings,
                onClick = {onNavigerTil(Ruter.Preferanser.name)}
            )
        }
    }
}

