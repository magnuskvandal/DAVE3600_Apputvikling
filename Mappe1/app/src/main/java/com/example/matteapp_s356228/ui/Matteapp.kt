package com.example.matteapp_s356228.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.matteapp_s356228.ui.navigasjon.Navigering
import com.example.matteapp_s356228.ui.theme.Matteapp_s356228Theme

@Composable
fun Matteapp(){
    Matteapp_s356228Theme(dynamicColor = false) {
        val navController = rememberNavController()
        Navigering(navController = navController)
    }
}