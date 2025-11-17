package com.example.mittKart_s356228.data

data class Place(
    val id: Int = 0,
    val name: String,
    val description: String,
    val address: String?,
    val latitude: Double,
    val longitude: Double
)
