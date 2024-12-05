package com.example.deliveryapp

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun FoodCardUI() {

    Box(
        modifier = Modifier.fillMaxWidth().background(Color(0xFFFFF8E1)))
    {

        Image(
            painter = painterResource(id = R.drawable.img1), // Remplacez par l'ID de votre image
            contentDescription = "Main Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp).clip(RoundedCornerShape(16.dp)) // Hauteur de l'image principale
        )


        Image(
            painter = painterResource(id = R.drawable.img1),
            contentDescription = "Logo Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.BottomCenter)
                .offset(y = 40.dp)
                .clip(CircleShape).shadow(30.dp, CircleShape, ambientColor = Color.Black)
        )
    }
}


@Composable
fun RatingDeliveryScreen(Restaurant : Restaurant) {
    var rating = remember { mutableStateOf(4) }       // Default 4 stars
    var reviewText =  remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()

            .background(Color(0xFFFFF8E1)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ) {
        FoodCardUI()
        Spacer(Modifier.height(60.dp))
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFF8E1)),
            horizontalAlignment = Alignment.CenterHorizontally,

            ){
            Text(text = "${Restaurant.name}", fontSize = 20.sp, color = Color.Black, fontFamily = FontFamily(
                Font(R.font.regular)) , fontWeight = FontWeight.Medium)
            Text(text = "${Restaurant.location}", fontSize = 14.sp, color = Color.Gray , fontFamily = FontFamily(
                Font(R.font.regular)))
            Text(text = "Order Delivered", fontSize = 16.sp, color = Color(0xFF1EC13F), fontFamily = FontFamily(
                Font(R.font.regular)))

            Spacer(modifier = Modifier.height(16.dp))

            // Rating Section
            Text(text = "Please Rate Delivery Service", fontSize = 18.sp, color = Color.Black,fontFamily = FontFamily(
                Font(R.font.meduim)) , fontWeight = FontWeight.Light)
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
                fontFamily = FontFamily(Font(R.font.regular))
            )


            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = reviewText.value,
                onValueChange = { reviewText.value = it },
                label = { Text(text = "Write review") },
                modifier = Modifier
                    .width(300.dp) // Prend toute la largeur disponible
                    .height(160.dp).border(0.dp, color = Color.Transparent),
                maxLines = 5,
                placeholder = { Text("Your review") },
                colors = TextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White, ))



            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { /* Handle Submit */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFECD5E)),
                modifier = Modifier.width(200.dp).height(50.dp)
            ) {
                Text(text = "SUBMIT", fontFamily =
                FontFamily(Font(R.font.bold)))
            }
        }}}




@Composable
fun RatingRestaurantScreen(Restaurant : Restaurant) {
    var rating = remember { mutableStateOf(4) }       // Default 4 stars
    var reviewText =  remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()

            .background(Color(0xFFFFF8E1)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ) {
        FoodCardUI()
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
                text = " from ${Restaurant.name} ?",
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
                FontFamily(Font(R.font.bold)))
            }
        }}}







