package com.example.deliveryapp

import RestaurantList
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import reviewsList
import sampleRestaurants
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFFFFFCE4))
                    .windowInsetsPadding(WindowInsets.systemBars)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(0xFFFFFCE4))
                ) {
                    SetupNavGraph()
                }
            }
        }
    }

    @Composable
    fun SetupNavGraph() {
        val context = LocalContext.current
        val sharedPrefs = context.getSharedPreferences("user_prefs", MODE_PRIVATE)
        val navController = rememberNavController()
        var isLoggedIn by remember { mutableStateOf(sharedPrefs.getBoolean("isLoggedIn", false)) }
        val sharedImageName = 1

        val restaurantsList = listOf(
            Restaurant(
                restaurantId = "1",
                name = "La Dolce Vita",
                logo = 1,
                cuisineType = "Italian",
                location = "Downtown",
                avgRating = 4.7,
                contactInfo = ContactInfo(
                    phone = "123-456-7890",
                    email = "info@ladolcevita.com",
                    socialMedia = listOf(
                        SocialMedia("Facebook", "https://facebook.com/ladolcevita"),
                        SocialMedia("Instagram", "https://instagram.com/ladolcevita")
                    )
                ),
                img = sharedImageName,
                menu = listOf(/* MenuItem */),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Restaurant(
                restaurantId = "2",
                name = "Sakura Delight",
                logo = sharedImageName,
                cuisineType = "Italian",
                location = "Midtown",
                avgRating = 4.5,
                contactInfo = ContactInfo(
                    phone = "123-555-7890",
                    email = "contact@sakura.com",
                    socialMedia = listOf(
                        SocialMedia("Twitter", "https://twitter.com/sakuradelight"),
                        SocialMedia("Instagram", "https://instagram.com/sakuradelight")
                    )
                ),
                img = sharedImageName,
                menu = listOf(/* MenuItem */),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Restaurant(
                restaurantId = "3",
                name = "Grill & Chill",
                logo = sharedImageName,
                cuisineType = "Italian",
                location = "City Center",
                avgRating = 4.3,
                contactInfo = ContactInfo(
                    phone = "123-888-7890",
                    email = "support@grillchill.com",
                    socialMedia = listOf(
                        SocialMedia("Facebook", "https://facebook.com/grillchill"),
                        SocialMedia("Twitter", "https://twitter.com/grillchill")
                    )
                ),
                img = sharedImageName,
                menu = listOf(/* MenuItem */),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Restaurant(
                restaurantId = "4",
                name = "Le Petit Café",
                logo = sharedImageName,
                cuisineType = "Italian",
                location = "Old Town",
                avgRating = 4.8,
                contactInfo = ContactInfo(
                    phone = "123-999-7890",
                    email = "info@lepetitcafe.com",
                    socialMedia = listOf(
                        SocialMedia("Instagram", "https://instagram.com/lepetitcafe"),
                        SocialMedia("Pinterest", "https://pinterest.com/lepetitcafe")
                    )
                ),
                img = sharedImageName,
                menu = listOf(/* MenuItem */),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Restaurant(
                restaurantId = "5",
                name = "Spice Paradise",
                logo = sharedImageName,
                cuisineType = "Italian",
                location = "East End",
                avgRating = 4.6,
                contactInfo = ContactInfo(
                    phone = "123-444-7890",
                    email = "info@spiceparadise.com",
                    socialMedia = listOf(
                        SocialMedia("Facebook", "https://facebook.com/spiceparadise"),
                        SocialMedia("Instagram", "https://instagram.com/spiceparadise")
                    )
                ),
                img = sharedImageName,
                menu = listOf(/* MenuItem */),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Restaurant(
                restaurantId = "6",
                name = "El Buen Taco",
                logo = sharedImageName,
                cuisineType = "Italian",
                location = "West Side",
                avgRating = 4.4,
                contactInfo = ContactInfo(
                    phone = "123-222-7890",
                    email = "info@elbuentaco.com",
                    socialMedia = listOf(
                        SocialMedia("Instagram", "https://instagram.com/elbuentaco"),
                        SocialMedia("Twitter", "https://twitter.com/elbuentaco")
                    )
                ),
                img = sharedImageName,
                menu = listOf(/* MenuItem */),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Restaurant(
                restaurantId = "7",
                name = "Olive & Thyme",
                logo = sharedImageName,
                cuisineType = "Italian",
                location = "Harbor Front",
                avgRating = 4.9,
                contactInfo = ContactInfo(
                    phone = "123-666-7890",
                    email = "contact@oliveandthyme.com",
                    socialMedia = listOf(
                        SocialMedia("Facebook", "https://facebook.com/oliveandthyme"),
                        SocialMedia("Instagram", "https://instagram.com/oliveandthyme")
                    )
                ),
                img = sharedImageName,
                menu = listOf(/* MenuItem */),
                createdAt = Date(),
                updatedAt = Date()
            )
        )

        NavHost(
            navController = navController,
            startDestination = if (isLoggedIn) "home" else "splash_one"
        ) {
            composable("splash_one") {
                SplashScreenOne(onNext = { navController.navigate("splash_two") },
                    onSkip = { navController.navigate("login") })
            }
            composable("tracking") {
                    TrackingScreen(
                        onBackPress = { navController.popBackStack() }
                    )
            }
            composable("splash_two") {
                SplashScreenTwo(onNext = { navController.navigate("splash_three") })
            }
            composable("splash_three") {
                SplashScreenThree(onNext = { navController.navigate("login") })
            }
            composable("register") {
                RegisterScreen(onRegisterSuccess = {
                    navController.navigate("account_verification") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                    onLogin = {
                        navController.navigate("login") {
                            popUpTo("register") { inclusive = true }
                        }
                    })
            }
            composable("account_verification") {
                AccountVerificationScreen(
                    onCodeSent = { navController.navigate("verification_code") },
                    onBack = {
                        navController.navigate("register") {
                            popUpTo("account_verification") { inclusive = true }
                        }
                    }
                )
            }
            composable("verification_code") {
                VerificationCodeScreen(
                    onVerificationSuccess = {
                        navController.navigate("final_step") {
                            popUpTo("verification_code") { inclusive = true }
                        }
                    },
                    onBack = { navController.navigateUp() }
                )
            }
            composable("final_step") {
                AddingProfilePicture(
                    onAddingPicture = {
                        sharedPrefs.edit()
                            .putBoolean("hasAccount", true)
                            .putBoolean("isLoggedIn", true)
                            .apply()
                        isLoggedIn = true
                        navController.navigate("home") {
                            popUpTo("final_step") { inclusive = true }
                        }
                    },
                    onBack = {
                        navController.navigate("verification_code") {
                            popUpTo("final_step") { inclusive = true }
                        }
                    }
                )
            }
            composable("login") {
                AuthScreen(
                    onLoginSuccess = {
                        sharedPrefs.edit().putBoolean("isLoggedIn", true).apply()
                        isLoggedIn = true
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    onNoAccount = {
                        navController.navigate("register") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    onForgotPassword = { navController.navigate("reset_password_email") }
                )
            }
            composable("home") {
                Acceuil(
                    onLogout = {
                    sharedPrefs.edit().putBoolean("isLoggedIn", false).apply()
                    isLoggedIn = false
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                    onSearch = { navController.navigate("restaurant_list") },
                    restaurants1 = restaurantsList,
                    restaurants2 = restaurantsList,
                    restaurants3 = restaurantsList,
                    restaurants4 = restaurantsList,
                    restaurants5 = restaurantsList,
                    restaurants6 = restaurantsList,
                    restaurants7 = restaurantsList,

                )
            }
            composable("reset_password_email") {
                ResetPasswordEmailScreen(
                    onCodeVerified = {
                        navController.navigate("reset_password_new") {
                            popUpTo("reset_password_email") { inclusive = true }
                        }
                    },
                    onBackToLogin = {
                        navController.navigate("login") {
                            popUpTo("reset_password_email") { inclusive = true }
                        }
                    }
                )
            }
            composable("reset_password_new") {
                ResetPasswordNewPasswordScreen(
                    onPasswordReset = {
                        navController.navigate("login") {
                            popUpTo("reset_password_new") { inclusive = true }
                        }
                    },
                    onBack = {
                        navController.navigate("reset_password_email") {
                            popUpTo("reset_password_new") { inclusive = true }
                        }
                    }
                )
            }

            composable("restaurant_list") { RestaurantList(sampleRestaurants, navController) }

            composable("menu_list/{restaurantId}") { backStackEntry ->
                val restaurantId = backStackEntry.arguments?.getString("restaurantId") ?: ""
                MenuListScreen(navController, restaurantId,reviewsList)
            }
            composable("menu_detail/{restaurantId}/{menuName}") { backStackEntry ->
                val restaurantId = backStackEntry.arguments?.getString("restaurantId") ?: ""
                val menuName = backStackEntry.arguments?.getString("menuName") ?: ""
                MenuDetailScreen(navController, restaurantId, menuName)
            }
            composable("panier2/{orderId}/{note}") { backStackEntry ->
                val orderId = backStackEntry.arguments?.getString("orderId") ?: ""
                val note = backStackEntry.arguments?.getString("note")

                // Now you can pass these arguments to the FoodOrderScreen
                FoodOrderScreen(navController)
            }
        }

    }
}
