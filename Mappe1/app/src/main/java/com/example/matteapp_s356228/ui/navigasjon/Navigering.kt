package com.example.matteapp_s356228.ui.navigasjon

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.matteapp_s356228.ui.sider.OmSpilletSide
import com.example.matteapp_s356228.ui.sider.Preferanserside
import com.example.matteapp_s356228.ui.sider.Spillside
import com.example.matteapp_s356228.ui.sider.Startside

@Composable
fun Navigering(
    navController: NavHostController
){
    val navigerTilbake: () -> Unit = { navController.navigateUp() }
    NavHost(
        navController = navController,
        startDestination = "startside"
    ){
        composable(route = Ruter.Startside.name){
            Startside(
                onNavigerTil = { rute -> navController.navigate(route = rute) }
            )
        }
        composable(route = Ruter.Spillside.name) {
            Spillside(
                onNavigerTilbake = navigerTilbake
            )
        }
        composable(route = Ruter.OmSpillet.name){
            OmSpilletSide(
                onNavigerTilbake = navigerTilbake
            )
        }
        composable(route = Ruter.Preferanser.name){
            Preferanserside(
                onNavigerTilbake = navigerTilbake
            )
        }
    }
}


enum class Ruter{
    Startside,
    Spillside,
    OmSpillet,
    Preferanser
}