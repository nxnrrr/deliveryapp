package com.example.deliveryapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import getRestaurantByID
//import sampleRestaurants
import java.util.Date
/*
object OrderManager {
    private var _currentOrder = mutableStateOf<Order?>(null)
    val currentOrder: State<Order?> get() = _currentOrder

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

    fun addItemToOrder(item: OrderItem, Note: String?) {
        // Get the current order
        val order = getCurrentOrder()

        // Check if the item already exists in the order
        val existingItem = order.items.find { it.itemId == item.itemId }
        if (existingItem != null) {
            // Update the quantity of the existing item
            existingItem.quantity += item.quantity
        } else {
            // Add the new item to the order
            order.items.add(item)
        }

        // Recalculate the total amount and cast it to Float
        order.totalAmount = (order.items.sumOf {
            (it.quantity * getItemPrice(it.itemId, order.restaurantId)).toDouble()
        }).toFloat()

        // Update the order's last update date
        order.updatedAt = Date()

        // Append the new note to the delivery notes, ensuring it's not null
        if (Note != null) {
            order.deliveryNotes = order.deliveryNotes + " " + Note
        }

        // Debug: Log the updated order state
        println("Updated Order: $order")

        // Trigger state change to notify UI
        _currentOrder.value = order
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
    internal fun getItemName(itemId: String, restaurantId: String): String {
        val restaurants = sampleRestaurants
        val restaurant = getRestaurantByID(restaurantId, restaurants)
        val menuItem = restaurant?.menu?.find { it.itemId == itemId }
        return menuItem?.name ?: ""
    }

}
*/

