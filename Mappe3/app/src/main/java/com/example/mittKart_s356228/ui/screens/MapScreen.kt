package com.example.mittKart_s356228.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mittKart_s356228.R
import com.example.mittKart_s356228.ui.components.AddPlaceSheet
import com.example.mittKart_s356228.ui.components.DeleteConfirmationDialog
import com.example.mittKart_s356228.ui.components.MapSearchBar
import com.example.mittKart_s356228.ui.components.PlaceDetailsSheet
import com.example.mittKart_s356228.ui.viewmodels.AppViewModelProvider
import com.example.mittKart_s356228.ui.viewmodels.MapViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    viewModel: MapViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val startPosition = LatLng(59.911491, 10.757933)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(startPosition, 11f)
    }

    LaunchedEffect(uiState.cameraUpdate) {
        uiState.cameraUpdate?.let{ cameraUpdate ->
            cameraPositionState.animate(CameraUpdateFactory.newCameraPosition(cameraUpdate))
            viewModel.resetCameraUpdate()
        }
    }

    Scaffold(){
        innerPadding ->

        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                contentPadding = innerPadding,
                cameraPositionState = cameraPositionState,
                onMapLongClick = { latLng ->
                    viewModel.prepareNewPlace(latLng = latLng, context = context)
                },
                uiSettings = MapUiSettings(
                    compassEnabled = false,
                    zoomControlsEnabled = false,
                )
            ) {
                uiState.places.forEach { place ->
                    Marker(
                        state = MarkerState(position = LatLng(place.latitude, place.longitude)),
                        title = place.name,
                        onClick = {
                            viewModel.onPlaceSelected(place)
                            true
                        }
                    )
                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(WindowInsets.navigationBars.asPaddingValues())
                    .padding(end = 20.dp, bottom = 30.dp)
            ){

                SmallFloatingActionButton(
                    onClick = {
                        scope.launch{
                            cameraPositionState.animate(CameraUpdateFactory.zoomIn())
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                    contentColor = MaterialTheme.colorScheme.primary

                ){
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.zoom_in_button_description),
                        modifier = Modifier.size(30.dp)
                    )
                }

                SmallFloatingActionButton(
                    onClick = {
                        scope.launch{
                            cameraPositionState.animate(CameraUpdateFactory.zoomOut())
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                    contentColor = MaterialTheme.colorScheme.primary

                ){
                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = stringResource(R.string.zoom_out_button_description),
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            MapSearchBar(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(WindowInsets.statusBars.asPaddingValues())
                    .padding(top = 15.dp)
                    .padding(horizontal = 15.dp),
                query = uiState.searchQuery,
                onQueryChange = { input -> viewModel.onSearchQueryChange(input)},
                onSearch = { viewModel.searchLocation(context)}
            )

            if (uiState.showAddPlaceSheet) {
                val addSheetState = rememberModalBottomSheetState(
                    skipPartiallyExpanded = true
                )

                ModalBottomSheet(
                    onDismissRequest = { viewModel.onAddPlaceSheetDismissed() },
                    containerColor = MaterialTheme.colorScheme.surface,
                    sheetState = addSheetState
                ) {
                    AddPlaceSheet(
                        name = uiState.newPlaceName,
                        description = uiState.newPlaceDescription,
                        address = uiState.newPlaceAddress,
                        onNameChange = { input -> viewModel.onNameChange(input)},
                        onDescriptionChange = { input -> viewModel.onDescriptionChange(input)},
                        onSaveClick = { viewModel.addPlace() },
                        onDismiss = {
                            scope.launch{
                                addSheetState.hide()
                                viewModel.onAddPlaceSheetDismissed()
                            }
                        },
                        hasAttemptedSave = uiState.hasAttemptedSave
                    )
                }
            }

            if(uiState.showDetailsSheet){
                val detailsSheetState = rememberModalBottomSheetState(
                    skipPartiallyExpanded = true
                )
                ModalBottomSheet(
                    onDismissRequest = { viewModel.onPlaceDetailsSheetDismissed() },
                    containerColor = MaterialTheme.colorScheme.surface,
                    sheetState = detailsSheetState
                ){
                    uiState.selectedPlace?.let{ selectedPlace ->
                        PlaceDetailsSheet(
                            place = selectedPlace,
                            onDismiss = {
                                scope.launch{
                                    detailsSheetState.hide()
                                    viewModel.onPlaceDetailsSheetDismissed()
                                }
                            },
                            onDeleteClick = { viewModel.showDeleteDialog()}
                        )
                    }
                }
            }

            if(uiState.showDeleteConfirmation){
                uiState.selectedPlace?.let{ selectedPlace ->
                    DeleteConfirmationDialog(
                        placeName = selectedPlace.name,
                        onDismiss = { viewModel.cancelDelete() },
                        onConfirm = { viewModel.deleteSelectedPlace() }
                    )
                }
            }
        }
    }
}