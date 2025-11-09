package com.example.bursdagsassistent_s356228

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import android.Manifest
import com.example.bursdagsassistent_s356228.ui.BursdagsassistenApp

class MainActivity : ComponentActivity() {
    private val requestSmsPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        isGranted: Boolean ->
        if(isGranted){
            Log.d("MainActivity", "Tillatelse til å sende SMS")
        }else{
            Log.d("MainActivity", "Ikke tillatelse til å sende SMS")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED ){
            Log.d("MainActivity", "Tillatelse til å sende SMS er allerede gitt")
        }else{
            requestSmsPermission.launch(Manifest.permission.SEND_SMS)
        }
        enableEdgeToEdge()
        setContent {
            BursdagsassistenApp()
        }
    }
}
