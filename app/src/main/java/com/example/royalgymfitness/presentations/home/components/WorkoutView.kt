package com.example.royalgymfitness.presentations.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.royalgymfitness.R
import com.example.royalgymfitness.ui.theme.DarkBlue

@Composable
fun WorkoutView(
    imageLink: String,
    exerciseName: String,
    onClick : ()->Unit
) {

    Box(
        modifier = Modifier.clickable {
            onClick()
        }
            .width(200.dp)
            .height(150.dp)
            .background(DarkBlue, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBlue, shape = RoundedCornerShape(8.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .padding(2.dp)
                    .weight(0.8f),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(DarkBlue),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                AsyncImage(
                    model = imageLink,
                    contentDescription = null, contentScale = ContentScale.Crop
                )
            }
            TextComponent(text = exerciseName, fontSize = 14.sp, font = R.font.sans_medium)

        }
    }

}