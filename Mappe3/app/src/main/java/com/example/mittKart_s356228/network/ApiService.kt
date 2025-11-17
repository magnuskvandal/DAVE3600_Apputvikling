package com.example.mittKart_s356228.network

import com.example.mittKart_s356228.data.Place
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

private val BASE_URL = "https://dave3600.cs.oslomet.no/~s356228/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService{
    @GET("jsonout.php")
    suspend fun getPlaces(): List<Place>

    @FormUrlEncoded
    @POST("jsonin.php")
    suspend fun addPlace(
        @Field("name") name: String,
        @Field("description") description: String,
        @Field("address") address: String?,
        @Field("latitude") latitude: Double,
        @Field("longitude") longitude: Double
    ): Response<Unit>

    @FormUrlEncoded
    @POST("delete.php")
    suspend fun deletePlace(
        @Field("id") id: Int
    ): Response<Unit>
}

object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}