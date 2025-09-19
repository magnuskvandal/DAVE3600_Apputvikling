package com.example.matteapp_s356228.ui.sider

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.matteapp_s356228.ui.komponenter.TopBar
import com.example.matteapp_s356228.ui.theme.Matteapp_s356228Theme
import com.example.matteapp_s356228.R
import com.example.matteapp_s356228.ui.komponenter.Spillpanel

@Composable
fun Spillside(
    modifier: Modifier = Modifier
){
    Scaffold(
        modifier = modifier,
        topBar = { TopBar(tittel = stringResource(R.string.tittel), onNavigateBack = { /*TODO*/ }) }
    ){ innerPadding ->
        Spillpanel(modifier = Modifier.padding(innerPadding))
    }
}



@Preview(showBackground = true)
@Composable
fun SpillsidePreview() {
    Matteapp_s356228Theme(dynamicColor = false) {
        Spillside()
    }
}