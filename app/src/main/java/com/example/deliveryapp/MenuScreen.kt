package com.example.deliveryapp

import android.adservices.adid.AdId
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deliveryapp.MenuCard
import com.example.deliveryapp.ui.theme.Montserrat
import getRestaurantByID
import sampleRestaurants

@Composable
fun MenuListScreen(navController: NavHostController, restaurantId: String) {
    val restaurants = sampleRestaurants
    val restaurant = getRestaurantByID(restaurantId, restaurants)
    val menuItems = restaurant?.menu ?: emptyList()
    var isClicked = remember { mutableStateOf(true) }
    var isClicked2 = remember { mutableStateOf(false) }
    var isClicked3 = remember { mutableStateOf(false) }
    var rating = remember { mutableStateOf(4)}
    var reviewText =  remember { mutableStateOf("") }




    Column (modifier = Modifier
        .fillMaxWidth()
        .background(Color(0xFFFFF8E1))
    )

    {
        Box(modifier = Modifier
            .fillMaxWidth()

            .fillMaxHeight(0.35f))
        {
            Image(
                painter = painterResource(id = restaurant!!.img),
                contentDescription = "Restaurant image",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.Transparent),
                contentScale = ContentScale.Crop
            )
            Image(
                painter = painterResource(id = R.drawable.shadow),
                contentDescription = "Restaurant image",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .offset(y = (-23).dp)
                    .background(Color.Transparent)
                    .graphicsLayer(alpha = 0.5f), // Adjust this value for opacity
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 70.dp)) {
                Text(
                    text = restaurant.name ,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        color = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))

                Row() {
                   Icon(
                       imageVector = Icons.Default.LocationOn,
                       contentDescription = null,
                       Modifier.size(18.dp),
                       tint = Color.White
                   )
                   Spacer(modifier = Modifier.width(2.dp))
                   Text(
                       text = restaurant.location,
                       style = MaterialTheme.typography.titleMedium.copy(
                           fontFamily = Montserrat,
                           fontWeight = FontWeight.Medium,

                           fontSize = 18.sp,
                           color = Color.White
                       )
                   )
               }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)

                    .padding(16.dp), // Optional padding
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.width(100.dp),
                    onClick = {
                        if (isClicked.value == false) {
                            isClicked.value = true
                        }

                        // Correcting the logical conditions for isClicked2 and isClicked
                        if (isClicked2.value || isClicked3.value) {
                            isClicked2.value = false
                            isClicked3.value = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isClicked.value) Color(0xFFFDDB6F) else Color.White // White when clicked
                    ),
                ) {
                    Text(
                        text = "Menu",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 16.sp,
                            color = Color(0xFF3E2C0F)
                        )
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Button(
                    modifier = Modifier.width(120.dp),
                    onClick = {
                        if (isClicked2.value == false) {
                            isClicked2.value = true
                        }

                        // Correcting the logical conditions for isClicked2 and isClicked
                        if (isClicked.value || isClicked3.value) {
                            isClicked.value = false
                            isClicked3.value = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isClicked2.value) Color(0xFFFDDB6F) else Color.White // White when clicked
                    ),
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 5.dp),
                        text = "Reviews",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 16.sp,
                            color = Color(0xFF3E2C0F)
                        )
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))

                Button(
                    modifier = Modifier.width(100.dp),
                    onClick = {
                        if (isClicked3.value == false) {
                            isClicked3.value = true
                        }

                        // Correcting the logical conditions for isClicked2 and isClicked
                        if (isClicked2.value || isClicked.value) {
                            isClicked2.value = false
                            isClicked.value = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isClicked3.value) Color(0xFFFDDB6F) else Color.White // White when clicked
                    ),
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 5.dp),
                        text = "Rate",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 16.sp,
                            color = Color(0xFF3E2C0F)
                        )
                    )
                }
            }
        }


        if (isClicked.value == true) {
            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn {

                items(menuItems) { menuItem ->
                    MenuCard(menuItem) {
                        navController.navigate("menu_detail/${restaurant?.restaurantId}/${menuItem.name}")
                    }
                }
            }
        }
        if (isClicked2.value == true){

        }
        if (isClicked3.value == true){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFFF8E1)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly

            ) {

                Spacer(Modifier.height(60.dp))
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFFFF8E1)),
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ){
                    Text(
                        text = "How was your last order ",
                        fontFamily = FontFamily(Font(R.font.meduim)),
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        fontSize = 25.sp)
                    Text(
                        text = " from ${restaurant!!.name} ?",
                        fontFamily = FontFamily(Font(R.font.meduim)),
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        fontSize = 25.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        for (i in 1..5) {
                            IconButton(onClick = { rating.value = i }) {
                                Icon(
                                    imageVector = if (i <= rating.value) Icons.Default.Star else Icons.Default.Star,
                                    contentDescription = "Star $i",
                                    tint = if (i <= rating.value) Color(0xFFFECD5E) else Color.Gray
                                )
                            }
                        }
                    }
                    Text(
                        text = when (rating.value) {
                            1 -> "Bad"
                            2 -> "Fair"
                            3 -> "Good"
                            4 -> "Very Good"
                            else -> "Excellent"
                        },
                        fontSize = 16.sp,
                        color = Color(0xFFFECD5E),
                        fontFamily = FontFamily(Font(R.font.meduim))
                    )


                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = reviewText.value,
                        onValueChange = { reviewText.value = it },
                        label = { Text(text = "Write review") },
                        modifier = Modifier
                            .width(300.dp)
                            .height(160.dp).border(0.dp, color = Color.Transparent),
                        maxLines = 5,
                        placeholder = { Text("Your review") },
                        colors = TextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White, ))



                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {  },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFECD5E)),
                        modifier = Modifier.width(200.dp).height(50.dp)
                    ) {
                        Text(text = "SUBMIT", fontFamily =
                        FontFamily(Font(R.font.bold))
                        )
                    }
                }}}
    }
}