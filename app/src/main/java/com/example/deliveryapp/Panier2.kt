package com.example.deliveryapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import java.text.DecimalFormat

@Composable
fun FoodOrderScreen(navController: NavHostController, note: String?) {
    val order = OrderManager.getCurrentOrder()
    var totalAmount by remember { mutableStateOf(order.totalAmount.toDouble()) }
    val subTotal = totalAmount
    val taxesFees = 10.0
    val deliveryFee = 5.0
    val totalPrice = subTotal + taxesFees + deliveryFee

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 0.dp, end = 0.dp, bottom = 16.dp)
            .background(color = Color(0xFFF7F5E9))
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "  Current location",
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.meduim)),
            color = Color.Gray
        )
        Text(
            text = "  Jl. Soekarno Hatta 15A Malang",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.meduim)),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            order.items.forEach { item ->
                FoodCard(
                    orderItem = item,
                    price = OrderManager.getItemPrice(item.itemId, order.restaurantId).toDouble(),
                    name = OrderManager.getItemName(item.itemId, order.restaurantId),
                    total = totalPrice,
                    onQuantityChange = { id, delta ->
                        val orderItem = order.items.find { it.itemId == id }
                        orderItem?.let {
                            val newQuantity = it.quantity + delta
                            if (newQuantity > 0) {
                                val pricePerItem = OrderManager.getItemPrice(it.itemId, order.restaurantId).toDouble()
                                it.quantity = newQuantity
                                order.totalAmount += (delta * pricePerItem).toFloat()
                                totalAmount = order.totalAmount.toDouble()
                            }
                            if (newQuantity == 0) {
                                val pricePerItem = OrderManager.getItemPrice(it.itemId, order.restaurantId).toDouble()
                                order.totalAmount += (delta * pricePerItem).toFloat()
                                totalAmount = order.totalAmount.toDouble()
                                order.items.remove(it)
                            }
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        SummarySection(subTotal, taxesFees, deliveryFee, totalPrice,order.deliveryNotes)
        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { },
                modifier = Modifier
                    .width(300.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA9411D))
            ) {
                Text(
                    text = "Checkout",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.bold))
                )
            }

        }
    }
}

@Composable
fun FoodCard(orderItem: OrderItem, total: Double, price: Double,name: String, onQuantityChange: (String, Int) -> Unit) {
    var quantity by remember { mutableStateOf(orderItem.quantity) }

    Card(
        modifier = Modifier
            .width(360.dp)
            .height(150.dp)
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(Color(0xFFF7F5E9)),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(color = Color(0xFFFFD67A), shape = RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                // Replace with a real image or use a placeholder image if orderItem.imageUrl is invalid
                Image(
                    painter = painterResource(id = orderItem.imageUrl), // Use a placeholder image here
                    contentDescription = "Item image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .background(Color.Transparent),

                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = name,
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.meduim))
                )
                Text(
                    text = "${price} DZ",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontFamily = FontFamily(Font(R.font.meduim))
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            if (quantity >= 1) {
                                quantity--
                                onQuantityChange(orderItem.itemId, -1)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFDDB6F))
                    ) {
                        Text(
                            text = "-",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontSize = 22.sp,
                                color = Color(0xFF3E2C0F)
                            )
                        )
                    }
                    Text(
                        text = quantity.toString(),
                        modifier = Modifier.padding(8.dp),
                        fontSize = 16.sp
                    )
                    Button(
                        onClick = {
                            quantity++
                            onQuantityChange(orderItem.itemId, 1)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFDDB6F))
                    ) {
                        Text(
                            text = "+",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontSize = 22.sp,
                                color = Color(0xFF3E2C0F)
                            )
                        )
                    }
                }
                Text(
                    text = "Price: ${String.format("%.2f", price)} DZ",
                    fontSize = 14.sp,
                    color = Color(0xFFA9411D),
                    fontFamily = FontFamily(Font(R.font.meduim))
                )
            }
        }
    }
}

@Composable
fun SummarySection(subTotal: Double, taxesFees: Double, deliveryFee: Double, totalPrice: Double, notes: String?) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        SummaryRow(label = "  Prix", amount = subTotal)
        SummaryRow(label = "  Taxes", amount = taxesFees)
        SummaryRow(label = "  Prix de livraison ", amount = deliveryFee)

        // Display notes or a fallback text if null
        Text(
            text = notes ?: "No additional notes", // Fallback text if notes are null
            fontSize = 14.sp,
            color = Color.Red,
            fontFamily = FontFamily(Font(R.font.meduim)),
            fontWeight = FontWeight.Normal // You can set this to Bold if you want
        )

        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        SummaryRow(label = "  Prix Total", amount = totalPrice, isBold = true)
    }
}


@Composable
fun SummaryRow(label: String, amount: Double, isBold: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.meduim)),
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
        )
        Text(
            text = " ${String.format("%.2f", amount)} DZ   ",
            fontSize = 14.sp,
            color = Color(0xFFA9411D),
            fontFamily = FontFamily(Font(R.font.meduim)),
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
        )
    }
}
