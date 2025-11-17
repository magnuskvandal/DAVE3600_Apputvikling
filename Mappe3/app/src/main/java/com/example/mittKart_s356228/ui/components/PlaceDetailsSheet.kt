package com.example.mittKart_s356228.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mittKart_s356228.data.Place

@Composable
fun PlaceDetailsSheet(
    modifier: Modifier = Modifier,
    place: Place,
    onDismiss: () -> Unit,
    onDeleteClick: () -> Unit
    ){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
    )
    {
        TextButton(
            onClick = onDismiss,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
        ){
            Text(
                text = "Lukk",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = place.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp),
                textAlign = TextAlign.Center
            )
            if(!place.address.isNullOrBlank()){
                PlaceInfoRow(
                    label = "Adresse",
                    value = place.address!!
                )
            }

            PlaceInfoRow(
                label = "Beskrivelse",
                value = place.description
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onDeleteClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text("Slett Sted")
            }
        }
    }
}


@Composable
fun PlaceInfoRow(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
){
    Column(
        modifier = modifier.fillMaxWidth(),
    ){
        Column(
            modifier = Modifier.padding(vertical = 8.dp)
        ){
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceDetailsSheetPreview() {
    PlaceDetailsSheet(
        place = Place(
            id = 1,
            name = "Test Place",
            address = "123 Main Street",
            description = "This is a test place.",
            latitude = 1.0,
            longitude = 2.0
        ),
        onDismiss = {},
        onDeleteClick = {}
    )
}


@Preview(showBackground = true)
@Composable
fun placeInfoRowPreview() {
    PlaceInfoRow(
        label = "Test Label",
        value = "Test Value"
    )
}
