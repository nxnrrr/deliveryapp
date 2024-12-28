package com.example.deliveryapp

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddingProfilePicture(
    onAddingPicture: () -> Unit,
    onBack: () -> Unit
) {
    var address by remember { mutableStateOf("") }
    val context = LocalContext.current
    val image = painterResource(id = R.drawable.header)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 0.dp)
    ) {
        Image(
            painter = image,
            contentDescription = "top background",
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp),
            contentScale = ContentScale.Crop,
        )
    }

    Text(
        text = "One final \nstep..",
        modifier = Modifier.padding(top = 240.dp, start = 16.dp),
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )

    Text(
        text = "Please add a profile picture and a delivery address, don't worry you can change them later!",
        modifier = Modifier.padding(top = 330.dp, start = 16.dp),
        fontSize = 17.sp,
        color = Color(0xFF5B5B5E)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 330.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            fontSize = 17.sp,
            text = "Address",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(6.dp))

        var isFocusedAddress by remember { mutableStateOf(false) }
        TextField(
            value = address,
            onValueChange = { address = it },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = if (isFocusedAddress) Color(0xFFFFB700) else Color(0xFFEEEEEE),
                    shape = RoundedCornerShape(10.dp)
                )
                .onFocusChanged { focusState -> isFocusedAddress = focusState.isFocused },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    text = "Enter your Address",
                    color = Color.LightGray,
                    fontSize = 16.sp
                )
            })
        // Profile Picture Selection
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .offset(y = 25.dp)
                .size(120.dp)
                .border(4.dp, Color.Black, CircleShape)
                .clip(CircleShape)
                .clickable { /* your click action */ }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Image(
                    painter = painterResource(id = R.drawable.avatar),  // Add your image to drawable folder
                    contentDescription = "Profile background",
                    modifier = Modifier.size(120.dp)  // This will ensure the image fills the circle
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.camera),
            contentDescription = "Add photo",
            modifier = Modifier
                .size(50.dp)
                .offset(x = 30.dp, y = (-10).dp))


        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (address.isNotEmpty()) {
                    val sharedPrefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                    sharedPrefs.edit()
                        .putString("address", address)
                        .apply()
                    onAddingPicture()
                } else {
                    Toast.makeText(context, "Please enter your address", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFB700),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(28.5.dp),
        ) {
            Text(fontSize = 20.sp, text = "SAVE", fontWeight = FontWeight.Bold)
        }


        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
        ) {
            Text(
                text = "Go Back",
                fontSize = 18.sp,
                color = Color(0xFFA9411D),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onBack() }
            )
        }
    }
}