package com.example.deliveryapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deliveryapp.com.example.deliveryapp.OrderModel
import com.example.deliveryapp.ui.theme.Montserrat
import java.text.DecimalFormat

@Composable
fun FoodOrderScreen(navController: NavHostController, orderModel: OrderModel, onPlaceOrderSuccess: () -> Unit) {
    val context = LocalContext.current
    var orders by remember { mutableStateOf(OrderManager.getCartItems(context)) }

    // Calculate totals based on current orders
    val totalPriceNet = orders.sumOf { (it.price * it.quantity).toDouble() }
    val subTotal = DecimalFormat("#.##").format(totalPriceNet).toDouble()
    val taxesFees = 10.0
    val deliveryFee = 5.0
    val totalPrice = subTotal + taxesFees + deliveryFee
    val isLoading by orderModel.isLoading

    // Effect to update orders when SharedPreferences changes
    LaunchedEffect(Unit) {
        // You could set up a Flow here to observe SharedPreferences changes
        orders = OrderManager.getCartItems(context)
    }


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
            orders.forEach { item ->
                 // Use remembered state for quantity
                FoodCard(
                    orderItem = item,
                    price = item.price,
                    total = totalPrice,
                    onQuantityChange = { id, delta ->
                        // Update SharedPreferences
                        OrderManager.updateItemQuantity(id, delta, context)
                        // Update local state
                        orders = OrderManager.getCartItems(context)
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (orders.isEmpty()) {
            EmptyCartMessage()
        } else {
            SummarySection(
                subTotal = subTotal,
                taxesFees = taxesFees,
                deliveryFee = deliveryFee,
                totalPrice = totalPrice
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    // place new order
                    val orderRequest = OrderRequest(
                        userId = "67733f029830e0566597ed68",
                        restaurantId = "67733f029830e0566597ed68",
                        items = orders.map { OrderItem(
                            it.itemId, it.name,
                            imageUrl = it.imageUrl,
                            quantity = it.imageUrl,
                            price = it.quantity * it.price,
                            restaurantId = it.restaurantId
                        ) },
                        totalAmount = totalPrice.toInt(),
                        status = "Pending",
                        deliveryAddress = "Higher National School of Comouter Science ESI ex INI, Oued Smar, Algiers",
                        deliveryNotes = "Please call when you arrive"
                    )
                    orderModel.placeOrder(orderRequest, onPlaceOrderSuccess)
                },
                modifier = Modifier
                    .width(300.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA9411D))
            ) {
                Text(
                    text = if (isLoading) "Placing Order..." else "Checkout",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.bold))
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    navController.navigate("home")
                },
                modifier = Modifier
                    .width(300.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA9411D))
            ) {
                Text(
                    text = "Continuer vos achats",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.bold))
                )
            }
        }
    }
}


@Composable
fun FoodCard(orderItem: OrderItem,
             total: Double,
             price: Float,
             onQuantityChange: (String, Int) -> Unit) {
    val context = LocalContext.current
    var quantity by remember { mutableStateOf(orderItem.quantity) }

    LaunchedEffect(orderItem.quantity) {
        quantity = orderItem.quantity
    }

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

                Image(
                    painter = painterResource(id = orderItem.imageUrl), // Assuming imageUrl is a valid resource ID
                    contentDescription = "Item image",
                    modifier = Modifier
                        .size(64.dp)
                        .background(Color.Transparent),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = orderItem.name,
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.meduim))
                )
                Text(
                    text = "${orderItem.price} DZ",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontFamily = FontFamily(Font(R.font.meduim))
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            if (quantity > 0) {
                                onQuantityChange(orderItem.itemId, -1)
                                quantity--
                            }
                        },
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
                        text = quantity.toString(),
                        modifier = Modifier.padding(8.dp),
                        fontSize = 16.sp
                    )

                    Button(
                        onClick = {
                            onQuantityChange(orderItem.itemId, 1)
                            quantity++
                        },
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
                Text(
                    text = "Total: ${String.format("%.2f", price * quantity)} DZ",
                    fontSize = 14.sp,
                    color = Color(0xFFA9411D),
                    fontFamily = FontFamily(Font(R.font.meduim))
                )
            }
        }
    }
}

@Composable
fun EmptyCartMessage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No items added to cart yet",
            textAlign = TextAlign.Center
        )
    }
}



@Composable
fun SummarySection(subTotal: Double, taxesFees: Double, deliveryFee: Double, totalPrice: Double) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        SummaryRow(label = "  Prix", amount = subTotal)
        SummaryRow(label = "  Taxes", amount = taxesFees)
        SummaryRow(label = "  Prix de livraison ", amount = deliveryFee)
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
