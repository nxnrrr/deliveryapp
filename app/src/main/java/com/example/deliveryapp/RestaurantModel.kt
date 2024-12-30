package com.example.deliveryapp

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RestaurantModel: ViewModel() {

    val restaurants = mutableStateOf<List<Restaurant>>(emptyList())
    val isLoading =  mutableStateOf(true)


    fun getRestaurants(){

        viewModelScope.launch {
            val response = RetrofitClient.api.getRestaurants()
            if (response.isSuccessful) {
                restaurants.value = response.body()!!
            }
            else{

            }

        }




    }



}