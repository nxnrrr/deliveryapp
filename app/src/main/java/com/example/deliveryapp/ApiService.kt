package com.example.deliveryapp

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("restaurants/restaurants")  // Assurez-vous que l'URL correspond à votre backend
    suspend fun getRestaurants(): Response<List<Restaurant>>

    @GET("restaurants/search")
    suspend fun searchRestaurants(@Query("q") query: String): Response<List<Restaurant>>

    @GET("restaurants/menu/{restaurantId}")

    suspend fun getMenuByRestaurantId(@Path("restaurantId") restaurantId: String): Response<List<MenuItem>>
    @GET("restaurants/restaurants/{id}")
    suspend fun getRestaurantById(@Path("id") restaurantId: String): Response<Restaurant>
    @GET("reviews/resto/{restaurantId}")
    suspend fun getReview(@Path("restaurantId") restaurantId: String): Response<ReviewsResponse>
    @GET("restaurants/menuid/{menuId}")
    suspend fun getMenubyId(@Path("menuId") menuId: String): Response<MenuItem>
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<AuthResponse>

    @POST("auth/google")
    suspend fun loginWithGoogle(@Body googleRequest: GoogleRequest): Response<AuthResponse>

    @POST("auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<AuthResponse>

    @POST("orders/orders")
    suspend fun placeOrder(@Body orderRequest: OrderRequest): Response<OrderResponse>

    @GET("orders/orders/{orderId}")
    suspend fun getOrderById(@Path("orderId") orderId: String): Response<OrderResponse>

//
//    @GET("auth")
//    suspend fun checkAuth(): Response<AuthResponse>
//
//    @GET("auth/verify/{token}")
//    suspend fun verifyAccount(@Path("token") token: String): Response<AuthResponse>
//
//    @POST("auth/password/reset")
//    suspend fun requestPasswordReset(@Body resetRequest: PasswordResetRequest): Response<AuthResponse>
//
//    @POST("auth/password/reset/{token}")
//    suspend fun resetPassword(@Path("token") token: String, @Body newPassword: PasswordResetConfirmRequest): Response<AuthResponse>

}