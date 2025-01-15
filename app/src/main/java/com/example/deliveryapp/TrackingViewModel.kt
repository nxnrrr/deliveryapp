package com.example.deliveryapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TrackingViewModel : ViewModel() {
    private val _currentStep = MutableStateFlow(0)
    val currentStep = _currentStep.asStateFlow()

    fun updateTrackingStatus(orderId: String) {
        viewModelScope.launch {
            try {
                // Make your API call here
                // val status = trackingRepository.getOrderStatus(orderId)
                // _currentStep.value = status.currentStep
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}