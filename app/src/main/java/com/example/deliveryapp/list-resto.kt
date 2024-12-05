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


