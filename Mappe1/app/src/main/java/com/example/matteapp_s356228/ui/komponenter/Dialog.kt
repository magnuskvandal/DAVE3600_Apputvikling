package com.example.matteapp_s356228.ui.komponenter

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.matteapp_s356228.ui.theme.Matteapp_s356228Theme

// Generell dialogboks-komponent som kan brukes til bekreftelse/avbrytelse
@Composable
fun Dialog(
    onBekreft: () -> Unit,
    onAvbryt: () -> Unit,
    dialogtittel: String,
    dialogtekst: String,
    ikon: ImageVector? = null,
    ikonbeskrivelse: String? = null,
    bekreftTekst: String,
    avbrytTekst: String
){
    AlertDialog(
        containerColor = MaterialTheme.colorScheme.background,
        titleContentColor = MaterialTheme.colorScheme.primary,
        textContentColor = MaterialTheme.colorScheme.onBackground,

        icon = {
            if (ikon != null)
                Icon(
                    imageVector = ikon,
                    contentDescription = ikonbeskrivelse
                )
        },
        title = {
            Text(
                text = dialogtittel
            )
        },
        text = {
            Text(
                text = dialogtekst
            )
        },
        onDismissRequest = onAvbryt,
        confirmButton = {
            TextButton(
                onClick = onBekreft
            ){
                Text(text = bekreftTekst)
            }
        },
        dismissButton = {
            TextButton(
                onClick = onAvbryt
            ) {
                Text(
                    text = avbrytTekst
                )
            }
        }
    )
}