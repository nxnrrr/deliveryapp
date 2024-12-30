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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import reviewsList

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
        val restaurantModel = ViewModelProvider(this)[RestaurantModel::class.java]
        val restaurants = remember { mutableStateOf<List<Restaurant>>(emptyList()) }
        val isLoading = remember { mutableStateOf(true) } // Pour indiquer que les données sont en cours de chargement
        val errorMessage = remember { mutableStateOf<String?>(null) } // Pour afficher les messages d'erreur


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
                Acceuil(restaurantModel, navController)

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

            composable("restaurant_list") {

                RestaurantList(restaurants.value, navController, isLoading.value, errorMessage.value)
            }

            composable("menu_list/{restaurantId}") { backStackEntry ->
                val restaurantId = backStackEntry.arguments?.getString("restaurantId") ?: ""
                //MenuListScreen(navController, restaurantId,reviewsList)
            }
            composable("menu_detail/{restaurantId}/{menuName}") { backStackEntry ->
                val restaurantId = backStackEntry.arguments?.getString("restaurantId") ?: ""
                val menuName = backStackEntry.arguments?.getString("menuName") ?: ""
                //MenuDetailScreen(navController, restaurantId, menuName)
            }
            composable("panier2/{orderId}/{note}") { backStackEntry ->
                val orderId = backStackEntry.arguments?.getString("orderId") ?: ""
                val note = backStackEntry.arguments?.getString("note")

                // Now you can pass these arguments to the FoodOrderScreen
                //FoodOrderScreen(navController)
            }
        }

    }
}
