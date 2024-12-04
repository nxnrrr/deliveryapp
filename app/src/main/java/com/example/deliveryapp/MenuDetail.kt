package com.example.projettdm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    var userInput by remember { mutableStateOf("")}
    var quantity by remember { mutableStateOf(1) }

    Column() {
        menuItem?.let {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFDDB6F)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(it.imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                )
            }
            Column( modifier = Modifier
                .padding(23.dp)){

                Text(text = it.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Price: ${it.price} DZD", fontSize = 18.sp)
                Text(text = "${it.description}", fontSize = 16.sp)

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
                    value = userInput,
                    onValueChange = { userInput = it },
                    placeholder = { Text(
                        text = ("Des allergies? Des préférences?" ),
                        fontSize = 12.sp) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),

                )
                Spacer(modifier = Modifier.height(20.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Quantity: ", fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { if (quantity > 1) quantity-- },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = androidx.compose.ui.graphics.Color(0xFFFDDB6F) // Orange color
                        )
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
                    Text(text = "$quantity", modifier = Modifier.padding(8.dp), fontSize = 16.sp)
                    Button(
                        onClick = { quantity++ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = androidx.compose.ui.graphics.Color(0xFFFDDB6F) // Orange color
                        )
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

                val totalPrice = it.price * quantity
                Text(text = "Total Price: $totalPrice DZD", fontSize = 20.sp)

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {  },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = androidx.compose.ui.graphics.Color(0xFFFDDB6F) // Orange color
                    )
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
    }}
