package com.example.deliveryapp


import RestaurantList
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.deliveryapp.ui.theme.DeliveryappTheme
import androidx.compose.runtime.mutableStateOf




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DeliveryappTheme {
                val navController = rememberNavController()



                val restaurants = remember { mutableStateOf<List<Restaurant>>(emptyList()) }
                val isLoading = remember { mutableStateOf(true) } // Pour indiquer que les données sont en cours de chargement
                val errorMessage = remember { mutableStateOf<String?>(null) } // Pour afficher les messages d'erreur


                LaunchedEffect(Unit) {
                    try {
                        isLoading.value = true
                        val response = RetrofitClient.api.getRestaurants()
                        restaurants.value = response
                        Log.d("MainActivity", "Données récupérées: $response")
                    } catch (e: Exception) {
                        errorMessage.value = "Erreur de connexion: ${e.message}"
                        Log.e("MainActivity", "Erreur de connexion", e)
                    } finally {
                        isLoading.value = false
                    }
                }



                NavHost(navController = navController, startDestination = "accueil") {
                    composable("accueil") {
                        val italiens = restaurants.value.filter { "Italian" in  it.cuisineType }
                        val traditionnal = restaurants.value.filter {"Traditionnal" in  it.cuisineType }
                        val fastFood = restaurants.value.filter {"Fast" in  it.cuisineType  }
                        val asian = restaurants.value.filter { "Asian" in  it.cuisineType }
                        val mexican = restaurants.value.filter {"Mexican" in  it.cuisineType }
                        val turkish = restaurants.value.filter {"Turkish" in  it.cuisineType  }
                        val indian = restaurants.value.filter {"Indian" in  it.cuisineType  }

                        Acceuil(italiens,traditionnal,fastFood,asian,mexican,turkish,indian, navController)
                    }
                    composable("restaurant_list") {

                        RestaurantList(restaurants.value, navController, isLoading.value, errorMessage.value)
                    }


                }


            }
        }
    }
}
