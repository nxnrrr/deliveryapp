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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onLogin: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
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
    Text(text = "Sign up",modifier = Modifier.padding(top = 240.dp, start = 16.dp),fontSize = 35.sp,fontWeight = FontWeight.Bold,color = Color.Black)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 140.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Center // Ensure it starts at the top
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

        Spacer(modifier = Modifier.height(16.dp)) // Add space between fields

        // Email Field Section
        Text(fontSize = 17.sp, text = "E-mail", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

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

        Spacer(modifier = Modifier.height(16.dp)) // Add space between fields

        // Password Field Section
        Text(fontSize = 17.sp, text = "Password", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        var isFocusedPassword by remember { mutableStateOf(false) }

        TextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = visualTransformation, // Apply the visual transformation based on visibility
            trailingIcon = {
                val icon = if (passwordVisible)
                    painterResource(id = R.drawable.eye) // Your "eye-open" icon
                else
                    painterResource(id = R.drawable.hidden) // Your "eye-closed" icon

                IconButton(onClick = {
                    passwordVisible = !passwordVisible // Toggle visibility state
                }, modifier = Modifier.size(24.dp)) {
                    Icon(painter = icon, contentDescription = "Toggle password visibility")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password, // Use password keyboard
                imeAction = ImeAction.Done // Action button in the keyboard
            ),
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
                    text = "Enter your password",
                    color = Color.LightGray,
                    fontSize = 16.sp
                )
            }
        )


        Button(
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()) {
                    val sharedPrefs =
                        context.getSharedPreferences("user_prefs", MODE_PRIVATE)
                    sharedPrefs.edit()
                        .putString("password", password)
                        .putString("email", email)
                        .putString("name", name)
                        .apply()
                    onRegisterSuccess()

                } else {
                    Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT)
                        .show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 27.dp)
                .padding(horizontal = 60.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFB700), // Background color
                contentColor = Color.White  // Text color inside the button
            ),
            shape = RoundedCornerShape(28.5.dp),
        ) {
            Text(fontSize = 20.sp, text = "Sign Up", fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 20.dp)
                .padding(top = 10.dp),
        ) {
            Text(
                text = "Already have an account? ",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5B5B5E)
            )
            Text(
                text = "Login",
                fontSize = 18.sp,
                color = Color(0xFFA9411D),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onLogin() } // Clickable action for "Login"
            )
        }
    }
    Button(
        onClick = { /* Google sign up logique wela maembalich */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 60.dp)
            .offset(y = 780.dp)
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White, // Button background color
            contentColor = Color.Black    // Default text/icon color
        ),
        shape = RoundedCornerShape(28.dp), // Rounded edges for the button
        elevation = ButtonDefaults.buttonElevation(5.dp) // Shadow effect
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_google_logo),
                contentDescription = "Google logo",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp) // Icon size
            )
            Spacer(modifier = Modifier.width(8.dp)) // Space between icon and text
            Text(
                text = "Google",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = 730.dp)
            .padding(horizontal = 16.dp), // Adjusts spacing from screen edges
        verticalAlignment = Alignment.CenterVertically, // Centers the text and dividers vertically
        horizontalArrangement = Arrangement.SpaceBetween // Ensures spacing between elements
    ) {
        HorizontalDivider(
            modifier = Modifier
                .weight(1f), // Makes the divider take equal space on both sides
            thickness = 1.dp,
            color = Color.Gray.copy(alpha = 0.5f) // Light gray color
        )

        Text(
            text = "Sign up with",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 8.dp) // Space around the text
        )

        HorizontalDivider(
            modifier = Modifier
                .weight(1f),
            thickness = 1.dp,
            color = Color.Gray.copy(alpha = 0.5f)
        )
    }
}

