package com.example.deliveryapp

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
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
import androidx.navigation.NavController

@Composable

fun Acceuil( restaurants1: List<Restaurant>,restaurants2: List<Restaurant>,restaurants3: List<Restaurant>,
             restaurants4: List<Restaurant>,restaurants5: List<Restaurant>,restaurants6: List<Restaurant>,
             restaurants7: List<Restaurant>, onLogout: () -> Unit, onSearch: () -> Unit) {
    var searchText by remember { mutableStateOf("") }
    var selectedFilters by remember { mutableStateOf(setOf<String>()) }
    val data = listOf(
        "ITALIAN" to restaurants1,
        "TRADITIONNAL" to restaurants2,
        "FAST FOOD" to restaurants3,
        "ASIAN" to restaurants4,
        "MEXICAN" to restaurants5,
        "TURKISH" to restaurants6,
        "INDIAN" to restaurants7
    ).filter { it.second.isNotEmpty() }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8E1)) // Light background color
            .padding(top = 50.dp, start = 10.dp, end = 0.dp)
    ) {
        Column {
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
                filters = listOf("Ratings", "Italian", "Fast food", "Mexican", "Indian","Turkish", "Asian", "Traditionnal"),
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
                        modifier = Modifier.clickable { onSearch() }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(24.dp),
                singleLine = true,
                colors = TextFieldDefaults.colors(

                )
            )
            Spacer(modifier = Modifier.height(15.dp))
            LazyColumn (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(0.dp)
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
                                //RestaurantItem(item, onClick) {

                                    // navController.navigate("menu_list/${item.restaurantId}")
                                //}
                            }
                        }
                    }
                }}}}}



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


//@Composable
//fun RestaurantLogo(logoUrl: String) {
//    Image(
//        painter = rememberImagePainter(logoUrl),
//        contentDescription = "Restaurant Logo",
//        modifier = Modifier
//            .size(70.dp)
//            .clip(CircleShape)
//            .border(4.dp, Color(0xFFFDDB6F), CircleShape),
//        contentScale = ContentScale.Crop
//    )
//}

//@Composable
//fun RestaurantItem(restaurant: Restaurant, onClick: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .width(350.dp)
//            .padding(8.dp)
//            .clickable(onClick = onClick),
//        elevation = CardDefaults.cardElevation(4.dp),
//        colors = CardDefaults.cardColors(containerColor = Color(0xFFFDDB6F))
//    ) {
//        Column(
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        ) {
//            Box {
//                /*Image(
//                    painter = rememberImagePainter(data = restaurant.img),
//                    contentDescription = "${restaurant.name} image",
//                    modifier = Modifier
//                        .size(350.dp, 100.dp),
//                    contentScale = ContentScale.Crop
//                )*/
//                Image(
//                    painter = painterResource(id = R.drawable.asi),
//                    contentDescription = "${restaurant.name} image",
//                    modifier = Modifier
//                        .size(350.dp, 100.dp),
//                    contentScale = ContentScale.Crop
//                )
//
//                Box(
//                    modifier = Modifier
//                        .align(Alignment.Center)
//                        .offset(y = 45.dp)
//                ) {
//                    // RestaurantLogo(restaurant.logo)
//                    Image(
//                        painter = painterResource(id = R.drawable.logo1), // Remplacez par votre ressource locale
//                        contentDescription = "Restaurant Logo",
//                        modifier = Modifier
//                            .size(70.dp)
//                            .clip(CircleShape)
//                            .border(4.dp, Color(0xFFFDDB6F), CircleShape),
//                        contentScale = ContentScale.Crop
//                    )
//                }
//            }
//            Spacer(modifier = Modifier.height(18.dp))
//            Column(
//                modifier = Modifier
//                    .padding(12.dp)
//                    .align(Alignment.CenterHorizontally),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = restaurant.name,
//                    style = MaterialTheme.typography.titleMedium.copy(
//                        // fontFamily = Montserrat,
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 20.sp,
//                        color = Color(0xFF3E2C0F)
//                    )
//                )
//                Spacer(modifier = Modifier.height(4.dp))
//                Row {
//                    Icon(
//                        imageVector = Icons.Default.LocationOn,
//                        contentDescription = null,
//                        modifier = Modifier.size(16.dp),
//                        tint = Color(0xFFA9411D)
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = restaurant.location,
//                        style = MaterialTheme.typography.bodyMedium.copy(
//                            // fontFamily = Montserrat,
//                            fontSize = 12.sp,
//                            color = Color(0xFF3E2C0F)
//                        )
//                    )
//                }
//            }
////            Row (
////                horizontalArrangement = Arrangement.Center,
////                modifier = Modifier.fillMaxWidth()
////            ) {
////
////                // Vérification de la présence de Twitter dans les réseaux sociaux
////                restaurant.contactInfo.socialMedia.find { it.platform == "Twitter" }?.let {
////                    Icon(
////                        painter = painterResource(id = R.drawable.twitter),
////                        contentDescription = "Twitter Icon",
////                        modifier = Modifier.size(16.dp),
////                        tint = Color(0xFFA9411D)
////                    )
////                    Spacer(modifier = Modifier.width(20.dp))
////                }
////
////                // Vérification de la présence de Facebook dans les réseaux sociaux
////                restaurant.contactInfo.socialMedia.find { it.platform == "Facebook" }?.let {
////                    Icon(
////                        painter = painterResource(id = R.drawable.face),
////                        contentDescription = "Facebook Icon",
////                        modifier = Modifier.size(16.dp),
////                        tint = Color(0xFFA9411D)
////                    )
////                    Spacer(modifier = Modifier.width(20.dp))
////                }
////
////                // Vérification de la présence d'Instagram dans les réseaux sociaux
////                restaurant.contactInfo.socialMedia.find { it.platform == "Instagram" }?.let {
////                    Icon(
////                        painter = painterResource(id = R.drawable.insta),
////                        contentDescription = "Instagram Icon",
////                        modifier = Modifier.size(16.dp),
////                        tint = Color(0xFFA9411D)
////                    )
////                }
////            }
//
//            Spacer(modifier = Modifier.height(5.dp))
//
//            Row(
//                horizontalArrangement = Arrangement.spacedBy(8.dp), // Espacement entre les Box
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.align(Alignment.CenterHorizontally)
//            ) {
//                // Pour chaque type de cuisine dans la liste cuisineType
//                restaurant.cuisineType.forEach { cuisine ->
//                    Box(
//                        modifier = Modifier
//                            .clip(RoundedCornerShape(10.dp))
//                            .background(Color.White)
//                            .padding(vertical = 3.dp, horizontal = 8.dp)
//                    ) {
//                        Text(
//                            text = "$cuisine food",
//                            // fontFamily = Montserrat,
//                            fontSize = 12.sp,
//                            color = Color(0xFF3E2C0F)
//                        )
//                    }
//                }
//            }
//
//
//            Spacer(modifier = Modifier.height(5.dp))
//
//
//        }
//    }
//
//
//}

