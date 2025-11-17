package com.example.mittKart_s356228

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mittKart_s356228.ui.screens.MapScreen
import com.example.mittKart_s356228.ui.theme.MittKart_s356228Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MittKart_s356228Theme {
                MapScreen()
            }
        }
    }
}