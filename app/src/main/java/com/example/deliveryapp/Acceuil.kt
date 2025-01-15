package com.example.deliveryapp
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString


import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class SearchData(val query: String, val activeFilters: List<String>)

// Fonction pour sauvegarder les données en JSON
fun saveSearchToJson(context: Context, searchData: SearchData) {
    val json = Json.encodeToString(searchData)
    val file = File(context.filesDir, "search_data.json")
    file.writeText(json)
}
fun readSearchDataFromJson(context: Context): SearchData? {
    val file = File(context.filesDir, "search_data.json")
    return if (file.exists()) {
        val json = file.readText()
        Json.decodeFromString<SearchData>(json)
    } else {
        null
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun GreetingCard(context: Context, restaurantList: List<Restaurant>) {
    var searchText by remember { mutableStateOf("") }
    var selectedFilters by remember { mutableStateOf(setOf<String>()) }

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
                            Font(R.font.inter) // Directly reference the font here
                        ),
                        fontSize = 18.sp,
                        color = Color(0xFF383838)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Main question text, each line in a separate Text composable
                    Text(
                        text = "What do you",
                        fontFamily = FontFamily(
                            Font(R.font.montserrat_bold) // Directly reference the font here
                        ),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3E2C0F)
                    )
                    Text(
                        text = "want to eat",
                        fontFamily = FontFamily(
                            Font(R.font.montserrat_bold) // Directly reference the font here
                        ),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3E2C0F)
                    )
                    Text(
                        text = "today?",
                        fontFamily = FontFamily(
                            Font(R.font.montserrat_bold) // Directly reference the font here
                        ),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3E2C0F)
                    )
                }

                // Profile image on the right half, in a circular shape
                Image(
                    painter = painterResource(id = R.drawable.nesnes), // Replace with your image resource
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
                            val searchData = SearchData(searchText, selectedFilters.toList())
                            saveSearchToJson(context, searchData)
                            val savedData = readSearchDataFromJson(context)
                            savedData?.let {
                                Toast.makeText(
                                    context,
                                    "Recherche: ${it.query}, Filtres: ${
                                        it.activeFilters.joinToString(
                                            ", "
                                        )
                                    }",
                                    Toast.LENGTH_LONG
                                ).show()
                                Log.d(
                                    "SearchData",
                                    "Recherche: ${it.query}, Filtres: ${it.activeFilters}"
                                )
                            }
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(24.dp),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = Color.Gray,
                    cursorColor = Color.Black,
                    containerColor = Color.White,
                )
            )



            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                items(restaurantList) { restaurant ->
                    RestaurantCard(restaurant)
                }
            }
        }
    }}
