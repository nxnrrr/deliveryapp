package com.example.deliveryapp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Response

class RestaurantModel: ViewModel() {

    val restaurants = mutableStateOf<List<Restaurant>>(emptyList())
    val cc = mutableStateOf<List<Restaurant>>(emptyList())
    val isLoading =  mutableStateOf(false)
    val resto = mutableStateOf<Restaurant?>(null)
    val reviews = mutableStateOf<List<Review>>(emptyList())

    fun getRestaurants(){
        isLoading.value = true // Set loading to true when starting
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.getRestaurants()
                if (response.isSuccessful) {
                    restaurants.value = response.body()!!
                }
                else{

                }
            } catch (e: Exception) {
                // Handle error
            } finally {
                isLoading.value = false // Set loading to false when done
            }
        }
    }

    // Dans votre ViewModel
    var errorMessage by mutableStateOf("")
        private set

    fun getSearch(searchText: String) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                val response = RetrofitClient.api.searchRestaurants(searchText)

                if (response.isSuccessful) {
                    cc.value = response.body()!!
                } else {
                    // Affichez le code de statut HTTP de la réponse
                    Log.e("RestaurantSearch", "Erreur API : Code réponse ${response.code()} - ${response.message()}")
                    errorMessage = "Erreur lors de la récupération des restaurants."
                }

            } catch (e: Exception) {
                // Log l'exception dans Logcat
                Log.e("RestaurantSearch", "Erreur lors de la recherche: ${e.localizedMessage}")
                errorMessage = "Une erreur est survenue : ${e.localizedMessage}"
            } finally {
                isLoading.value = false
            }
        }
    }
    fun getRestaurantById(restaurantId: String) {
        viewModelScope.launch {
            try {
                isLoading.value = true // Indique que le chargement est en cours

                // Effectuer l'appel API pour récupérer le restaurant
                val response = RetrofitClient.api.getRestaurantById(restaurantId)

                if (response.isSuccessful && response.body() != null) {
                    // Si la réponse est réussie, stocker le restaurant dans la variable
                    resto.value = response.body()
                    Log.d("RestaurantSearch", "Restaurant récupéré avec succès: ${resto}")
                } else {
                    // Si la réponse n'est pas réussie, enregistrer l'erreur
                    Log.e("RestaurantSearch", "Erreur API : Code réponse non réussi, code: ${response.code()}")
                    errorMessage = "Erreur lors de la récupération du restaurant."
                }
            } catch (e: Exception) {
                // Gestion des exceptions
                Log.e("RestaurantSearch", "Erreur lors de la recherche: ${e.localizedMessage}")
                errorMessage = "Une erreur est survenue : ${e.localizedMessage}"
            } finally {
                isLoading.value = false // Fin du chargement
            }
        }
    }

    fun getReviewByResto(restaurantId: String) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                Log.d("RestaurantSearch", "Recherche des avis pour le restaurant $restaurantId")

                val response = RetrofitClient.api.getReview(restaurantId)

                if (response.isSuccessful && response.body() != null) {
                    val reviewsResponse = response.body()!!
                    reviews.value = reviewsResponse.reviews
                    Log.d("cccRestaurantSearch", "Avis récupérés avec succès : ${reviews.value}")
                } else {
                    Log.e("cccRestaurantSearch", "Erreur API : Code ${response.code()}, Corps ${response.errorBody()?.string()}")
                    errorMessage = "Erreur lors de la récupération des avis."
                }
            } catch (e: Exception) {
                Log.e("cccRestaurantSearch", "Erreur lors de la recherche: ${e.message}", e)
                errorMessage = "Une erreur est survenue : ${e.message}"
            } finally {
                isLoading.value = false
                Log.d("cccRestaurantSearch", "Chargement terminé")
            }
        }
    }
}



