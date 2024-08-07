package com.example.royalgymfitness.presentations.home.weekly_plan

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.royalgymfitness.R
import com.example.royalgymfitness.backend.util.WorkoutType
import com.example.royalgymfitness.backend.util.bodyPartMap
import com.example.royalgymfitness.backend.util.targetMap
import com.example.royalgymfitness.presentations.home.components.TextComponent
import com.example.royalgymfitness.presentations.nav_graph.Routes
import com.example.royalgymfitness.ui.theme.DarkBlue
import com.example.royalgymfitness.ui.theme.MainColor
import java.net.URLEncoder

@Composable
fun WeeklyPlanScreen(navController: NavHostController) {

    // Your weekly plan content goes here
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor)
    ) {

        LazyColumn(
            modifier = Modifier
                .background(MainColor)
                .padding(5.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            val listOfWeek = listOf(
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday"
            )
            val listOfExercises = listOf("Abs", "Biceps", "Triceps")

            items(listOfWeek) { day ->
                MessageCardView(
                    day = day,
                    listOfExercises = listOfExercises
                ) { exercise ->

                    navController.navigate(
                        Routes.ExerciseScreen.passExercise(
                            exerciseName = exercise,
                            exerciseImage = URLEncoder.encode(
                                targetMap[exercise.lowercase()],
                                "UTF-8"
                            ),
                            workoutType = WorkoutType.TARGET.toString()
                        )
                    )
                }
            }
        }
    }
}