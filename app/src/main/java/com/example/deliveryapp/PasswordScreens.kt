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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResetPasswordEmailScreen(
    onCodeVerified: () -> Unit,
    onBackToLogin: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var verificationCode by remember { mutableStateOf("") }
    var isCodeSent by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Mock verification code (in real app, this would be sent via email)
    val mockCode = "123456"

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
        text = "Reset Password",
        modifier = Modifier.padding(top = 240.dp, start = 16.dp),
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
    Text(
        text = "Please enter your email address to request a password reset",
        modifier = Modifier.padding(top = 300.dp, start = 16.dp),
        fontSize = 17.sp,
        color = Color(0xFF5B5B5E)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 120.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Email Field
        if (!isCodeSent) {
            var isFocusedEmail by remember { mutableStateOf(false) }
            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = if (isFocusedEmail) Color(0xFFFFB700) else Color(0xFFEEEEEE),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .onFocusChanged { focusState -> isFocusedEmail = focusState.isFocused },
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
                        text = "Enter your Email",
                        color = Color.LightGray,
                        fontSize = 16.sp
                    )
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
//                        val sharedPrefs = context.getSharedPreferences("user_prefs", MODE_PRIVATE)
//                        val savedEmail = sharedPrefs.getString("email", "")

                    // if (email == savedEmail)
                    if (email.isNotEmpty()){
                        // In a real app, you would send the verification code here
                        isCodeSent = true
                        Toast.makeText(context, "Verification code sent to your email", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Email not found", Toast.LENGTH_SHORT).show()
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
                Text(fontSize = 20.sp, text = "Send Code", fontWeight = FontWeight.Bold)
            }
        } else {
            // Verification Code Field
            Text(fontSize = 17.sp, text = "Verification Code", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            var isFocusedCode by remember { mutableStateOf(false) }
            TextField(
                value = verificationCode,
                onValueChange = { verificationCode = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = if (isFocusedCode) Color(0xFFFFB700) else Color(0xFFEEEEEE),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .onFocusChanged { focusState -> isFocusedCode = focusState.isFocused },
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
                        text = "Enter verification code",
                        color = Color.LightGray,
                        fontSize = 16.sp
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (verificationCode == mockCode) {
                        onCodeVerified()
                    } else {
                        Toast.makeText(context, "Invalid verification code", Toast.LENGTH_SHORT).show()
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
                Text(fontSize = 20.sp, text = "Verify Code", fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
        ) {
            Text(
                text = "Back to ",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5B5B5E)
            )
            Text(
                text = "Login",
                fontSize = 18.sp,
                color = Color(0xFFA9411D),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onBackToLogin() }
            )
        }
    }
}

@Composable
fun ResetPasswordNewPasswordScreen(
    onPasswordReset: () -> Unit,
    onBack: () -> Unit
) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val context = LocalContext.current
    var passwordVisible by remember { mutableStateOf(false) }
    val visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()

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
        text = "New Password",
        modifier = Modifier.padding(top = 240.dp, start = 16.dp),
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 120.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // New Password Field
        Text(fontSize = 17.sp, text = "New Password", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        var isFocusedPassword by remember { mutableStateOf(false) }
        TextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            visualTransformation = visualTransformation,
            trailingIcon = {
                val icon = if (passwordVisible)
                    painterResource(id = R.drawable.eye)
                else
                    painterResource(id = R.drawable.hidden)

                IconButton(onClick = {
                    passwordVisible = !passwordVisible
                }, modifier = Modifier.size(24.dp)) {
                    Icon(painter = icon, contentDescription = "Toggle password visibility")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = if (isFocusedPassword) Color(0xFFFFB700) else Color(0xFFEEEEEE),
                    shape = RoundedCornerShape(10.dp)
                )
                .onFocusChanged { focusState -> isFocusedPassword = focusState.isFocused },
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
                    text = "Enter new password",
                    color = Color.LightGray,
                    fontSize = 16.sp
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Confirm Password Field
        Text(fontSize = 17.sp, text = "Confirm Password", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        var isFocusedConfirmPassword by remember { mutableStateOf(false) }
        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            visualTransformation = visualTransformation,
            trailingIcon = {
                val icon = if (passwordVisible)
                    painterResource(id = R.drawable.eye)
                else
                    painterResource(id = R.drawable.hidden)

                IconButton(onClick = {
                    passwordVisible = !passwordVisible
                }, modifier = Modifier.size(24.dp)) {
                    Icon(painter = icon, contentDescription = "Toggle password visibility")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = if (isFocusedConfirmPassword) Color(0xFFFFB700) else Color(0xFFEEEEEE),
                    shape = RoundedCornerShape(10.dp)
                )
                .onFocusChanged { focusState -> isFocusedConfirmPassword = focusState.isFocused },
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
                    text = "Confirm new password",
                    color = Color.LightGray,
                    fontSize = 16.sp
                )
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (newPassword.isNotEmpty() && confirmPassword.isNotEmpty()) {
                    if (newPassword == confirmPassword) {
                        val sharedPrefs = context.getSharedPreferences("user_prefs", MODE_PRIVATE)
                        sharedPrefs.edit()
                            .putString("password", newPassword).apply()
                        Toast.makeText(context, "Password reset successful", Toast.LENGTH_SHORT).show()
                        onPasswordReset()
                    } else {
                        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
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
            Text(fontSize = 20.sp, text = "Reset Password", fontWeight = FontWeight.Bold)
        }
    }
    Spacer(modifier = Modifier.height(16.dp))

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = 630.dp)
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