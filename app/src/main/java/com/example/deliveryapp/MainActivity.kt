
package com.example.deliveryapp

import RestaurantList
import android.os.Bundle
import android.widget.Toast
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
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RetrofitClient.initialize(this)
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
        val authModel = ViewModelProvider(this)[AuthModel::class.java]
        val menuModel = ViewModelProvider(this)[MenuModel::class.java]
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
            composable("splash_two") {
                SplashScreenTwo(onNext = { navController.navigate("splash_three") })
            }
            composable("splash_three") {
                SplashScreenThree(onNext = { navController.navigate("login") })
            }
            composable("register") {
                RegisterScreenOne(
                    onNext = { navController.navigate("register_step_two")
                    },
                    onLogin = {
                        navController.navigate("login") {
                            popUpTo("register") { inclusive = true }
                        }
                    }
                )
            }

            composable("register_step_two") {
                RegisterScreenTwo(
                    onRegisterSuccess = {
                        navController.navigate("login") {
                            popUpTo("login") { inclusive = true }
                        }
                        Toast.makeText(context, "Registered with success, check your email for verification", Toast.LENGTH_SHORT).show()
                    },
                    onLogin = {
                        navController.navigate("login") {
                            popUpTo("register") { inclusive = true }
                        }
                    },
                    authModel = authModel
                )
            }

            composable("verification_code") {
                VerificationCodeScreen(
                    onVerificationSuccess = {
                        navController.navigate("final_step") {
                            popUpTo("verification_code") { inclusive = true }
                        }
                    },
                    onBack = { navController.navigate("register_step_two") {
                        popUpTo("verification_code") { inclusive = true }
                    }
            } )
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

            composable("home") {
                Acceuil(
                    restaurantModel = restaurantModel,
                    navController = navController,
                    onProfile = { navController.navigate("profile") },
                    onLogOut = {
                        sharedPrefs.edit()
                            .putBoolean("isLoggedIn", false)
                            .apply()
                        isLoggedIn = false
                        navController.navigate("login") {
                            popUpTo("home") { inclusive = true }
                        }
                    }
                )
            }

            composable("profile") {
                ProfileScreen(
                    onBack = { navController.popBackStack() }
                )
            }

            composable("login") {
                AuthScreen(
                    onLoginSuccess = { user ->
                        val userInfo = mapOf(
                            "token" to user.token,
                            "name" to user.name,
                            "email" to user.email,
                            "phoneNumber" to user.phoneNumber
                        )
                        val userJson = Gson().toJson(userInfo)
                        sharedPrefs.edit().putBoolean("isLoggedIn", true)
                            .putString("userInfo", userJson).apply()

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
                    onForgotPassword = { navController.navigate("reset_password_email") },
                    authModel = authModel
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

            composable("restaurant_list?searchText={searchText}") { backStackEntry ->
                // Récupérer le paramètre 'searchText' depuis les arguments de la route
                val searchText = backStackEntry.arguments?.getString("searchText") ?: ""

                // Passer searchText au composant RestaurantList
                RestaurantList(restaurantModel, navController, searchText, isLoading.value, errorMessage.value)
            }


            composable("menu_list/{restaurantId}") { backStackEntry ->
                val restaurantId = backStackEntry.arguments?.getString("restaurantId") ?: ""
                MenuListScreen(restaurantModel,menuModel,navController, restaurantId)
            }
            composable("menu_detail/{menuId}") { backStackEntry ->
                val menuId = backStackEntry.arguments?.getString("menuId") ?: ""
                MenuDetailScreen(menuModel,navController, menuId)
            }
            composable("panier") { backStackEntry ->
                val orderId = backStackEntry.arguments?.getString("orderId") ?: ""
                val note = backStackEntry.arguments?.getString("note")

                // Now you can pass these arguments to the FoodOrderScreen
                FoodOrderScreen(navController)
            }
            composable("tracking") {
                TrackingScreen(
                    onBackPress = { navController.popBackStack() }
                )
            }
        }

    }
}

