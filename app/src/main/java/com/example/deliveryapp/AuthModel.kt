package com.example.deliveryapp

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AuthModel: ViewModel() {

    val isLoading = mutableStateOf(false)
    val isLoggedIn = mutableStateOf(false)
    val isRegistered = mutableStateOf(false)
    val authErrorMessage = mutableStateOf("")
    val user = mutableStateOf<User?>(null)
    val user1 = mutableStateOf<User?>(null)
    private val _authResponse = mutableStateOf<AuthResponse?>(null)

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                authErrorMessage.value = ""

                val response = RetrofitClient.api.login(loginRequest)

                if (response.isSuccessful) {
                    val authResponse = response.body()
                    if (authResponse != null) {
                        isLoggedIn.value = true
                        user.value = authResponse.data
                        _authResponse.value = authResponse
                        Log.d("AuthModel", "Login successful: ${authResponse.data}")
                    } else {
                        Log.e("AuthModel", "Login failed: Empty response body")
                        authErrorMessage.value = "Login failed: Empty response body"
                    }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    Log.e("AuthModel", "Login failed: $errorBody")
                    authErrorMessage.value = "Login failed: $errorBody"
                }
            } catch (e: Exception) {
                Log.e("AuthModel", "Login error: ${e.localizedMessage}", e)
                authErrorMessage.value = "An error occurred during login: ${e.localizedMessage}"
            } finally {
                isLoading.value = false
            }
        }
    }

    fun register(registerRequest: RegisterRequest, onRegisterSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                authErrorMessage.value = ""

                val response = RetrofitClient.api.register(registerRequest)

                if (response.isSuccessful) {
                    val authResponse = response.body()
                    if (authResponse != null) {
                        Log.d("AuthModel", "Registration successful: ${authResponse.data}")
                        onRegisterSuccess()
                    } else {
                        Log.e("AuthModel", "Registration failed: Empty response body")
                        authErrorMessage.value = "Registration failed: Empty response body"
                    }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    Log.e("AuthModel", "Registration failed: $errorBody")
                    authErrorMessage.value = "Registration failed: $errorBody"
                }
            } catch (e: Exception) {
                Log.e("AuthModel", "Registration error: ${e.localizedMessage}", e)
                authErrorMessage.value = "An error occurred during registration: ${e.localizedMessage}"
            } finally {
                isLoading.value = false
            }
        }
    }



//    fun register(email: String, password: String, confirmPassword: String) {
//        viewModelScope.launch {
//            try {
//                isLoading.value = true
//                val response = RetrofitClient.api.register(email, password, confirmPassword)
//
//                if (response.isSuccessful && response.body() != null) {
//                    Log.d("AuthModel", "Registration successful: ${response.body()}")
//                } else {
//                    Log.e("AuthModel", "Registration failed: ${response.errorBody()?.string()}")
//                    authErrorMessage.value = "Registration failed. Please check the input fields."
//                }
//            } catch (e: Exception) {
//                Log.e("AuthModel", "Registration error: ${e.localizedMessage}", e)
//                authErrorMessage.value = "An error occurred during registration: ${e.localizedMessage}"
//            } finally {
//                isLoading.value = false
//            }
//        }
//    }
//
//    fun verifyAccount(token: String) {
//        viewModelScope.launch {
//            try {
//                isLoading.value = true
//                val response = RetrofitClient.api.verifyAccount(token)
//
//                if (response.isSuccessful) {
//                    Log.d("AuthModel", "Account verification successful")
//                } else {
//                    Log.e("AuthModel", "Verification failed: ${response.errorBody()?.string()}")
//                    authErrorMessage.value = "Account verification failed."
//                }
//            } catch (e: Exception) {
//                Log.e("AuthModel", "Verification error: ${e.localizedMessage}", e)
//                authErrorMessage.value = "An error occurred during verification: ${e.localizedMessage}"
//            } finally {
//                isLoading.value = false
//            }
//        }
//    }
//
//    fun resetPassword(email: String) {
//        viewModelScope.launch {
//            try {
//                isLoading.value = true
//                val response = RetrofitClient.api.requestPasswordReset(email)
//
//                if (response.isSuccessful) {
//                    Log.d("AuthModel", "Password reset request sent successfully")
//                } else {
//                    Log.e("AuthModel", "Password reset request failed: ${response.errorBody()?.string()}")
//                    authErrorMessage.value = "Password reset request failed."
//                }
//            } catch (e: Exception) {
//                Log.e("AuthModel", "Password reset error: ${e.localizedMessage}", e)
//                authErrorMessage.value = "An error occurred during password reset request: ${e.localizedMessage}"
//            } finally {
//                isLoading.value = false
//            }
//        }
//    }
//
//    fun resetPasswordConfirm(token: String, newPassword: String) {
//        viewModelScope.launch {
//            try {
//                isLoading.value = true
//                val response = RetrofitClient.api.resetPassword(token, newPassword)
//
//                if (response.isSuccessful) {
//                    Log.d("AuthModel", "Password reset successful")
//                } else {
//                    Log.e("AuthModel", "Password reset failed: ${response.errorBody()?.string()}")
//                    authErrorMessage.value = "Password reset failed."
//                }
//            } catch (e: Exception) {
//                Log.e("AuthModel", "Password reset error: ${e.localizedMessage}", e)
//                authErrorMessage.value = "An error occurred during password reset: ${e.localizedMessage}"
//            } finally {
//                isLoading.value = false
//            }
//        }
//    }
//
//    fun checkAuthStatus() {
//        viewModelScope.launch {
//            try {
//                isLoading.value = true
//                val response = RetrofitClient.api.checkAuth()
//
//                if (response.isSuccessful) {
//                    isLoggedIn.value = true
//                    Log.d("AuthModel", "User is logged in")
//                } else {
//                    isLoggedIn.value = false
//                    Log.e("AuthModel", "User not authenticated")
//                }
//            } catch (e: Exception) {
//                Log.e("AuthModel", "Auth status error: ${e.localizedMessage}", e)
//                authErrorMessage.value = "An error occurred while checking authentication status."
//            } finally {
//                isLoading.value = false
//            }
//        }
//    }
}