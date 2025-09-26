package com.example.matteapp_s356228.ui.komponenter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.matteapp_s356228.ui.theme.Matteapp_s356228Theme

@Composable
fun GenerellKnapp(
    modifier: Modifier = Modifier,
    tekst: String,
    onClick: () -> Unit,
    knappfarge: Color = MaterialTheme.colorScheme.tertiary,
    enabled: Boolean = true
){
    Button(
        onClick= onClick,
        modifier = modifier.sizeIn(minHeight = 48.dp),
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.buttonColors(
            containerColor = knappfarge,
            contentColor = MaterialTheme.colorScheme.onTertiary,
            disabledContainerColor = knappfarge.copy(alpha = 0.12f),
            disabledContentColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.38f)
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

@Preview(showBackground = true)
@Composable
fun GenerellKnappPreview(){
    Matteapp_s356228Theme {
        GenerellKnapp(
            tekst = "eksempel",
            onClick = {},
        )
    }
}