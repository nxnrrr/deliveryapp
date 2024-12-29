import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.deliveryapp.ContactInfo
import com.example.deliveryapp.MenuItem
import com.example.deliveryapp.R
import com.example.deliveryapp.Restaurant
import com.example.deliveryapp.RestaurantItem
import com.example.deliveryapp.SocialMedia
import java.util.Date


@Composable
fun RestaurantList(restaurants: List<Restaurant>, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(restaurants) { restaurant ->
                RestaurantItem(restaurant) {
                    navController.navigate("menu_list/${restaurant.restaurantId}")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}



val sampleRestaurants = listOf(
    Restaurant(
        restaurantId = "01",
        name = "La cité Asiatique",
        location = "Hydra, Alger",
        avgRating = 4.5,
        img = R.drawable.asi,
        logo = R.drawable.logo2,
        menu = listOf(
            MenuItem(
                restaurantId = "01",
                name = "Sushi",
                imageUrl = R.drawable.sushi,
                price = 900f,
                available = true,
                description = "Sushiiiiiii ",
                itemId = "04"

            ),
            MenuItem(
                restaurantId = "01",
                name = "Noodles",
                imageUrl = R.drawable.noodles,
                price = 900f,
                available = true,
                description = "Sushiiiiiii ",
                itemId = "03"

            ),
            MenuItem(
                restaurantId = "01",
                name = "Pizza Margherita",
                imageUrl = R.drawable.pizza2,
                price = 500f,
                available = true,
                description = "Delicious pasta",
                itemId = "01"
            ),
            MenuItem(
                restaurantId = "01",
                name = "Pasta",
                imageUrl = R.drawable.img1,
                price = 900f,
                available = true,
                description = "Delicious pizza ",
                        itemId = "02"

            ),


        ),
        contactInfo = ContactInfo(
            phone = "05 40 95 35",
            email = "contact@otacos.com",
            socialMedia = listOf(
                SocialMedia(type = "facebook", url = "https://facebook.com/otacos"),
                SocialMedia(type = "instagram", url = "https://instagram.com/otacos")
            )
        ),
        createdAt = Date(),
        cuisineType = "Asian",

        updatedAt = Date()
    ),
    Restaurant(
        restaurantId = "03",
        name = "O'Tacos",
        location = "Bab Ezzouar, Alger",
        avgRating = 4.5,
        img = R.drawable.presto,
        logo = R.drawable.logo1,
        menu = listOf(
            MenuItem(
                restaurantId = "03",
                name = "Pizza Margherita",
                imageUrl = R.drawable.pizza2,
                price = 500f,
                available = true,
                description = "Delicious pasta",
                itemId = "01"

            ),
            MenuItem(
                restaurantId = "03",
                name = "Pasta",
                imageUrl = R.drawable.img1,
                price = 900f,
                available = true,
                description = "Delicious pizza ",
                itemId = "02"

            )
        ),
        contactInfo = ContactInfo(
            phone = "05 40 95 35",
            email = "contact@otacos.com",
            socialMedia = listOf(
                SocialMedia(type = "facebook", url = "https://facebook.com/otacos"),
                SocialMedia(type = "instagram", url = "https://instagram.com/otacos")
            )
        ),

        createdAt = Date(),
        cuisineType = "Asian",

        updatedAt = Date()
    ),
    Restaurant(
        restaurantId = "02",
    name = "El marssam",
    location = "Bab Ezzouar, Alger",
    avgRating = 4.5,
    img = R.drawable.mer,
    logo = R.drawable.marssam,
    menu = listOf(
        MenuItem(
            restaurantId = "02",
            name = "Pizza Margherita",
            imageUrl = R.drawable.img1,
            price = 500f,
            available = true,
            description = "Delicious pizza with fresh ingredients.",
            itemId = "01"

        ),
                MenuItem(
                restaurantId = "02",
        name = "Pasta",
        imageUrl = R.drawable.img1,
        price = 900f,
        available = true,
        description = "Delicious pizza with fresh ingredients.",
                    itemId = "02"

                )
    ),
    contactInfo = ContactInfo(
        phone = "05 40 95 35",
        email = "contact@otacos.com",
        socialMedia = listOf(
            SocialMedia(type = "facebook", url = "https://facebook.com/otacos"),
            SocialMedia(type = "instagram", url = "https://instagram.com/otacos")
        )
    ),
        cuisineType = "Asian",
    createdAt = Date(),
    updatedAt = Date(),
)
)









// Function to get a restaurant by its id

fun getRestaurantByID(id: String, restaurants: List<Restaurant>): Restaurant? {
    return restaurants.find { it.restaurantId == id }
}
