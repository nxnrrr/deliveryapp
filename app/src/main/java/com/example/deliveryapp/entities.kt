package com.example.deliveryapp

import java.util.Date

// Enum for Role
enum class Role {
    USER, ADMIN, MANAGER // Add other roles as needed
}

// Users data class
data class User(
    val userId: String,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val address: List<String>,
    val password: String,
    val profileImage: String,
    val googleId: String?,
    val isVerified: Boolean,
    val verificationToken: String?,
    val verificationTokenExpiry: Date?,
    val resetPasswordToken: String?,
    val resetPasswordTokenExpiry: Date?,
    val createdAt: Date,
    val updatedAt: Date,
    val role: Role
)

// SocialMedia data class
data class SocialMedia(
    val type: String,
    val url: String
)

// Restaurants data class
data class Restaurant(
    val restaurantId: String,
    val name: String,
    val logo: String,
    val location: String,
    val cuisineType: String,
    val avgRating: Double,
    val reviewsCount: Int,
    val contactInfo: ContactInfo,
    val menu: List<MenuItem>,
    val createdAt: Date,
    val updatedAt: Date
)

// ContactInfo data class
data class ContactInfo(
    val phone: String,
    val email: String,
    val socialMedia: List<SocialMedia>
)

// MenuItem data class
data class MenuItem(
    val itemId: String,
    val restaurantId: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val available: Boolean
)

// Orders data class
data class Order(
    val orderId: String,
    val userId: String,
    val restaurantId: String,
    val items: List<OrderItem>,
    val totalAmount: Double,
    val status: String,
    val deliveryAddress: String,
    val deliveryNotes: String?,
    val deliveryLocation: DeliveryLocation?,
    val history: List<OrderHistory>,
    val createdAt: Date,
    val updatedAt: Date
)

// OrderItem data class
data class OrderItem(
    val itemId: String,
    val quantity: Int
)

// DeliveryLocation data class
data class DeliveryLocation(
    val latitude: Double,
    val longitude: Double
)

// OrderHistory data class
data class OrderHistory(
    val status: String,
    val timestamp: Date
)

// Reviews data class
data class Review(
    val reviewId: String,
    val restaurantId: String,
    val userId: String,
    val rating: Double,
    val comment: String,
    val createdAt: Date
)

// Notifications data class
data class Notification(
    val notificationId: String,
    val userId: String,
    val orderId: String?,
    val message: String,
    val read: Boolean,
    val createdAt: Date
)
