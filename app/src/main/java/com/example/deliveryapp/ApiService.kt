package com.example.deliveryapp

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("restaurants")  // Assurez-vous que l'URL correspond à votre backend
    suspend fun getRestaurants(): List<Restaurant>
}