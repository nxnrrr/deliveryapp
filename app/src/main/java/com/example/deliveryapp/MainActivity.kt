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


                // État pour stocker la liste des restaurants récupérés
                val restaurants = remember { mutableStateOf<List<Restaurant>>(emptyList()) }
                val isLoading = remember { mutableStateOf(true) } // Pour indiquer que les données sont en cours de chargement
                val errorMessage = remember { mutableStateOf<String?>(null) } // Pour afficher les messages d'erreur


                // Lancer l'appel réseau pour récupérer les restaurants
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


                // NavHost pour gérer la navigation
                NavHost(navController = navController, startDestination = "restaurant_list") {
                    composable("restaurant_list") {
                        // Passez l'état de chargement et d'erreur au composant RestaurantList
                        RestaurantList(restaurants.value, navController, isLoading.value, errorMessage.value)
                    }




                }


            }
        }
    }
}
