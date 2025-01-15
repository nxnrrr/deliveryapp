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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deliveryapp.ui.theme.Montserrat
import java.util.Date

val sampleRestaurants = listOf(
    Restaurant(
        _id = "01",
        name = "La cité Asiatique",
        location = "Hydra, Alger",
        avgRating = 4.5,
        img = "R.drawable.asi",
        logo = "R.drawable.logo2",
        menu = listOf(
            MenuItem(
                restaurantId = "01",
                name = "Sushi",
                imageUrl = "R.drawable.sushi",
                price = 900f,
                available = true,
                description = "Sushiiiiiii ",
                _id = "04"

            ),
            MenuItem(
                restaurantId = "01",
                name = "Noodles",
                imageUrl = "R.drawable.noodles",
                price = 900f,
                available = true,
                description = "Sushiiiiiii ",
                _id = "03"

            ),
            MenuItem(
                restaurantId = "01",
                name = "Pizza Margherita",
                imageUrl = "R.drawable.pizza2",
                price = 500f,
                available = true,
                description = "Delicious pasta",
                _id = "01"
            ),
            MenuItem(
                restaurantId = "01",
                name = "Pasta",
                imageUrl = "R.drawable.img1",
                price = 900f,
                available = true,
                description = "Delicious pizza ",
                _id = "02"

            ),


            ),
        contactInfo = ContactInfo(
            phone = "05 40 95 35",
            email = "contact@otacos.com",
            socialMedia = listOf(
                SocialMedia(platform = "facebook", url = "https://facebook.com/otacos"),
                SocialMedia(platform = "instagram", url = "https://instagram.com/otacos")
            )
        ),
        createdAt = Date(),
        cuisineType = listOf("Asian"),

        updatedAt = Date()
    ),
    Restaurant(
        _id = "03",
        name = "O'Tacos",
        location = "Bab Ezzouar, Alger",
        avgRating = 4.5,
        img = "R.drawable.presto",
        logo = "R.drawable.logo1",
        menu = listOf(
            MenuItem(
                restaurantId = "03",
                name = "Pizza Margherita",
                imageUrl = "R.drawable.pizza2",
                price = 500f,
                available = true,
                description = "Delicious pasta",
                _id = "01"

            ),
            MenuItem(
                restaurantId = "03",
                name = "Pasta",
                imageUrl = "R.drawable.img1",
                price = 900f,
                available = true,
                description = "Delicious pizza ",
                _id = "02"

            )
        ),
        contactInfo = ContactInfo(
            phone = "05 40 95 35",
            email = "contact@otacos.com",
            socialMedia = listOf(
                SocialMedia(platform = "facebook", url = "https://facebook.com/otacos"),
                SocialMedia(platform = "instagram", url = "https://instagram.com/otacos")
            )
        ),

        createdAt = Date(),
        cuisineType = listOf("Asian"),

        updatedAt = Date()
    )
)
@Composable
fun MenuDetailScreen(menuModel: MenuModel,navController: NavHostController, menuId: String) {
    LaunchedEffect(true) {
        menuModel.getMenubyId(menuId)
    }
    val menuItem = menuModel.menu1

    val context = LocalContext.current

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
                    painter = painterResource(R.drawable.pizza),
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
                            itemId = item._id,
                            name = item.name,
                            imageUrl = R.drawable.img1,
                            quantity = quantity,
                            price = item.price,
                            restaurantId = item.restaurantId
                        )
                        OrderManager.addItemToOrder(
                            newOrderItem,
                            context = context,
                            context
                        )

                        // Navigate to the panier screen with the updated order
                        navController.navigate("panier")
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



