package com.example.deliveryapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deliveryapp.ui.theme.Montserrat
import java.text.DecimalFormat

@Composable
fun Panier(navController: NavHostController) {
    val order = OrderManager.getCurrentOrder()  // Retrieve the current order from OrderManager

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Your Order", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        // Display the order items
        order.items.forEach { item ->
            Text(text = "Item: ${item.itemId}, Quantity: ${item.quantity}", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display the total amount
        val totalAmount = DecimalFormat("#.##").format(order.totalAmount)
        Text(text = "Total Amount: $totalAmount DZD", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        // Button to proceed with the order or reset
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    // Here you can handle order completion or redirection to another screen
                    // Example: navController.navigate("order_confirmation")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFDDB6F))
            ) {
                Text(text = "Proceed to Checkout", style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = Montserrat,
                    fontSize = 18.sp,
                    color = Color(0xFF3E2C0F)
                ))
            }

            Button(
                onClick = {
                    // Optional: Reset the current order (e.g., when the user decides to cancel or clear the cart)
                    navController.navigate("menu_list")  // Navigate back to the menu list or another screen
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text(text = "Clear Order", style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = Montserrat,
                    fontSize = 18.sp,
                    color = Color.White
                ))
            }
        }
    }
}
