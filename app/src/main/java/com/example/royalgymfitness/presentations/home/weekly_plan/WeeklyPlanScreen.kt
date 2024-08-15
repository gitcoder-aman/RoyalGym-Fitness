package com.example.royalgymfitness.presentations.home.weekly_plan

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.royalgymfitness.R
import com.example.royalgymfitness.backend.util.WorkoutType
import com.example.royalgymfitness.backend.util.bodyPartMap
import com.example.royalgymfitness.backend.util.mapOfDayExerciseImage
import com.example.royalgymfitness.backend.util.mapOfExerciseListWithDay
import com.example.royalgymfitness.backend.util.targetMap
import com.example.royalgymfitness.presentations.home.components.TextComponent
import com.example.royalgymfitness.presentations.nav_graph.Routes
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

            items(listOfWeek) {
                MessageCardView(
                    day = it,
                    listOfExercises = mapOfExerciseListWithDay[it]!!
                ) { exercise ->

                    Log.d("@@exerciseName", "Screen: $exercise")

                    var workoutType = ""

                    if (exercise.isNotEmpty()) {
                        if (targetMap.containsKey(exercise.lowercase())) {
                            workoutType = WorkoutType.TARGET.toString()
                        } else if (bodyPartMap.containsKey(exercise.lowercase())) {
                            workoutType = WorkoutType.BODY.toString()
                        } else {
                            workoutType = WorkoutType.EQUIPMENT.toString()
                        }
                    }
                    navController.navigate(
                        Routes.ExerciseScreen.passExercise(
                            exerciseName = exercise,
                            exerciseImage = URLEncoder.encode(
                                mapOfDayExerciseImage[exercise.lowercase()],
                                "UTF-8"
                            ),
                            workoutType = workoutType
                        )
                    )
                }
            }

            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    val context = LocalContext.current
                    Spacer(modifier = Modifier.height(16.dp))
                    TextComponent(text = "Follow Me", fontSize = 22.sp)
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.instagram),
                            contentDescription = "instagram",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(35.dp)
                                .clickable {
                                    openSocialMedia(
                                        "https://www.instagram.com/coder.aman/",
                                        context
                                    )
                                }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.linkedin),
                            contentDescription = "linkedin",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(33.dp)
                                .clickable {
                                    openSocialMedia(
                                        "https://www.linkedin.com/in/aman-kumar-4aaa631b5/",
                                        context
                                    )
                                }
                        )
                    }
                }
            }
        }
    }
}

fun openSocialMedia(socialMediaUrl: String, context: Context) {

    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(socialMediaUrl)
    )
    if (socialMediaUrl.contains("instagram")) {
        intent.setPackage("com.instagram.android")
    } else {
        intent.setPackage("com.linkedin.android")
    }
    context.startActivity(intent)

}
