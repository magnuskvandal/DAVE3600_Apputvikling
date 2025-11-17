package com.example.mittKart_s356228.ui.viewmodels

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mittKart_s356228.repository.PlaceRepository

object AppViewModelProvider {
    val Factory = viewModelFactory{
        initializer {
            MapViewModel(
                placeRepository = PlaceRepository()
            )
        }
    }
}