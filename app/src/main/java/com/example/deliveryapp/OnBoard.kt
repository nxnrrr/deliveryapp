package com.example.deliveryapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SplashScreenOne( onNext: () -> Unit,
                     onSkip: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8E1)), // Couleur de fond jaune clair
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image principale
        Image(
            painter = painterResource(id = R.drawable.img1),
            contentDescription = "Illustration",
            modifier = Modifier
                .size(600.dp) // Taille ajustée
                .padding(bottom = 16.dp) // Espacement réduit avec le texte
        )

        // Texte principal
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .offset(y = -(120).dp)
        ) {

            Text(
                text = "On Demand &\nRuntime Location",
                style = TextStyle(
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333),
                ),
            )

            Spacer(modifier = Modifier.height(16.dp)) // Espacement réduit

            Text(
                text = "Pickup from your location at your preferred date and time",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFFB03060), // Rouge brique
                ),
            )
        }
        Spacer(modifier = Modifier.height(50.dp)) // Espacement réduit


        // Boutons
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(bottom = 10.dp)
                .offset(y = -(100).dp)
        ) {
            Button(
                onClick = { onSkip() },
                modifier = Modifier
                    .height(51.dp)
                    .width(150.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(28.dp) // Boutons arrondis
            ) {
                Text(text = "Skip tour")
            }

            Button(
                onClick = { onNext() },
                modifier = Modifier
                    .height(51.dp)
                    .width(150.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFBE00), // Jaune orangé
                    contentColor = Color(0xFFB03060) // Rouge brique
                ),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(text = "Get started")
            }
        }
    }
}

@Composable
fun SplashScreenTwo(onNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8E1))
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        // Image (vous devrez remplacer R.drawable.image_livraison par votre ressource)
        Image(painter = painterResource(id = R.drawable.img2), contentDescription = "Image de livraison", modifier = Modifier.size(600.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .offset(y = -(120).dp)
        ) {

            Text(
                text = "Live Track \nYour Shipement",
                style = TextStyle(
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333),
                ),
            )

            Spacer(modifier = Modifier.height(16.dp)) // Espacement réduit

            Text(
                text = "See a real time viex of your courier on the man on the day of the delivery",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFFB03060), // Rouge brique
                ),
            )
        }
        Spacer(modifier = Modifier.height(50.dp)) // Espacement réduit


        // Boutons
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(bottom = 10.dp)
                .offset(y = -(100).dp)
        ) {


            Button(
                onClick = { onNext() },
                modifier = Modifier
                    .height(51.dp)
                    .width(150.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFBE00), // Jaune orangé
                    contentColor = Color(0xFFB03060) // Rouge brique
                ),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(text = "NEXT")
            }
        }
    }
}

@Composable
fun SplashScreenThree( onNext: () -> Unit ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8E1))
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        // Image (vous devrez remplacer R.drawable.image_livraison par votre ressource)
        Image(painter = painterResource(id = R.drawable.img3), contentDescription = "Image de livraison", modifier = Modifier.size(600.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .offset(y = -(120).dp)
        ) {

            Text(
                text = "Live Track \nYour Shipement",
                style = TextStyle(
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333),
                ),
            )

            Spacer(modifier = Modifier.height(16.dp)) // Espacement réduit

            Text(
                text = "See a real time viex of your courier on the man on the day of the delivery",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFFB03060), // Rouge brique
                ),
            )
        }
        Spacer(modifier = Modifier.height(50.dp)) // Espacement réduit


        // Boutons
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(bottom = 10.dp)
                .offset(y = -(100).dp)
        ) {

            Button(
                onClick = { onNext() },
                modifier = Modifier
                    .height(51.dp)
                    .width(150.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFBE00), // Jaune orangé
                    contentColor = Color(0xFFB03060) // Rouge brique
                ),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(text = "NEXT")
            }
        }
    }
}