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
    var token: String,
    val createdAt: Date,
    val updatedAt: Date,
    val role: Role
)

// SocialMedia data class
data class SocialMedia(
    val platform: String,
    val url: String
)

// Restaurants data class
data class Restaurant(
    val _id: String,
    val name: String,
    val logo: String,
    val cuisineType: List<String>,
    val location: String,
    val avgRating: Double,
    val contactInfo: ContactInfo,
    val img: String,
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
    val _id: String,
    val restaurantId: String,
    val name: String,
    val description: String,
    val price: Float,
    val imageUrl: String,
    val available: Boolean
)

// Orders data class
data class Order(
    val _id: String?,
    val orderId: String,
    val userId: String,
    val restaurantId: String,
    var items: MutableList<OrderItem>,
    var totalAmount: Float,
    val status: String,
    val deliveryAddress: String,
    var deliveryNotes: String?,
    val history: List<OrderHistory>,
    val createdAt: Date,
    var updatedAt: Date
)

// OrderItem data class
data class OrderItem(
    val itemId: String,
    val name: String,
    val restaurantId: String,
    val imageUrl: Int,
    var quantity: Int,
    var price: Float,
)

data class CartItem(
    val itemId: String,
    val quantity: Int,
    val price: Float,
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
data class ReviewsResponse(
    val reviews: List<Review>
)

data class LoginRequest(
    val email: String,
    val password: String,
)

data class RegisterRequest(
    val email: String,
    val password: String,
    val phoneNumber: String?,
    val name: String?,
)

data class OrderRequest(
    val userId: String,
    val restaurantId: String,
    val items: List<OrderItem>,
    val totalAmount: Int,
    val deliveryAddress: String,
    val deliveryNotes: String?,
    val status: String,
)

data class OrderResponse(
    val status: String,
    val data: Order,
    val message: String
)

data class AuthResponse(
    val status: String,
    val data: User,
    val message: String
)

//map that attaches to every order status an id
val orderStatusMap = mapOf(
    "Preparing" to 0,
    "Preparing" to 1,
    "OnTheWay" to 2,
    "Delivered" to 3
)