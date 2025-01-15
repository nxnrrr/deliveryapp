package com.example.deliveryapp

import android.content.Context.MODE_PRIVATE
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.gson.Gson

@Composable
fun Acceuil(restaurantModel: RestaurantModel, navController: NavHostController, onProfile: () -> Unit, onLogOut: () -> Unit) {
    val restaurants = restaurantModel.restaurants.value
    val isLoading = restaurantModel.isLoading.value
    var searchText by remember { mutableStateOf("") }
    var showMenu by remember { mutableStateOf(false) }

    val italiens = restaurants.filter { "Italian" in it.cuisineType }
    val traditionnal = restaurants.filter { "Traditionnal" in it.cuisineType }
    val fastFood = restaurants.filter { "Fast" in it.cuisineType }
    val asian = restaurants.filter { "Asian" in it.cuisineType }
    val mexican = restaurants.filter { "Mexican" in it.cuisineType }
    val turkish = restaurants.filter { "Turkish" in it.cuisineType }
    val indian = restaurants.filter { "Indian" in it.cuisineType }

    val context = LocalContext.current
    val sharedPrefs = context.getSharedPreferences("user_prefs", MODE_PRIVATE)
    val userJson = sharedPrefs.getString("userInfo", null)
    val userInfo = if (userJson != null) Gson().fromJson(userJson, Map::class.java) else null
    val name = userInfo?.get("name") as? String ?: "User"
    val img = userInfo?.get("image") as? String ?: ""

    LaunchedEffect(true) {
        restaurantModel.getRestaurants()
    }

    val data = listOf(
        "ITALIAN" to italiens,
        "TRADITIONNAL" to traditionnal,
        "FAST FOOD" to fastFood,
        "ASIAN" to asian,
        "MEXICAN" to mexican,
        "TURKISH" to turkish,
        "INDIAN" to indian
    ).filter { it.second.isNotEmpty() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8E1))
            .padding(top = 50.dp, start = 10.dp, end = 0.dp)
    ) {
        if (isLoading) {
            // Loading state
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = Color(0xFFFFB700),
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Loading",
                    fontSize = 18.sp,
                    color = Color(0xFF383838)
                )
            }
        } else {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 16.dp)
                    ) {
                        Text(
                            text = "Hello, $name",
                            fontFamily = FontFamily(Font(R.font.regular)),
                            fontSize = 18.sp,
                            color = Color(0xFF383838)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "What do you",
                            fontFamily = FontFamily(Font(R.font.bold)),
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF3E2C0F)
                        )
                        Text(
                            text = "want to eat",
                            fontFamily = FontFamily(Font(R.font.bold)),
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF3E2C0F)
                        )
                        Text(
                            text = "today?",
                            fontFamily = FontFamily(Font(R.font.bold)),
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF3E2C0F)
                        )
                    }

                    // Add Row for icons
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Cart Icon
                        IconButton(
                            onClick = { navController.navigate("panier") },
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFFB700))
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.cart),
                                contentDescription = "Cart",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        // Profile Image with Dropdown
                        Box {
                            Image(
                                painter = rememberAsyncImagePainter(model = img),
                                contentDescription = "Profile Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(CircleShape)
                                    .clickable { showMenu = true }
                            )

                            DropdownMenu(
                                expanded = showMenu,
                                onDismissRequest = { showMenu = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Edit your profile") },
                                    onClick = {
                                        onProfile()
                                        showMenu = false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Logout") },
                                    onClick = {
                                        onLogOut()
                                        showMenu = false
                                    }
                                )
                            }
                        }
                    }
                }

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
                                Log.d("RestaurantListComposable", "Entered composable with searchText: $searchText")
                                navController.navigate(("restaurant_list?searchText=$searchText"))
                            }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .height(56.dp),
                    shape = RoundedCornerShape(24.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.colors()
                )

                Spacer(modifier = Modifier.height(15.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    items(data) { (title, items) ->
                        Column(modifier = Modifier.padding(bottom = 16.dp)) {
                            Text(
                                text = title,
                                fontFamily = FontFamily(Font(R.font.meduim))
                            )

                            LazyRow(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                items(items) { item ->
                                    RestaurantItem(item, navController)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FilterButtons(filters: List<String>, selectedFilters: Set<String>, onFilterChange: (String) -> Unit) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(filters) { filter ->
            val isSelected = selectedFilters.contains(filter)
            FilterButton(
                text = filter,
                isSelected = isSelected,
                onClick = { onFilterChange(filter) }
            )
        }
    }
}
@Composable
fun FilterButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = if (!isSelected) {
            Modifier
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(20.dp)
                )
        } else Modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFFFFD700) else Color(0xFFFFFBD9),
            contentColor = if (isSelected) Color.Black else Color(0xFF968B7B)
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 0.dp, pressedElevation = 4.dp)
    ) {
        Text(text)
    }

}



