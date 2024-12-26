package com.example.deliveryapp

import android.adservices.adid.AdId
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.deliveryapp.MenuCard
import getRestaurantByID
import sampleRestaurants

@Composable
fun MenuListScreen(navController: NavHostController, restaurantId: String) {
    val restaurants = sampleRestaurants
    val restaurant = getRestaurantByID(restaurantId, restaurants)
    val menuItems = restaurant?.menu ?: emptyList()

    LazyColumn {
        items(menuItems) { menuItem ->
            MenuCard(menuItem) {
                navController.navigate("menu_detail/${restaurant?.restaurantId}/${menuItem.name}")
            }
        }
    }
}