@Composable
fun AuthScreen(
    onLoginSuccess: () -> Unit,
    onNoAccount: () -> Unit,
    onForgotPassword: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    // toggle to hide/show mdp
    var passwordVisible by remember { mutableStateOf(false) }

    // Toggle between hidden (PasswordVisualTransformation) and plain text
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
        )}
    Text(text = "Sign in",modifier = Modifier.padding(top = 240.dp, start = 16.dp),fontSize = 35.sp,fontWeight = FontWeight.Bold,color = Color.Black)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 120.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Center) {
        Text(fontSize = 17.sp, text = "E-mail",fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))
        var isFocusedEmail by remember { mutableStateOf(false) }

        TextField(
            value = email,
            onValueChange = { email = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = if (isFocusedEmail) Color(0xFFFFB700) else Color(0xFFEEEEEE), // Change border color based on focus
                    shape = RoundedCornerShape(10.dp)
                )
                .onFocusChanged { focusState ->
                    isFocusedEmail = focusState.isFocused
                },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,           // Black text when focused
                unfocusedTextColor = Color.Black,         // Black text when unfocused
                focusedContainerColor = Color.White,      // White background when focused
                unfocusedContainerColor = Color.White,    // White background when unfocused
                focusedIndicatorColor = Color.Transparent, // No underline when focused
                unfocusedIndicatorColor = Color.Transparent,
            ),
            placeholder = {
                Text(
                    text = "Enter your Email",
                    color = Color.LightGray, // Light gray placeholder color
                    fontSize = 16.sp         // Optional: Adjust font size for placeholder
                )
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth() // Fill the width of the screen
        ) {
            Text(fontSize = 17.sp, text = "Password",fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(8.dp))
        var isFocusedPassword by remember { mutableStateOf(false) }
        TextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = visualTransformation, // Apply the visual transformation based on visibility
            trailingIcon = {
                val icon = if (passwordVisible)
                    painterResource(id = R.drawable.eye) // Your "eye-open" icon
                else
                    painterResource(id = R.drawable.hidden) // Your "eye-closed" icon

                IconButton(onClick = {
                    passwordVisible = !passwordVisible // Toggle visibility state
                },
                    modifier = Modifier.size(24.dp)) {
                    Icon(painter = icon, contentDescription = "Toggle password visibility")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password, // Use password keyboard
                imeAction = ImeAction.Done // Action button in the keyboard
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = if (isFocusedPassword) Color(0xFFFFB700) else Color(0xFFEEEEEE), // Change border color based on focus
                    shape = RoundedCornerShape(10.dp)
                )
                .onFocusChanged { focusState ->
                    isFocusedPassword = focusState.isFocused
                },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,           // Black text when focused
                unfocusedTextColor = Color.Black,         // Black text when unfocused
                focusedContainerColor = Color.White,      // White background when focused
                unfocusedContainerColor = Color.White,    // White background when unfocused
                focusedIndicatorColor = Color.Transparent, // No underline when focused
                unfocusedIndicatorColor = Color.Transparent),
            placeholder = {
                Text(
                    text = "Enter your password",
                    color = Color.LightGray, // Light gray placeholder color
                    fontSize = 16.sp         // Optional: Adjust font size for placeholder
                )
            }

        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            fontSize = 17.sp,
            text = "Forgot password?",
            fontWeight = FontWeight.Bold,
            color = Color(0xFFA9411D),
            modifier = Modifier
                .padding(bottom = 50.dp)
                .clickable { onForgotPassword() })

        Button(
            onClick = {
                val sharedPrefs =
                    context.getSharedPreferences("user_prefs", MODE_PRIVATE)
                val savedEmail = sharedPrefs.getString("email", "")
                val savedPassword = sharedPrefs.getString("password", "")
                if (email == savedEmail && password == savedPassword) {
                    onLoginSuccess()
                } else {
                    Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 50.dp)
                .padding(horizontal = 60.dp)
                .height(60.dp),


            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFB700), // Background color
                contentColor = Color.White  // Text color inside the button
            ),
            shape = RoundedCornerShape(28.5.dp),

            ) {
            Text(fontSize = 20.sp, text = "Sign in", fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 40.dp)
                .padding(top = 10.dp),
        ) {
            Text(
                text = "Don’t have an account? ",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5B5B5E)
            )
            Text(
                text = "Sign Up",
                fontSize = 18.sp,
                color = Color(0xFFA9411D),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onNoAccount() } // Clickable action for "Sign Up"
            )

        }


    }
    Button(
        onClick = { /* Google sign in logique wela maembalich */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 60.dp)
            .offset(y = 780.dp)
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White, // Button background color
            contentColor = Color.Black    // Default text/icon color
        ),
        shape = RoundedCornerShape(28.dp), // Rounded edges for the button
        elevation = ButtonDefaults.buttonElevation(5.dp) // Shadow effect
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_google_logo), // Replace with your Google icon resource
                contentDescription = "Google logo",
                tint = Color.Unspecified, // Ensure the original icon color is preserved
                modifier = Modifier.size(24.dp) // Icon size
            )
            Spacer(modifier = Modifier.width(8.dp)) // Space between icon and text
            Text(
                text = "Google",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = 730.dp)
            .padding(horizontal = 16.dp), // Adjusts spacing from screen edges
        verticalAlignment = Alignment.CenterVertically, // Centers the text and dividers vertically
        horizontalArrangement = Arrangement.SpaceBetween // Ensures spacing between elements
    ) {
        HorizontalDivider(
            modifier = Modifier
                .weight(1f), // Makes the divider take equal space on both sides
            thickness = 1.dp,
            color = Color.Gray.copy(alpha = 0.5f) // Light gray color
        )

        Text(
            text = "Sign in with",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 8.dp) // Space around the text
        )

        HorizontalDivider(
            modifier = Modifier
                .weight(1f),
            thickness = 1.dp,
            color = Color.Gray.copy(alpha = 0.5f)
        )
    }
}

