package com.example.bursdagsassistent_s356228.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.bursdagsassistent_s356228.ui.navigation.AppNavigation
import com.example.bursdagsassistent_s356228.ui.theme.Bursdagsassistent_s356228Theme

@Composable
fun BursdagsassistenApp(){
    Bursdagsassistent_s356228Theme {
        val navController = rememberNavController()
        AppNavigation(navController = navController)
    }
}