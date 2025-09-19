package com.example.matteapp_s356228.ui.sider
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.matteapp_s356228.ui.komponenter.TopBar
import com.example.matteapp_s356228.ui.theme.Matteapp_s356228Theme
import com.example.matteapp_s356228.R
import com.example.matteapp_s356228.ui.komponenter.AntallOppgaver

@Composable
fun Preferanserside(
    modifier: Modifier = Modifier
){
    // Midlertidig state management for valgt antall oppgaver
    var valgtAntallState by remember { mutableStateOf(5) }
    val muligeAntall = listOf(5, 10, 15)

    Scaffold(
        modifier = modifier,
        topBar = { TopBar(tittel = stringResource(R.string.preferanser), onNavigateBack = { /*TODO*/ }) }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(all = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            AntallOppgaver(
                valgtAntall = valgtAntallState,
                tilgjengeligeAntall = muligeAntall,
                onAntallValgt = {valgtAntall -> valgtAntallState = valgtAntall},
                modifier = Modifier
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreferansersidePreview() {
    Matteapp_s356228Theme(dynamicColor = false) {
        Preferanserside()
    }
}