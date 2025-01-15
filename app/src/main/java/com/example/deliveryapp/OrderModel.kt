package com.example.deliveryapp.com.example.deliveryapp
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.Order
import com.example.deliveryapp.OrderRequest
import com.example.deliveryapp.OrderResponse
import com.example.deliveryapp.RetrofitClient
import kotlinx.coroutines.launch

class OrderModel: ViewModel()  {
    val isLoading = mutableStateOf(false)
    val isOrderPlaced = mutableStateOf(false)
    val orderErrorMessage = mutableStateOf("")
    val currentOrder = mutableStateOf<Order?>(null)

    fun placeOrder(orderRequest: OrderRequest, onPlaceOrderSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                orderErrorMessage.value = ""

                val response = RetrofitClient.api.placeOrder(orderRequest)

                if (response.isSuccessful) {
                    val orderResponse = response.body()
                    if (orderResponse != null) {
                        isOrderPlaced.value = true
                        currentOrder.value = orderResponse.data
                        Log.d("OrderModel", "Order placed successfully: ${orderResponse.data}")
                        onPlaceOrderSuccess()
                    } else {
                        Log.e("OrderModel", "Order failed: Empty response body")
                        orderErrorMessage.value = "Order failed: Empty response body"
                    }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    Log.e("OrderModel", "Order failed: $errorBody")
                    orderErrorMessage.value = "Order failed: $errorBody"
                    onPlaceOrderSuccess()
                }
            } catch (e: Exception) {
                Log.e("OrderModel", "Order error: ${e.localizedMessage}", e)
                orderErrorMessage.value = "An error occurred during order placement: ${e.localizedMessage}"
            } finally {
                isLoading.value = false
            }
        }
    }

//    get updated order from database

    fun getOrderById(orderId: String) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                orderErrorMessage.value = ""

                val response = RetrofitClient.api.getOrderById(orderId)

                if (response.isSuccessful) {
                    val orderResponse = response.body()
                    if (orderResponse != null) {
                        currentOrder.value = orderResponse.data
                        Log.d("OrderModel", "Order retrieved successfully: ${orderResponse.data}")
                    } else {
                        Log.e("OrderModel", "Order retrieval failed: Empty response body")
                        orderErrorMessage.value = "Order retrieval failed: Empty response body"
                    }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    Log.e("OrderModel", "Order retrieval failed: $errorBody")
                    orderErrorMessage.value = "Order retrieval failed: $errorBody"
                }
            } catch (e: Exception) {
                Log.e("OrderModel", "Order retrieval error: ${e.localizedMessage}", e)
                orderErrorMessage.value = "An error occurred during order retrieval: ${e.localizedMessage}"
            } finally {
                isLoading.value = false
            }
        }
    }

}