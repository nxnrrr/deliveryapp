package com.example.deliveryapp

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://tdm-rest-api-production.up.railway.app/api/"
    lateinit var api: ApiService

    fun initialize(context: Context): ApiService {

            val client = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(context))
                .build()

            api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ApiService::class.java)

        return api
    }

    fun getInstance(): ApiService {
        return api ?: throw IllegalStateException("RetrofitClient must be initialized first")
    }
}