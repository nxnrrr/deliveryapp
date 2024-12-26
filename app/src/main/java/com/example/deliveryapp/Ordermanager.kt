package com.example.deliveryapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import getRestaurantByID
import sampleRestaurants
import java.util.Date

object OrderManager {
    // Use a mutable state to track the current order
    private var _currentOrder = mutableStateOf<Order?>(null)
    val currentOrder: State<Order?> get() = _currentOrder

    // Function to get or create the current order
    internal fun getCurrentOrder(): Order {
        if (_currentOrder.value == null) {
            _currentOrder.value = Order(
                orderId = "01",
                userId = "123",
                restaurantId = "01",
                items = mutableListOf(),
                totalAmount = 0.0F,
                status = "Pending",
                deliveryAddress = "",
                deliveryNotes = "",
                deliveryLocation = null,
                history = emptyList(),
                createdAt = Date(),
                updatedAt = Date()
            )
        }
        return _currentOrder.value!!
    }

    // Update item quantity and recalculate the total amount
    fun updateItemQuantity(itemId: String, delta: Int) {
        val order = getCurrentOrder()
        val item = order.items.find { it.itemId == itemId }
        item?.let {
            it.quantity += delta
            if (it.quantity <= 0) {
                order.items.remove(it)
            }
        }
        updateTotalAmount()
    }
    fun addItemToOrder(item: OrderItem) {
        val order = getCurrentOrder()

        // Check if the item already exists in the order
        val existingItem = order.items.find { it.itemId == item.itemId }
        if (existingItem != null) {
            existingItem.quantity += item.quantity
        } else {
            order.items.add(item)
        }

        // Recalculate the total amount, ensuring the result is a Float
        order.totalAmount = (order.items.sumOf {
            (it.quantity * getItemPrice(it.itemId, order.restaurantId)).toDouble()
        }).toFloat()  // Cast the result to Float
        order.updatedAt = Date()
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
        val menuItem = restaurant?.menu?.find { it.itemId == itemId }
        return menuItem?.price ?: 0.0F
    }
}

