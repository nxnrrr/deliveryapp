package com.example.deliveryapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class TrackingStatus(
    val id: Int,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    val isCurrentStep: Boolean = false
)

@Composable
fun TrackingScreen(
    onBackPress: () -> Unit,
    deliveryDate: String = "Nov 15 GMT +01:00",
    currentStep: Int = 0,
    address: String = "Higher National School of Computer Science ESI"
) {
    val trackingSteps = listOf(
        TrackingStatus(
            0,
            "Delivery update",
            "Your order has been successfully received\nand is being processed.",
            isCompleted = currentStep >= 0,
            isCurrentStep = currentStep == 0
        ),
        TrackingStatus(
            1,
            "Order in Preparation",
            "Your order has been successfully received\nand is being processed.",
            isCompleted = currentStep >= 1,
            isCurrentStep = currentStep == 1
        ),
        TrackingStatus(
            2,
            "Order Shipping",
            "Your order has been successfully received\nand is being processed.",
            isCompleted = currentStep >= 2,
            isCurrentStep = currentStep == 2
        ),
        TrackingStatus(
            3,
            "Order Delivered",
            "Your order has been successfully received\nand is being processed.",
            isCompleted = currentStep >= 3,
            isCurrentStep = currentStep == 3
        )
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFFBE6)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackPress) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
                Text(
                    text = "Tracking",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                IconButton(onClick = { /* Handle menu click */ }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More options")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Delivery Date
            Text(
                text = "Delivery: $deliveryDate",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Tracking Steps
            trackingSteps.forEachIndexed { index, status ->
                TrackingStep(
                    status = status,
                    isLastItem = index == trackingSteps.lastIndex
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Delivery Address
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = "Location",
                    tint = Color(0xFFFF6B6B),
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = address,
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Composable
fun TrackingStep(
    status: TrackingStatus,
    isLastItem: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // Status indicator and line
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(24.dp)
        ) {
            // Circle indicator
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        when {
                            status.isCurrentStep -> Color(0xFFFFB700)
                            status.isCompleted -> Color(0xFFFFB700)
                            else -> Color.LightGray
                        },
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (status.isCompleted) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(Color.White, CircleShape)
                    )
                }
            }

            // Connecting line
            if (!isLastItem) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(50.dp)
                        .background(
                            if (status.isCompleted) Color(0xFFFFB700) else Color.LightGray
                        )
                )
            }
        }

        // Status text
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = status.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = if (status.isCompleted || status.isCurrentStep) Color.Black else Color.Gray
            )
            Text(
                text = status.description,
                fontSize = 14.sp,
                color = Color.Gray,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
fun TrackingScreenWithViewModel(
    viewModel: TrackingViewModel,
    onBackPress: () -> Unit
) {
    val currentStep by viewModel.currentStep.collectAsState()

    TrackingScreen(
        onBackPress = onBackPress,
        currentStep = currentStep
    )
}