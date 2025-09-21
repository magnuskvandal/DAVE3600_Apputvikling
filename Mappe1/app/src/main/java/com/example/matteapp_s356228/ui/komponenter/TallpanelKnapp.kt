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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.matteapp_s356228.ui.theme.Matteapp_s356228Theme

@Composable
fun Tallpanelknapp(
    modifier: Modifier = Modifier,
    tekst: String,
    onClick: () -> Unit,
    knappfarge: Color = MaterialTheme.colorScheme.surface,
    textStyle : TextStyle = MaterialTheme.typography.headlineLarge,
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
            style = textStyle,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TallpanelKnappPreview() {
    Matteapp_s356228Theme(dynamicColor = false){
        GenerellKnapp(tekst = "1", onClick = { /*TODO*/ })
    }
}