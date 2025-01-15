package com.example.deliveryapp
import android.content.Context
import android.content.SharedPreferences

class TokenManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("IdManager", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

     fun getToken(): String {
        return sharedPreferences.getString("id", "") ?: ""
    }

     fun saveToken(id: String) {
        editor.putString("id", id)
        editor.apply()
    }

     fun clearToken() {
        editor.remove("id")
        editor.apply()
    }


}