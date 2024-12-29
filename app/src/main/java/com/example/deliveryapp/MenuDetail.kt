package com.example.deliveryapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deliveryapp.ui.theme.Montserrat
import getRestaurantByID
import sampleRestaurants

@Composable
fun MenuDetailScreen(navController: NavHostController, restaurantID: String, menuName: String) {
    val restaurants = sampleRestaurants
    val restaurant = getRestaurantByID(restaurantID, restaurants)
    val menuItem = restaurant?.menu?.find { it.name == menuName }
    var note by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf(1) }

    Column {
        menuItem?.let { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFDDB6F)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(item.imageUrl),
                    contentDescription = null,
                    modifier = Modifier.size(150.dp)
                )
            }
            Column(modifier = Modifier.padding(23.dp)) {
                Text(text = item.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Price: ${item.price} DZD", fontSize = 18.sp)
                Text(text = item.description, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = Color.Red
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Une commande particulière?",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontFamily = Montserrat,
                            fontSize = 16.sp,
                            color = Color(0xFF3E2C0F)
                        )
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))

                OutlinedTextField(
                    value = note,
                    onValueChange = { note = it },
                    placeholder = { Text(text = "Des allergies? Des préférences?", fontSize = 12.sp) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Quantity: ", fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { if (quantity > 1) quantity-- },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFDDB6F))
                    ) {
                        Text(
                            text = "-",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontFamily = Montserrat,
                                fontSize = 22.sp,
                                color = Color(0xFF3E2C0F)
                            )
                        )
                    }
                    Text(
                        text = "$quantity",
                        modifier = Modifier.padding(8.dp),
                        fontSize = 16.sp
                    )
                    Button(
                        onClick = { quantity++ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFDDB6F))
                    ) {
                        Text(
                            text = "+",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontFamily = Montserrat,
                                fontSize = 22.sp,
                                color = Color(0xFF3E2C0F)
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                val totalPrice = item.price * quantity
                Text(text = "Prix: $totalPrice DZD", fontSize = 20.sp)

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        // Add the item to the current order using OrderManager
                        val newOrderItem = OrderItem(
                            itemId = item.itemId,
                            imageUrl = item.imageUrl,
                            quantity = quantity
                        )
                        OrderManager.addItemToOrder(newOrderItem,note)

                        // Navigate to the panier screen with the updated order
                        navController.navigate("panier2/${OrderManager.getCurrentOrder().orderId}/${note}")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFDDB6F))
                ) {
                    Text(
                        text = "Ajouter au panier",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontFamily = Montserrat,
                            fontSize = 18.sp,
                            color = Color(0xFF3E2C0F)
                        )
                    )
                }
            }
        }
    }
}
