package com.example.matteapp_s356228.ui.komponenter
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.matteapp_s356228.ui.theme.Matteapp_s356228Theme



@Composable
fun Navigasjonskort(
    modifier: Modifier = Modifier,
    tekst: String,
    ikon: ImageVector,
    onClick: () -> Unit
    ){
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 20.dp
        ),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        onClick = onClick
    ){
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Icon(
                imageVector = ikon,
                contentDescription = null,
                modifier = Modifier.size(60.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier
                .width(50.dp)
            )
            Text(
                text = tekst,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigasjonskortPreview() {
    Matteapp_s356228Theme(dynamicColor = false) {
        Navigasjonskort(
            tekst = "Start Spill",
            ikon = Icons.Filled.PlayArrow,
            onClick = {}
        )
    }
}
