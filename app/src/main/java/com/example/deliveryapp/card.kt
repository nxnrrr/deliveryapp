package com.example.deliveryapp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star

import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow

import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RestaurantCard(restaurant: Restaurant) {
    var review = formatReviewCount(restaurant.reviewsCount)
    var isFavorite = remember { mutableStateOf(false) }
    Card (
        modifier = Modifier.padding(16.dp).size(280.dp,200.dp),

        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.background(Color(0xFFF8BF40)).fillMaxSize()) {
            Box(){
            Image(
                painter = painterResource(id = R.drawable.nesnes), // Replace with your image resource
                contentDescription = "Food Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )


            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(color = Color(0xFF4D361D), shape = RoundedCornerShape(13.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(" ${restaurant.avgRating} ⭐ (${review}) ", color = Color(0xFFFFC107), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Box(
                    modifier = Modifier
                        .size(30.dp) // Size of the circle
                        .clip(CircleShape).background(Color(0xFF3E2C0F)),
                        contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isFavorite.value) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "favorite",
                        tint = Color(0xFFF8BF40),
                        modifier = Modifier.clickable { isFavorite.value = !isFavorite.value })
                }
            }}


            Text(
                text = " ${restaurant.name}",
                fontFamily = FontFamily(
                    Font(R.font.sofia_pro_semibold) // Directly reference the font here
                ),
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            // Delivery info
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Free Delivery",
                    tint = Color(0xFF3E2C0F)
                )
                Text(
                    text = "Free delivery",
                    fontFamily = FontFamily(
                        Font(R.font.sofia_pro_semibold) // Directly reference the font here
                    ),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 4.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource( R.drawable.time),
                    contentDescription = "Delivery Time",
                    modifier = Modifier.padding(end=4.dp)
                )
                Text(
                    text ="10-15 mins",
                    fontFamily = FontFamily(
                        Font(R.font.sofia_pro_semibold) // Directly reference the font here
                    ),

                )
            }

            // Tags
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Tag(text = "NOODLES")
                Tag(text = "DIMSUM")
                Tag(text = "FAST FOOD")
            }
        }
    }
}

@Composable
fun Tag(text: String) {
    Text(
        text = text,
        fontFamily = FontFamily(
            Font(R.font.sofia_pro_semibold) // Directly reference the font here
        ),
        fontSize = 8.sp,
        color = Color(0xFF3E2C0F),
        modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}

fun formatReviewCount(reviewCount:Int): String {
    return when {
        reviewCount > 50 -> "50+"
        reviewCount > 25 && reviewCount <= 50 -> "25+"

        else -> reviewCount.toString()
    }
}