package com.example.deliveryapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RestaurantScreen() {
    var selectedTab by remember { mutableStateOf("menu") } // État pour l'onglet sélectionné

    Column(modifier = Modifier.fillMaxSize()) {

        TopSection()


        TabsSection(selectedTab) { tab ->
            selectedTab = tab
        }


        when (selectedTab) {
            "menu" -> MenuContent()
            "reviews" -> ReviewsContent()
            "rate" -> RateContent()
        }
    }
}

@Composable
fun TopSection() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Gacoan",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Text(
                text = "Esi, ouedsmar",
                color = Color.Gray,
                fontSize = 14.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "4.3 ★", fontSize = 16.sp)
                Text(text = "60 Items", fontSize = 16.sp)
                Text(text = "145 Commands", fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun TabsSection(selectedTab: String, onTabSelected: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        TabItem("menu", selectedTab == "menu") { onTabSelected("menu") }
        TabItem("reviews", selectedTab == "reviews") { onTabSelected("reviews") }
        TabItem("rate", selectedTab == "rate") { onTabSelected("rate") }
    }
}

@Composable
fun TabItem(tabName: String, isSelected: Boolean, onClick: () -> Unit) {
    Text(
        text = tabName,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() },
        color = if (isSelected) Color.Black else Color.Gray,
        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
    )
}

@Composable
fun MenuContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Featured Items", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(top = 16.dp)) {
            items(4) { index -> // Vous pouvez remplacer avec votre liste d'éléments
                MenuItemCard(
                    name = "Item $index",
                    description = "Description $index",
                    price = "${(index + 1) * 100} DA"
                )
            }
        }
    }
}

@Composable
fun MenuItemCard(name: String, description: String, price: String) {
    Card (
        shape = RoundedCornerShape(8.dp),

        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = name, fontWeight = FontWeight.Bold)
            Text(text = description, color = Color.Gray)
            Text(text = price, fontWeight = FontWeight.Bold, color = Color.Green)
        }
    }
}

@Composable
fun ReviewsContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Reviews Section", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        // Ajoutez ici le contenu des avis
    }
}

@Composable
fun RateContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Rate Section", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        // Ajoutez ici le contenu pour noter
    }
}
