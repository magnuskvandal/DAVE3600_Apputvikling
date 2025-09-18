package com.example.matteapp_s356228.ui.sider
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.matteapp_s356228.ui.komponenter.TopBar
import com.example.matteapp_s356228.ui.theme.Matteapp_s356228Theme
import com.example.matteapp_s356228.R

@Composable
fun Prefereanserside(
    modifier: Modifier = Modifier
){
    Scaffold(
        modifier = modifier,
        topBar = { TopBar(tittel = stringResource(R.string.preferanser), onNavigateBack = { /*TODO*/ }) }
    ){ innerPadding ->
        Text(text ="midlertidig tekst" , modifier = Modifier.padding(innerPadding))
    }
}



@Preview(showBackground = true)
@Composable
fun PreferansersidePreview() {
    Matteapp_s356228Theme(dynamicColor = false) {
        Prefereanserside()
    }
}