@Composable
fun AccountVerificationScreen(
    onCodeSent: () -> Unit,
    onBack: () -> Unit
) {
    var phoneNumber by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 0.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.header),
            contentDescription = "top background",
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp),
            contentScale = ContentScale.Crop,
        )
    }

    Text(
        text = "Account \nVerification",
        modifier = Modifier.padding(top = 240.dp, start = 16.dp),
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )

    Text(
        text = "Please enter your phone number. We will send you a verification code.",
        modifier = Modifier.padding(top = 330.dp, start = 16.dp),
        fontSize = 17.sp,
        color = Color(0xFF5B5B5E)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 380.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        var isFocused by remember { mutableStateOf(false) }

        TextField(
            value = phoneNumber,
            onValueChange = {
                    input ->
                // regex to filter only numeric input
                if (input.matches(Regex("^\\d*\$"))) {
                    phoneNumber = input
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = if (isFocused) Color(0xFFFFB700) else Color(0xFFEEEEEE),
                    shape = RoundedCornerShape(10.dp)
                )
                .onFocusChanged { focusState -> isFocused = focusState.isFocused },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = {
                Text(
                    text = "Enter your phone number",
                    color = Color.LightGray,
                    fontSize = 16.sp
                )
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (phoneNumber.isNotEmpty()) {
                    // Here you would typically send the verification code
                    Toast.makeText(context, "Verification code sent!", Toast.LENGTH_SHORT).show()
                    onCodeSent()
                }
                else {
                    Toast.makeText(context, "Please enter your phone number", Toast.LENGTH_SHORT).show()
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
            shape = RoundedCornerShape(28.5.dp)
        ) {
            Text(fontSize = 20.sp, text = "SEND CODE", fontWeight = FontWeight.Bold)
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

@Composable
fun VerificationCodeScreen(
    onVerificationSuccess: () -> Unit,
    onBack: () -> Unit
) {
    var verificationCode by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 0.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.header),
            contentDescription = "top background",
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp),
            contentScale = ContentScale.Crop,
        )
    }

    Text(
        text = "Enter \nVerification Code",
        modifier = Modifier.padding(top = 240.dp, start = 16.dp),
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )

    Text(
        text = "Please enter the verification code we sent to your phone number.",
        modifier = Modifier.padding(top = 330.dp, start = 16.dp),
        fontSize = 17.sp,
        color = Color(0xFF5B5B5E)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 380.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        var isFocused by remember { mutableStateOf(false) }

        TextField(
            value = verificationCode,
            onValueChange = {
                input ->
                if (input.matches(Regex("^\\d*\$"))) {
                    verificationCode = input
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = if (isFocused) Color(0xFFFFB700) else Color(0xFFEEEEEE),
                    shape = RoundedCornerShape(10.dp)
                )
                .onFocusChanged { focusState -> isFocused = focusState.isFocused },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = {
                Text(
                    text = "Enter verification code",
                    color = Color.LightGray,
                    fontSize = 16.sp
                )
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (verificationCode.isNotEmpty()) {
                    // Here you would typically verify the code
                    Toast.makeText(context, "Verification successful!", Toast.LENGTH_SHORT).show()
                    onVerificationSuccess()
                }
                else {
                    Toast.makeText(context, "Please enter the verification code", Toast.LENGTH_SHORT).show()
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
            shape = RoundedCornerShape(28.5.dp)
        ) {
            Text(fontSize = 20.sp, text = "VERIFY", fontWeight = FontWeight.Bold)
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