package com.example.deliveryapp

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("restaurants")  // Assurez-vous que l'URL correspond à votre backend
    suspend fun getRestaurants(): Response<List<Restaurant>>
    @GET("search")
    suspend fun searchRestaurants(@Query("q") query: String): Response<List<Restaurant>>
    @GET("menu/{restaurantId}")
    suspend fun getMenuByRestaurantId(@Path("restaurantId") restaurantId: String): Response<List<MenuItem>>
    @GET("restaurants/{id}")
    suspend fun getRestaurantById(@Path("id") restaurantId: String): Response<Restaurant>
    @GET("review/resto/{restaurantId}")
    suspend fun getReview(@Path("restaurantId") restaurantId: String): Response<ReviewsResponse>
    @GET("menuid/{menuId}")
    suspend fun getMenubyId(@Path("menuId") menuId: String): Response<MenuItem>


}