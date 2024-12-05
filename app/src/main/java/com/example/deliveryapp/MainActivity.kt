package com.example.deliveryapp

import RestaurantList
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.deliveryapp.ui.theme.DeliveryappTheme
import com.example.projettdm.MenuDetailScreen
import com.example.projettdm.MenuListScreen
import sampleRestaurants

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DeliveryappTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "acceuil") {
                    composable("restaurant_list") { RestaurantList(sampleRestaurants, navController) }
                    composable("acceuil") { Acceuil( sampleRestaurants,sampleRestaurants,sampleRestaurants ,navController) }

                    composable("menu_list/{restaurantId}") { backStackEntry ->
                        val restaurantId = backStackEntry.arguments?.getString("restaurantId") ?: ""
                        MenuListScreen(navController, restaurantId)
                    }
                    composable("menu_detail/{restaurantId}/{menuName}") { backStackEntry ->
                        val restaurantId = backStackEntry.arguments?.getString("restaurantId") ?: ""
                        val menuName = backStackEntry.arguments?.getString("menuName") ?: ""
                        MenuDetailScreen(navController, restaurantId, menuName)
                    }
                }
            }
        }
    }
}
