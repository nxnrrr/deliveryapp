package com.example.deliveryapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.deliveryapp.ui.theme.Montserrat


@Composable
fun MenuCard(menuItem: MenuItem, onClick: () -> Unit) {
            Card(

    shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFFFF)
                ),
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .clickable(onClick = onClick)

                .fillMaxWidth()
            ) {

                Row(
                            verticalAlignment = Alignment.CenterVertically,

                ) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(120.dp) // Box size
                            .clip(RoundedCornerShape(25.dp))
                            .background(Color(0xFFFDDB6F)), // Box background color
                        contentAlignment = Alignment.Center // Center the content inside the Box
                    ) {
                        /*Image(
                            painter = painterResource(menuItem.imageUrl), // Replace with your image resource
                            contentDescription = null,
                            modifier = Modifier
                                .size(110.dp) // Image size
                        )*/
                    }


                    Spacer(modifier = Modifier.width(5.dp))

                    // Product Info
                    Column(
                        modifier = Modifier.weight(1f)
                            .padding(top = 8.dp)

                    ) {
                        Text(
                            text = menuItem.name,
                            fontFamily = Montserrat,
                            fontSize = 18.sp,
                            color = Color(0xFF3E2C0F)
                        )

                        Text(
                            text = menuItem.description,

                                    color = Color.Gray,
                            fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "${menuItem.price} DZD",
                            color = Color.Black
                        )

                    }

                    IconButton(onClick = onClick ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Increase",
                            tint = Color.Black
                        )
                    }
                    }




            }
                }




