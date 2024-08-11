package com.example.royalgymfitness.presentations.favourite

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.royalgymfitness.R
import com.example.royalgymfitness.db.domain.model.ExerciseListEntity
import com.example.royalgymfitness.presentations.home.components.TextComponent
import com.example.royalgymfitness.ui.theme.DarkBlue

@Composable
fun FavoriteListView(exerciseListEntity: ExerciseListEntity,onClick:()->Unit) {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(250.dp)
            .height(420.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = DarkBlue, contentColor = Color.White),
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AsyncImage(
                    model = exerciseListEntity.bundleThumbnailImage,
                    contentDescription = null, contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )

                TextComponent(
                    text = "${exerciseListEntity.workoutType} Workout",
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                TextComponent(
                    text = "${exerciseListEntity.exerciseName}",
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                TextComponent(
                    text = "${exerciseListEntity.exerciseList?.size} Exercises",
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Button(
                    onClick = {
                        onClick()
                    },
                    modifier = Modifier.width(200.dp),
                    shape = RoundedCornerShape(16.dp),
                    interactionSource = remember {
                        MutableInteractionSource()
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "View", modifier = Modifier.weight(0.8f), style = TextStyle(
                                fontSize = 18.sp,
                                color = Color.White,
                                fontFamily = FontFamily(
                                    Font(R.font.sans_bold)
                                )
                            ), textAlign = TextAlign.Center
                        )
                        Icon(
                            Icons.Default.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.weight(0.2f)
                        )
                    }
                }
            }
        }
    }
}