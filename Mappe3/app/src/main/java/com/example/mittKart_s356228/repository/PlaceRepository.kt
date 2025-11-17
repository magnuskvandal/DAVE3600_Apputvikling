package com.example.mittKart_s356228.repository

import android.util.Log
import com.example.mittKart_s356228.data.Place
import com.example.mittKart_s356228.network.Api

class PlaceRepository() {
    suspend fun getPlaces(): List<Place> {
        return try {
            Api.retrofitService.getPlaces()
        } catch (e: Exception) {
            Log.e("PlaceRepository", "Error fetching places: ${e.message}")
            emptyList()
        }
    }

    suspend fun addPlace(place: Place): Boolean{
        return try{
            val response = Api.retrofitService.addPlace(place.name, place.description, place.address, place.latitude, place.longitude)
            if(response.isSuccessful){
                Log.d("PlaceRepository", "Place added successfully")
            }else{
                Log.e("PlaceRepository", "Failed to add place. Server response: ${response.code()}")
            }
            response.isSuccessful
        }catch(e: Exception){
            Log.e("PlaceRepository", "Error adding place: ${e.message}")
            false
        }
    }

    suspend fun deletePlace(id: Int): Boolean{
        return try{
            val response = Api.retrofitService.deletePlace(id)
            if(response.isSuccessful){
                Log.d("PlaceRepository", "Place with id $id deleted successfully")
            }else{
                Log.e("PlaceRepository", "Failed to delete place. Server response: ${response.code()}")
            }
            response.isSuccessful
        }catch(e: Exception){
            Log.e("PlaceRepository", "Error deleting place: ${e.message}")
            false
        }
    }
}