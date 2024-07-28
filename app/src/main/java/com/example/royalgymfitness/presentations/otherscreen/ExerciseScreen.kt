package com.example.royalgymfitness.presentations.otherscreen

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ehsanmsz.mszprogressindicator.progressindicator.LineScaleProgressIndicator
import com.example.royalgymfitness.R
import com.example.royalgymfitness.backend.domain.model.ExerciseModel
import com.example.royalgymfitness.presentations.home.components.TextComponent
import com.example.royalgymfitness.presentations.nav_graph.Routes
import com.example.royalgymfitness.ui.theme.DarkBlue
import com.example.royalgymfitness.ui.theme.MainColor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun ExerciseScreen(
    navController: NavHostController,
    exerciseName: String?,
    exerciseImage: String?,
    workoutType: String?,
    exerciseListState: ExerciseState<List<ExerciseModel>>
) {

    val exerciseList = remember {
        mutableStateOf<List<ExerciseModel>>(emptyList())
    }

    when (exerciseListState) {
        is ExerciseState.Success -> {

            exerciseList.value = exerciseListState.data
            Log.d(
                "@@home",
                "fetchExerciseData: Success ${exerciseListState.data}"
            )
        }

        is ExerciseState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MainColor),
                contentAlignment = Alignment.Center
            ) {
                LineScaleProgressIndicator(
                    modifier = Modifier,
                    color = Color.White,
                    animationDuration = 800,
                    animationDelay = 200,
                    startDelay = 0
                )
            }
        }

        is ExerciseState.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MainColor),
                contentAlignment = Alignment.Center
            ) {
                TextComponent(
                    text = "Something went wrong 429 .Your API Services Exceed for this month",
                    fontSize = 24.sp
                )
            }
            Log.d("@@home", "fetchExerciseData: ${exerciseListState.message}")
        }

        else -> {
            Unit
        }
    }

    if (exerciseList.value.isNotEmpty()) {
        LaunchScreen(
            navController,
            workoutType,
            exerciseName,
            exerciseImage,
            exerciseList
        )
    }

}

@Composable
fun LaunchScreen(
    navController: NavHostController,
    workoutType: String?,
    exerciseName: String?,
    exerciseImage: String?,
    targetExerciseList: MutableState<List<ExerciseModel>>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            item {
                ExerciseImage(exerciseImage = exerciseImage,
                    exerciseName = exerciseName,
                    exerciseListSize = targetExerciseList.value.size.toString(),
                    workoutType = workoutType,
                    onFavClick = {
                    },
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
            items(targetExerciseList.value.size) {
                ExercisesView(
                    exerciseName = targetExerciseList.value[it].name,
                    equipmentName = targetExerciseList.value[it].equipment,
                    exerciseImage = targetExerciseList.value[it].gifUrl,
                    onClick = {
                        val exerciseDetail = URLEncoder.encode(
                            Json.encodeToString(targetExerciseList.value[it]),
                            StandardCharsets.UTF_8.toString()
                        )
                        Log.d("@@detail", "LaunchScreen: $exerciseDetail")
                        navController.navigate(
                            Routes.ExerciseDetailScreen.passExerciseDetailModel(
                                exerciseDetail
                            )
                        )
                    })

            }
        }
    }
}

@Composable
fun ExerciseImage(
    exerciseImage: String?,
    exerciseName: String?,
    exerciseListSize: String,
    workoutType: String?,
    onBackClick: () -> Unit,
    onFavClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
    ) {
        AsyncImage(
            model = exerciseImage.toString(),
            contentDescription = null, contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "",
            tint = Color.Unspecified,
            modifier = Modifier
                .padding(start = 12.dp, top = 12.dp)
                .size(50.dp)
                .align(Alignment.TopStart)
                .clickable {
                    onBackClick()
                }
        )
        Icon(
            painter = painterResource(id = R.drawable.fav_icon),
            contentDescription = "",
            modifier = Modifier
                .padding(end = 12.dp, top = 12.dp)
                .align(Alignment.TopEnd)
                .size(50.dp)
                .clickable {
                    onFavClick()
                },
            tint = Color.Unspecified
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 300.dp, start = 8.dp, end = 8.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            TextComponent(
                text = "$workoutType Workout",
                fontSize = 12.sp,
                font = R.font.sans_medium,
                color = colorResource(
                    id = R.color.orangeColor
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            TextComponent(text = exerciseName.toString(), fontSize = 24.sp, font = R.font.sans_bold)
            Spacer(modifier = Modifier.height(14.dp))
            TextComponent(
                text = exerciseListSize + ".109 kcl",
                fontSize = 12.sp,
                font = R.font.sans_light
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextComponent(text = "Exercise", fontSize = 16.sp, font = R.font.sans_medium)
            Spacer(modifier = Modifier.height(8.dp))


        }
    }

}

@Composable
fun ExercisesView(
    exerciseName: String,
    equipmentName: String,
    exerciseImage: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkBlue, shape = RoundedCornerShape(8.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = exerciseImage, contentDescription = "image", modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
                    .shadow(2.dp, shape = RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(0.3f),
            ) {
                TextComponent(
                    text = exerciseName,
                    fontSize = 14.sp,
                    font = R.font.sans_regular
                )
                Spacer(modifier = Modifier.height(4.dp))
                TextComponent(
                    text = equipmentName,
                    fontSize = 12.sp,
                    font = R.font.sans_light
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.play),
                contentDescription = "play",
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(35.dp)
            )
        }
    }
}

//@Composable
//fun ExerciseScreenPreview() {
//    RoyalGymFitnessTheme {
//        val navController = NavHostController(LocalContext.current)
//        ExerciseScreen(
//            navController = navController,
//            exerciseName = "exerciseName",
//            exerciseImage = "exerciseImage",
//            targetExerciseListState = ExerciseState()
//        )
//    }
//}