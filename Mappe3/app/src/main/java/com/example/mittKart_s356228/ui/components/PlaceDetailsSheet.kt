package com.example.mittKart_s356228.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mittKart_s356228.R
import com.example.mittKart_s356228.data.Place
import com.example.mittKart_s356228.ui.theme.MittKart_s356228Theme

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
            .fillMaxHeight(0.9f)
            .background(MaterialTheme.colorScheme.surface)

    )
    {
        TextButton(
            onClick = onDismiss,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
        ){
            Text(
                text = stringResource(R.string.close_sheet_button),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Text(
            text = place.name,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.weight(0.3f))

            if(!place.address.isNullOrBlank()){
                PlaceInfoRow(
                    label = stringResource(R.string.address_label),
                    value = place.address!!
                )
            }

            PlaceInfoRow(
                label = stringResource(R.string.description_label),
                value = place.description
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onDeleteClick,
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.place_details_delete_button),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
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
            modifier = Modifier.padding(vertical = 16.dp)
        ){
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
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
    MittKart_s356228Theme{
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
    
}


@Preview(showBackground = true)
@Composable
fun PlaceInfoRowPreview() {
    MittKart_s356228Theme{
        PlaceInfoRow(
            label = "Test Label",
            value = "Test Value"
        )
    }

}
