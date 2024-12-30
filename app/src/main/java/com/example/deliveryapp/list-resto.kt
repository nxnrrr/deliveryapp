import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
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
import com.example.deliveryapp.Review
import com.example.deliveryapp.SocialMedia
import java.util.Date


@Composable
fun RestaurantList(
    restaurants: List<Restaurant>,
    navController: NavController,
    isLoading: Boolean,
    errorMessage: String?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        if (isLoading) {
            // Affiche un indicateur de chargement pendant le téléchargement
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (errorMessage != null) {
            // Affiche un message d'erreur en cas de problème
            Text(
                text = errorMessage,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
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
}









val review1 = Review(
    reviewId = "review1",
    restaurantId = "restaurant1",
    userId = "user1",
    rating = 4.5,
    comment = "Great food, would definitely recommend!",
    createdAt = Date() // Utilise la date actuelle
)

val review2 = Review(
    reviewId = "review2",
    restaurantId = "restaurant1",
    userId = "user2",
    rating = 3.0,
    comment = "It was okay, but not exceptional.",
    createdAt = Date() // Utilise la date actuelle
)

val review3 = Review(
    reviewId = "review3",
    restaurantId = "restaurant2",
    userId = "user3",
    rating = 5.0,
    comment = "Amazing experience, everything was perfect!",
    createdAt = Date() // Utilise la date actuelle
)

// Liste des reviews
val reviewsList = listOf(review1, review2, review3)





// Function to get a restaurant by its id

fun getRestaurantByID(id: String, restaurants: List<Restaurant>): Restaurant? {
    return restaurants.find { it.restaurantId == id }
}
