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

class MenuModel: ViewModel() {

    val menu = mutableStateOf<List<MenuItem>>(emptyList())
    var menu1: MenuItem? = null

    val isLoading = mutableStateOf(false)

    fun getMenu(restoID: String){
        isLoading.value = true
        viewModelScope.launch {
            val response = RetrofitClient.api.getMenuByRestaurantId(restoID)
            if (response.isSuccessful) {
                menu.value = response.body()!!
            }
            else{

            }
            isLoading.value = false
        }
    }

    fun getMenubyId(menuID: String) {
        viewModelScope.launch {
            try {
                // Effectuer l'appel API pour récupérer le restaurant
                val response = RetrofitClient.api.getMenubyId(menuID)

                if (response.isSuccessful && response.body() != null) {
                    // Si la réponse est réussie, stocker le restaurant dans la variable
                    menu1 = response.body()
                    Log.d("RestaurantSearch", "Restaurant récupéré avec succès: ${menu1}")
                } else {
                    // Si la réponse n'est pas réussie, enregistrer l'erreur
                    Log.e(
                        "RestaurantSearch",
                        "Erreur API : Code réponse non réussi, code: ${response.code()}"
                    )
                }
            } catch (e: Exception) {
                // Gestion des exceptions
                Log.e("RestaurantSearch", "Erreur lors de la recherche: ${e.localizedMessage}")
            } finally {
            }
        }
    }
}







