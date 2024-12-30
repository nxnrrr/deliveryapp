import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.deliveryapp.R
import com.example.deliveryapp.Restaurant
import com.example.deliveryapp.RestaurantItem
import com.example.deliveryapp.RestaurantModel
import com.example.deliveryapp.Review
import java.util.Date


@Composable
fun RestaurantList(
    restaurantModel: RestaurantModel,
    navController: NavController,
    searchtext : String,
    isLoading: Boolean,
    errorMessage: String?
) {
    val restaurants = restaurantModel.cc.value


    LaunchedEffect(true) {
        restaurantModel.getSearch(searchtext)
    }
    Column (modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFFFF8E1)) // Light background color
        .padding(top=50.dp,start = 10.dp, end = 0.dp)){
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                items(restaurants) { restaurant ->
                    RestaurantItem(restaurant, navController)

                      Spacer(modifier = Modifier.height(8.dp))

                }
                }
            }
        }
    }


fun getRestaurantByID(id: String, restaurants: List<Restaurant>): Restaurant? {
    return restaurants.find { it._id == id }
}
