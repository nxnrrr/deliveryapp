package com.example.deliveryapp

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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


@Composable

fun Acceuil( restaurants1: List<Restaurant>,restaurants2: List<Restaurant>,restaurants3: List<Restaurant>,navController:NavHostController) {
    var searchText by remember { mutableStateOf("") }
    var selectedFilters by remember { mutableStateOf(setOf<String>()) }
    val data = listOf(
        "Asian" to restaurants1,
        "Traditional" to restaurants2,
        "FastFood" to restaurants3
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8E1)) // Light background color
            .padding(top=50.dp,start = 20.dp, end = 20.dp)
    ) {
        Column() {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top // Aligns the text and image to the top
            ) {
                // Text content on the left half
                Column(
                    modifier = Modifier
                        .weight(1f) // Takes up half of the Row
                        .padding(end = 16.dp) // Space between text and image
                ) {
                    // Greeting text
                    Text(
                        text = "Hello, Nesrine",
                        fontFamily = FontFamily(
                            Font(R.font.regular) // Directly reference the font here
                        ),
                        fontSize = 18.sp,
                        color = Color(0xFF383838)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Main question text, each line in a separate Text composable
                    Text(
                        text = "What do you",
                        fontFamily = FontFamily(
                            Font(R.font.bold) // Directly reference the font here
                        ),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3E2C0F)
                    )
                    Text(
                        text = "want to eat",
                        fontFamily = FontFamily(
                            Font(R.font.bold) // Directly reference the font here
                        ),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3E2C0F)
                    )
                    Text(
                        text = "today?",
                        fontFamily = FontFamily(
                            Font(R.font.bold) // Directly reference the font here
                        ),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3E2C0F)
                    )
                }

                // Profile image on the right half, in a circular shape
                Image(
                    painter = painterResource(id = R.drawable.img1), // Replace with your image resource
                    contentDescription = "Profile Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp) // Size of the image
                        .clip(CircleShape) // Makes the image circular
                        .align(Alignment.Top) // Aligns the image to the top of the Row
                )

            }
            // Filter buttons
            FilterButtons(
                filters = listOf("Ratings", "Asian", "Traditional", "Syrian"),
                selectedFilters = selectedFilters,
                onFilterChange = { filter ->
                    selectedFilters = selectedFilters.toMutableSet().apply {
                        if (contains(filter)) remove(filter) else add(filter)
                    }
                }
            )

            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("Search menu, restaurant or etc") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "Search Icon",
                        tint = Color.Gray,
                        modifier = Modifier.clickable {


                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top=16.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(24.dp),
                singleLine = true,
                colors = TextFieldDefaults.colors(

                )
                )
            Spacer(modifier = Modifier.height(15gitgit fetch
                .dp))
            LazyColumn (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {

                items(data) { (title, items) ->
                    Column(modifier = Modifier.padding(bottom = 16.dp)) {

                        Text(
                            text = title,
                            fontFamily = FontFamily(
                                Font(R.font.meduim) // Directly reference the font here
                            ),

                        )



            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                items(items) { item ->
                    RestaurantItem(item) {
                        navController.navigate("menu_list/${item.restaurantId}")
                    }
                }
            }
        }
    }}}}}