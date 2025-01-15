package com.example.deliveryapp

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import getRestaurantByID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date

object OrderManager {
    private val gson = Gson()
    private var _currentOrder = mutableStateOf<Order?>(null)
    val currentOrder: State<Order?> get() = _currentOrder

    private val _cartItems = MutableStateFlow<List<OrderItem>>(emptyList())
    val cartItems: StateFlow<List<OrderItem>> = _cartItems.asStateFlow()

    internal fun getCurrentOrder(): Order {
        if (_currentOrder.value == null) {
            _currentOrder.value = Order(
                _id = null,
                orderId = "01",
                userId = "123",
                restaurantId = "01",
                items = mutableListOf(),
                totalAmount = 0.0F,
                status = "Pending",
                deliveryAddress = "",
                deliveryNotes = "",
                history = emptyList(),
                createdAt = Date(),
                updatedAt = Date()
            )
        }
        return _currentOrder.value!!
    }

    fun addItemToOrder(item: OrderItem, context: Context?, context1: Context) {
        val sharedPrefs = context?.getSharedPreferences("cart_prefs", Context.MODE_PRIVATE)
        
        // Get existing cart items
        val cartJson = sharedPrefs?.getString("cart_items", "[]")
        val type = object : TypeToken<MutableList<OrderItem>>() {}.type
        val cartItems = gson.fromJson<MutableList<OrderItem>>(cartJson, type)

        // Check if item already exists
        val existingItem = cartItems.find { it.itemId == item.itemId }
        if (existingItem != null) {
            existingItem.quantity += item.quantity
        } else {
            cartItems.add(item)
        }

        // Save updated cart
        val editor = sharedPrefs?.edit()
        if (editor != null) {
            editor.putString("cart_items", gson.toJson(cartItems))
            editor.apply()
        }

        // Show success message
        Toast.makeText(context, "Ajouté au panier avec succès", Toast.LENGTH_SHORT).show()
    }

    fun getCartItems(context: Context): List<OrderItem> {
        val sharedPrefs = context.getSharedPreferences("cart_prefs", Context.MODE_PRIVATE)
        val cartJson = sharedPrefs.getString("cart_items", "[]")
        val type = object : TypeToken<List<OrderItem>>() {}.type
        return gson.fromJson(cartJson, type)
    }

    fun updateItemQuantity(itemId: String, delta: Int, context: Context) {
        val sharedPrefs = context.getSharedPreferences("cart_prefs", Context.MODE_PRIVATE)
        val cartJson = sharedPrefs.getString("cart_items", "[]")
        val type = object : TypeToken<MutableList<OrderItem>>() {}.type
        val cartItems = gson.fromJson<MutableList<OrderItem>>(cartJson, type)

        val item = cartItems.find { it.itemId == itemId }
        item?.let {
            it.quantity += delta
            if (it.quantity <= 0) {
                cartItems.remove(it)
            }
        }

        val editor = sharedPrefs.edit()
        editor.putString("cart_items", gson.toJson(cartItems))
        editor.apply()
    }

    fun clearCart(context: Context) {
        val sharedPrefs = context.getSharedPreferences("cart_prefs", Context.MODE_PRIVATE)
        sharedPrefs.edit().remove("cart_items").apply()
    }

    fun getTotalAmount(context: Context): Float {
        val cartItems = getCartItems(context)
        return cartItems.sumOf { 
            (it.quantity * getItemPrice(it.itemId, it.restaurantId)).toDouble()
        }.toFloat()
    }

    // Recalculate total amount based on the items in the order
    private fun updateTotalAmount() {
        val order = getCurrentOrder()
        order.totalAmount = order.items.sumOf {
            (it.quantity * getItemPrice(it.itemId, order.restaurantId)).toDouble()
        }.toFloat()
        order.updatedAt = Date()

        // Trigger state change to notify UI
        _currentOrder.value = order
    }

    // Fetch price of a menu item
    internal fun getItemPrice(itemId: String, restaurantId: String): Float {
        val restaurants = sampleRestaurants
        val restaurant = getRestaurantByID(restaurantId, restaurants)
        val menuItem = restaurant?.menu?.find { it._id == itemId }
        return menuItem?.price ?: 0.0F
    }
    internal fun getItemName(itemId: String, restaurantId: String): String {
        val restaurants = sampleRestaurants
        val restaurant = getRestaurantByID(restaurantId, restaurants)
        val menuItem = restaurant?.menu?.find { it._id == itemId }
        return menuItem?.name ?: ""
    }

}

