package com.example.mittKart_s356228.ui.viewmodels

import android.content.Context
import android.location.Geocoder
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mittKart_s356228.data.Place
import com.example.mittKart_s356228.repository.PlaceRepository
import com.example.mittKart_s356228.ui.state.MapUiState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale

class MapViewModel(private val placeRepository: PlaceRepository): ViewModel() {
    private val _uiState: MutableStateFlow<MapUiState> = MutableStateFlow(MapUiState())
    val uiState: StateFlow<MapUiState> = _uiState.asStateFlow()

    init{
        getPlaces()
    }

    private fun getPlaces(){
        viewModelScope.launch {
            val places = placeRepository.getPlaces()
            _uiState.update{
                currentState -> currentState.copy(
                    places = places
                )
            }
        }
    }

    fun onNameChange(name: String){
        _uiState.update {
            currentState -> currentState.copy(
                newPlaceName = name
            )
        }
    }

    fun onDescriptionChange(description: String){
        _uiState.update {
            currentState -> currentState.copy(
                newPlaceDescription = description
            )
        }
    }

    fun onPlaceSelected(place: Place){
        _uiState.update {
            currentState -> currentState.copy(
                selectedPlace = place,
                showDetailsSheet = true
            )
        }
    }

    fun onPlaceDetailsSheetDismissed(){
        _uiState.update {
            currentState -> currentState.copy(
                selectedPlace = null,
                showDetailsSheet = false,
                showDeleteConfirmation = false
            )
        }
    }

    fun addPlace(){
        _uiState.update{
            currentState -> currentState.copy(
                hasAttemptedSave = true
            )
        }

        val name = _uiState.value.newPlaceName
        val description = _uiState.value.newPlaceDescription

        if(name.isBlank() || description.isBlank()){
            return
        }

        viewModelScope.launch {
            _uiState.value.newPlaceLatLng?.let{ latLng ->
                val newPlace = Place(
                    name = _uiState.value.newPlaceName,
                    description = _uiState.value.newPlaceDescription,
                    address = if (_uiState.value.newPlaceAddress.isBlank()) null else _uiState.value.newPlaceAddress,
                    latitude = latLng.latitude,
                    longitude = latLng.longitude
                )

                val success = placeRepository.addPlace(newPlace)

                if(success){
                    getPlaces()
                }
            }
            onAddPlaceSheetDismissed()
        }
    }

    fun deleteSelectedPlace(){
        viewModelScope.launch{
            _uiState.value.selectedPlace?.let{ selectedPlace ->
                val success = placeRepository.deletePlace(selectedPlace.id)

                if(success){
                    getPlaces()
                }
            }
            onPlaceDetailsSheetDismissed()
        }
    }

    fun showDeleteDialog(){
        _uiState.update {
            currentState -> currentState.copy(
            showDeleteConfirmation = true
            )
        }
    }

    fun cancelDelete(){
        _uiState.update{
            currentState -> currentState.copy(
                showDeleteConfirmation = false
            )
        }
    }

    fun prepareNewPlace(latLng: LatLng, context: Context) {
        viewModelScope.launch {
            var addressText: String = ""
            try{
                val geocoder = Geocoder(context, Locale.getDefault())
                val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
                if(addresses != null && addresses.isNotEmpty()){
                    addressText = addresses[0].getAddressLine(0)
                }
            }catch(e: Exception){
                Log.e("MapViewModel", "Error getting address", e)
            }

            _uiState.update {
                currentState -> currentState.copy(
                    showAddPlaceSheet = true,
                    newPlaceLatLng = latLng,
                    newPlaceAddress = addressText
                )
            }
        }
    }

    fun onAddPlaceSheetDismissed() {
        _uiState.update {
            currentState -> currentState.copy(
                showAddPlaceSheet = false,
                newPlaceLatLng = null,
                newPlaceName = "",
                newPlaceDescription = "",
                newPlaceAddress = "",
                hasAttemptedSave = false
            )
        }
    }


    fun onSearchQueryChange(query: String){
        _uiState.update{
            currentState -> currentState.copy(
                searchQuery = query
            )
        }
    }

    fun searchLocation(context: Context) {
        if (_uiState.value.searchQuery.isBlank()) {
            return
        }

        viewModelScope.launch {
            try{
                val geocoder = Geocoder(context, Locale.getDefault())
                val addresses = geocoder.getFromLocationName(_uiState.value.searchQuery, 1)
                if(addresses != null && addresses.isNotEmpty()){
                    val location = addresses[0]
                    val latLng = LatLng(location.latitude, location.longitude)

                    _uiState.update{
                        currentState -> currentState.copy(
                            cameraUpdate = CameraPosition.fromLatLngZoom(latLng, 10f),
                            searchQuery = ""
                        )
                    }
                }else(
                    Log.d("MapViewModel", "Could not find location for query: ${_uiState.value.searchQuery}")
                )
            }catch(e: Exception){
                Log.e("MapViewModel", "Error searching for location", e)
            }
        }
    }

    fun resetCameraUpdate() {
        _uiState.update { it.copy(cameraUpdate = null) }
    }
}


