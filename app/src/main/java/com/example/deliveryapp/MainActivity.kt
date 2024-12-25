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
import sampleReviews
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DeliveryappTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "reviews") {
                    composable("restaurant_list") { RestaurantList(sampleRestaurants, navController) }
                    composable("acceuil") { Acceuil( sampleRestaurants,sampleRestaurants,sampleRestaurants ,navController) }
                       val restaurant1 = Restaurant(
                           restaurantId = "01",
                           name = "La cité Asiatique",
                           location = "Hydra, Alger",
                           avgRating = 4.5,
                           img = R.drawable.asi,
                           logo = R.drawable.logo2,
                           menu = listOf(
                               MenuItem(
                                   restaurantId = "01",
                                   name = "Pizza Margherita",
                                   imageUrl = R.drawable.pizza2,
                                   price = 500f,
                                   available = true,
                                   description = "Delicious pasta"
                               ),
                               MenuItem(
                                   restaurantId = "01",
                                   name = "Pasta",
                                   imageUrl = R.drawable.img1,
                                   price = 900f,
                                   available = true,
                                   description = "Delicious pizza "
                               )
                           ),
                           contactInfo = ContactInfo(
                               phone = "05 40 95 35",
                               email = "contact@otacos.com",
                               socialMedia = listOf(
                                   SocialMedia(type = "facebook", url = "https://facebook.com/otacos"),
                                   SocialMedia(type = "instagram", url = "https://instagram.com/otacos")
                               )
                           ),
                           createdAt = Date(),
                           cuisineType = "Asian",

                           updatedAt = Date()
                       )
                    composable("add_review_delivery") { RatingDeliveryScreen(restaurant1)}
                    composable("add_review_restaurant") { RatingRestaurantScreen(restaurant1)}
                    composable("reviews") { ReviewsScreen(sampleReviews)}
                    composable("rest") { RestaurantScreen() }

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
