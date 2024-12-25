package com.example.deliveryapp
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat

@Composable
fun ReviewsScreen(reviews: List<Review>) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
       Spacer(Modifier.height(40.dp))
        Text(text="Reviews",
            fontFamily = FontFamily(Font(R.font.meduim))
        )

        Button (
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp).height(50.dp).width(350.dp).background(Color.White,shape = RoundedCornerShape(30.dp)),
            border = BorderStroke(2.dp, Color.LightGray),
             colors =  ButtonDefaults.buttonColors(

                 contentColor = Color.Black,
                 containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(painter = painterResource(R.drawable.img1),
                    contentDescription = "nan")
                Spacer(Modifier.width(12.dp))
            Text(text = "Write your review",
                fontFamily = FontFamily(Font(R.font.meduim)))
        }}

        Spacer(Modifier.height(12.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(reviews) { review ->
                ReviewItem(review)
            }
        }
    }
}

@Composable
fun ReviewItem(review: Review) {
    Card (
        modifier = Modifier
            .width(350.dp).height(170.dp).clickable {  }
            .border(2.dp, Color(0xFFE0E0E0), shape = RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,

        )

    ) {
     Row(
         modifier = Modifier
             .fillMaxWidth()
             .padding(start=10.dp),
         verticalAlignment = Alignment.CenterVertically,
         horizontalArrangement = Arrangement.Start
     ) {
         Box(
             modifier = Modifier.size(40.dp),
             contentAlignment = Alignment.BottomEnd
         ) {

             Image(
                 painter = painterResource(id = R.drawable.logo2),
                 contentDescription = "Profile Picture",
                 modifier = Modifier
                     .size(40.dp)
                     .clip(CircleShape)
                     .background(Color.LightGray),
                 contentScale = ContentScale.Crop
             )


             Box(
                 modifier = Modifier
                     .size(18.dp)
                     .clip(CircleShape)
                     .background(Color(0xFFFFC107)),
                 contentAlignment = Alignment.TopCenter
             ) {
                 Text(
                     text = "${review.rating}",
                     color = Color.White,
                     fontSize = 8.sp,
                     fontWeight = FontWeight.Bold
                 )
             }
         }


        Spacer(modifier = Modifier.width(10.dp))


        Column (

        ) {
            Text(
                text = "Alyce Lambo",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.regular)),
                color = Color.Black
            )

            Text(
                text = "${ SimpleDateFormat("dd/MM/yyyy").format(review.createdAt)}", // Date
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.regular)),
                color = Color.Gray
            )}}











            Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start=10.dp, end=10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){

            Text(
                text = review.comment,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                fontFamily = FontFamily(Font(R.font.regular)),
                lineHeight = 16.sp,
                fontSize = 14.sp,
                softWrap = true,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify
            )
        }
    }}




