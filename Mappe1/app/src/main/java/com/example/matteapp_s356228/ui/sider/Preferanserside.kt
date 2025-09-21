package com.example.matteapp_s356228.ui.sider
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.matteapp_s356228.ui.komponenter.TopBar
import com.example.matteapp_s356228.R
import com.example.matteapp_s356228.ui.komponenter.AntallOppgaver
import com.example.matteapp_s356228.ui.viewmodels.PreferanserViewModel

@Composable
fun Preferanserside(
    modifier: Modifier = Modifier,
    viewModel: PreferanserViewModel = viewModel()
){

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
                valgtAntall = viewModel.valgtAntallState.value,
                tilgjengeligeAntall = viewModel.muligeAntall,
                onAntallValgt = { valgtAntall -> viewModel.settAntall(valgtAntall)},
                modifier = Modifier
            )
        }
    }
}
