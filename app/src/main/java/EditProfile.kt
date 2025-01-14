package com.example.deliveryapp

import android.app.Activity.MODE_PRIVATE
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen(
    onBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    val context = LocalContext.current

    Text(text = "Edit your Profile",modifier = Modifier
        .padding(top = 100.dp, start = 80.dp),
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black)


    Box(
        modifier = Modifier
            .offset(x=140.dp,y = 180.dp)
            .size(120.dp)
            .border(4.dp, Color.Black, CircleShape)
            .clip(CircleShape)
            .clickable { /* your click action */ }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "Profile background",
                modifier = Modifier.size(120.dp)
            )
        }
    }
    Image(
        painter = painterResource(id = R.drawable.camera),
        contentDescription = "Add photo",
        modifier = Modifier
            .size(50.dp)
            .offset(x = 200.dp, y = (260).dp))


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 140.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(fontSize = 17.sp, text = "Full name", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        var isFocusedName by remember { mutableStateOf(false) }

        TextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = if (isFocusedName) Color(0xFFFFB700) else Color(0xFFEEEEEE), // Change border color based on focus
                    shape = RoundedCornerShape(10.dp)
                )
                .onFocusChanged { focusState -> isFocusedName = focusState.isFocused },
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
                    text = "Enter your Full name",
                    color = Color.LightGray,
                    fontSize = 16.sp
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(fontSize = 17.sp, text = "Delivery Address", fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))

        var isFocusedAddress by remember { mutableStateOf(false) }

        TextField(
            value = address,
            onValueChange = { address = it },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = if (isFocusedAddress) Color(0xFFFFB700) else Color(0xFFEEEEEE), // Change border color based on focus
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
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(fontSize = 17.sp, text = "Phone Number", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))


        var isFocusedPhone by remember { mutableStateOf(false) }
        TextField(
            value = phoneNumber,
            onValueChange = { input ->
                if (input.matches(Regex("^\\d*\$"))) {
                    phoneNumber = input
                } },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = if (isFocusedPhone) Color(0xFFFFB700) else Color(0xFFEEEEEE), // Change border color based on focus
                    shape = RoundedCornerShape(10.dp)
                )
                .onFocusChanged { focusState -> isFocusedPhone = focusState.isFocused },
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
                    text = "Enter your phone number",
                    color = Color.LightGray,
                    fontSize = 16.sp
                )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        }

            Button(
            onClick = {
                if (name.isNotEmpty() && phoneNumber.isNotEmpty() && address.isNotEmpty()) {
                    val sharedPrefs =
                        context.getSharedPreferences("user_prefs", MODE_PRIVATE)
                    sharedPrefs.edit()
                        .putString("phone", phoneNumber)
                        .putString("address", address)
                        .putString("name", name)
                        .apply()
                    onBack()
                } else {
                    Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT)
                        .show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 700.dp)
                .padding(horizontal = 60.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFB700), // Background color
                contentColor = Color.White  // Text color inside the button
            ),
            shape = RoundedCornerShape(28.5.dp),
        ) {
            Text(fontSize = 20.sp, text = "Save", fontWeight = FontWeight.Bold)
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 760.dp)
                .padding(top = 10.dp),
            ){
                Text(
                    text = "Go Back",
                    fontSize = 18.sp,
                    color = Color(0xFFA9411D),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onBack() }
                )
            }}