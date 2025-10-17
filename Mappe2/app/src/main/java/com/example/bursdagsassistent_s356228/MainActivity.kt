package com.example.bursdagsassistent_s356228

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.bursdagsassistent_s356228.ui.navigation.AppNavigation
import com.example.bursdagsassistent_s356228.ui.theme.Bursdagsassistent_s356228Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Bursdagsassistent_s356228Theme {
                val navController = rememberNavController()
                AppNavigation(
                    navController = navController
                )
            }
        }
    }
}
