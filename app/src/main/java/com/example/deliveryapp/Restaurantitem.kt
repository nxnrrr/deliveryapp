package com.example.deliveryapp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalDensity

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.deliveryapp.ui.theme.Montserrat

@Composable
fun RestaurantLogo(logoUrl: String) {
    Image(
        painter = rememberImagePainter(logoUrl),
        contentDescription = "Restaurant Logo",
        modifier = Modifier
            .size(70.dp)
            .clip(CircleShape)
            .border(4.dp, Color(0xFFFDDB6F), CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun RestaurantItem(restaurant: Restaurant, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(350.dp)
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFDDB6F))
    ) {
        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Box {
                /*Image(
                    painter = rememberImagePainter(data = restaurant.img),
                    contentDescription = "${restaurant.name} image",
                    modifier = Modifier
                        .size(350.dp, 100.dp),
                    contentScale = ContentScale.Crop
                )*/
                Image(
                    painter = painterResource(id = R.drawable.asi),
                    contentDescription = "${restaurant.name} image",
                    modifier = Modifier
                        .size(350.dp, 100.dp),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = 45.dp)
                ) {
                    // RestaurantLogo(restaurant.logo)
                    Image(
                        painter = painterResource(id = R.drawable.logo1), // Remplacez par votre ressource locale
                        contentDescription = "Restaurant Logo",
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .border(4.dp, Color(0xFFFDDB6F), CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Spacer(modifier = Modifier.height(18.dp))
            Column(
                modifier = Modifier.padding(12.dp)
                    .align(Alignment.CenterHorizontally),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFF3E2C0F)
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Color(0xFFA9411D)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = restaurant.location,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = Montserrat,
                            fontSize = 12.sp,
                            color = Color(0xFF3E2C0F)
                        )
                    )
                }
            }
            Row() {
                /*Row(
                    verticalAlignment = Alignment.CenterVertically,) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = null,
                        Modifier.size(16.dp),
                        tint = Color(0xFFA9411D)
                    )
                    Spacer(modifier = Modifier.width(3.dp))

                    Text(
                        text = "${restaurant.contactInfo.phone}",
                        fontFamily = Montserrat,
                        fontSize = 12.sp,
                        color = Color(0xFF3E2C0F)

                    )
                }
                Spacer(modifier = Modifier.width(20.dp))*/
                /*Row(verticalAlignment = Alignment.CenterVertically,) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        Modifier.size(16.dp),
                        tint = Color(0xFFA9411D)
                    )
                    Spacer(modifier = Modifier.width(3.dp))

                    Text(
                        text = "${restaurant.contactInfo.email}",
                        fontFamily = Montserrat,
                        fontSize = 12.sp,
                        color = Color(0xFF3E2C0F)


                    )
                }*/
            }
            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {

                // Vérification de la présence de Twitter dans les réseaux sociaux
                restaurant.contactInfo.socialMedia.find { it.platform == "Twitter" }?.let {
                    Icon(
                        painter = painterResource(id = R.drawable.twitter),
                        contentDescription = "Twitter Icon",
                        modifier = Modifier.size(16.dp),
                        tint = Color(0xFFA9411D)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                }

                // Vérification de la présence de Facebook dans les réseaux sociaux
                restaurant.contactInfo.socialMedia.find { it.platform == "Facebook" }?.let {
                    Icon(
                        painter = painterResource(id = R.drawable.face),
                        contentDescription = "Facebook Icon",
                        modifier = Modifier.size(16.dp),
                        tint = Color(0xFFA9411D)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                }

                // Vérification de la présence d'Instagram dans les réseaux sociaux
                restaurant.contactInfo.socialMedia.find { it.platform == "Instagram" }?.let {
                    Icon(
                        painter = painterResource(id = R.drawable.insta),
                        contentDescription = "Instagram Icon",
                        modifier = Modifier.size(16.dp),
                        tint = Color(0xFFA9411D)
                    )
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp), // Espacement entre les Box
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                // Pour chaque type de cuisine dans la liste cuisineType
                restaurant.cuisineType.forEach { cuisine ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White)
                            .padding(vertical = 3.dp, horizontal = 8.dp)
                    ) {
                        Text(
                            text = "$cuisine food",
                            fontFamily = Montserrat,
                            fontSize = 12.sp,
                            color = Color(0xFF3E2C0F)
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(5.dp))


        }
    }


}

