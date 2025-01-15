package com.example.deliveryapp

import java.util.Date
import java.util.UUID

// Créons des instances des classes nécessaires
val socialMedia1 = SocialMedia(type = "Facebook", url = "https://facebook.com/restaurant1")
val socialMedia2 = SocialMedia(type = "Instagram", url = "https://instagram.com/restaurant1")

val contactInfo1 = ContactInfo(
    phone = "+213 555 123 456",
    email = "contact@restaurant1.com",
    socialMedia = listOf(socialMedia1, socialMedia2)
)

val menuItem1 = MenuItem(
    itemId = UUID.randomUUID().toString(),
    restaurantId = UUID.randomUUID().toString(),
    name = "Salade César",
    description = "Salade fraîche avec poulet grillé, croutons et sauce César",
    price = 8.99,
    imageUrl = "https://example.com/salad.jpg",
    available = true
)

val menuItem2 = MenuItem(
    itemId = UUID.randomUUID().toString(),
    restaurantId = UUID.randomUUID().toString(),
    name = "Pizza Margherita",
    description = "Pizza classique avec sauce tomate et mozzarella",
    price = 12.99,
    imageUrl = "https://example.com/pizza.jpg",
    available = true
)

// Instance de Restaurant
val restaurant1 = Restaurant(
    restaurantId = UUID.randomUUID().toString(),
    name = "Le Gourmet",
    logo = "https://example.com/logo1.jpg",
    location = "Algiers, Algeria",
    cuisineType = "Italian",
    avgRating = 4.8,
    reviewsCount = 125,
    contactInfo = contactInfo1,
    menu = listOf(menuItem1, menuItem2),
    createdAt = Date(),
    updatedAt = Date()
)

val contactInfo2 = ContactInfo(
    phone = "+213 555 987 654",
    email = "info@restaurant2.com",
    socialMedia = listOf(
        SocialMedia(type = "Twitter", url = "https://twitter.com/restaurant2"),
        SocialMedia(type = "LinkedIn", url = "https://linkedin.com/restaurant2")
    )
)

val menuItem3 = MenuItem(
    itemId = UUID.randomUUID().toString(),
    restaurantId = UUID.randomUUID().toString(),
    name = "Sushi Mix",
    description = "Assortiment de sushis frais",
    price = 15.99,
    imageUrl = "https://example.com/sushi.jpg",
    available = true
)

val menuItem4 = MenuItem(
    itemId = UUID.randomUUID().toString(),
    restaurantId = UUID.randomUUID().toString(),
    name = "Ramen",
    description = "Soupe ramen avec porc, oeuf et légumes",
    price = 9.99,
    imageUrl = "https://example.com/ramen.jpg",
    available = true
)

val restaurant2 = Restaurant(
    restaurantId = UUID.randomUUID().toString(),
    name = "Tokyo Deli",
    logo = "https://example.com/logo2.jpg",
    location = "Oran, Algeria",
    cuisineType = "Japanese",
    avgRating = 4.7,
    reviewsCount = 200,
    contactInfo = contactInfo2,
    menu = listOf(menuItem3, menuItem4),
    createdAt = Date(),
    updatedAt = Date()
)


