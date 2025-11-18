package com.example.mittKart_s356228.ui.state

import com.example.mittKart_s356228.data.Place
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

data class MapUiState(
    val places: List<Place> = emptyList(),
    val showAddPlaceSheet: Boolean = false,
    val newPlaceName: String = "",
    val newPlaceDescription: String = "",
    val newPlaceAddress: String = "",
    val newPlaceLatLng: LatLng? = null,
    val hasAttemptedSave: Boolean = false,
    val selectedPlace: Place? = null,
    val showDetailsSheet: Boolean = false,
    val showDeleteConfirmation: Boolean = false,
    val searchQuery: String = "",
    val cameraUpdate: CameraPosition? = null
)
