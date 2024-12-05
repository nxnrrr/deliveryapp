package com.example.deliveryapp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable

fun FilterButtons(filters: List<String>, selectedFilters: Set<String>, onFilterChange: (String) -> Unit) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(filters) { filter ->
            val isSelected = selectedFilters.contains(filter)
            FilterButton(
                text = filter,
                isSelected = isSelected,
                onClick = { onFilterChange(filter) }
            )
        }
    }
}
@Composable
fun FilterButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = if (!isSelected) {
            Modifier
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(20.dp)
                )
        } else Modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFFFFD700) else Color(0xFFFFFBD9),
            contentColor = if (isSelected) Color.Black else Color(0xFF968B7B)
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 0.dp, pressedElevation = 4.dp)
    ) {
        Text(text)
    }

}