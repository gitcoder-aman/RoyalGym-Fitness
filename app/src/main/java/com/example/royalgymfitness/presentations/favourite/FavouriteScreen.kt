package com.example.royalgymfitness.presentations.favourite

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.royalgymfitness.backend.domain.model.ExerciseModel
import com.example.royalgymfitness.db.domain.model.ExerciseEntity
import com.example.royalgymfitness.db.domain.model.ExerciseListEntity
import com.example.royalgymfitness.presentations.home.components.TextComponent
import com.example.royalgymfitness.presentations.nav_graph.Routes
import com.example.royalgymfitness.presentations.otherscreen.ExerciseState
import com.example.royalgymfitness.presentations.otherscreen.ExercisesView
import com.example.royalgymfitness.ui.theme.MainColor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.Locale

@Composable
fun FavouriteScreen(
    listFavExerciseBundle: ExerciseState<List<ExerciseListEntity>>,
    listFavExercise: ExerciseState<List<ExerciseEntity>>,
    navController: NavHostController
) {

    var listFavExerciseList = remember {
        mutableStateOf<List<ExerciseEntity>>(emptyList())
    }
    var listFavExerciseBundleList = remember {
        mutableStateOf<List<ExerciseListEntity>>(emptyList())
    }
    when (listFavExercise) {
        is ExerciseState.Success -> {
            listFavExerciseList.value = listFavExercise.data
        }

        is ExerciseState.Error -> {
            listFavExercise.message
        }

        else -> {}
    }
    when (listFavExerciseBundle) {
        is ExerciseState.Success -> {
            listFavExerciseBundleList.value = listFavExerciseBundle.data
        }

        is ExerciseState.Error -> {
            listFavExerciseBundle.message
        }

        else -> {}
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // First item: Header and LazyRow for Exercise List Favorite
            item {
                TextComponent(text = "Exercise List Favorite", fontSize = 24.sp)
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    if (listFavExerciseBundleList.value.isNotEmpty()) {
                        items(listFavExerciseBundleList.value.size) {
                            FavoriteListView(listFavExerciseBundleList.value[it]) {
                                Log.d("@@fav", "FavouriteScreen: ${listFavExerciseBundleList.value[it].bundleThumbnailImage}")
                                navController.navigate(
                                    Routes.ExerciseScreen.passExercise(
                                        exerciseImage = URLEncoder.encode(
                                            Json.encodeToString(listFavExerciseBundleList.value[it].bundleThumbnailImage),
                                            StandardCharsets.UTF_8.toString()
                                        ),
                                        exerciseName = listFavExerciseBundleList.value[it].exerciseName!!,
                                        workoutType = listFavExerciseBundleList.value[it].workoutType!!
                                    )
                                )
                            }
                        }
                    }
                }
            }

            // Second item: Spacer to separate sections
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Third item: Header and LazyColumn for Exercises Favorite
            item {
                TextComponent(text = "Exercises Favorite", fontSize = 24.sp)
            }

            if (listFavExerciseList.value.isNotEmpty()) {
                // Exercise list as separate items in the main LazyColumn
                items(listFavExerciseList.value.size) {
                    ExercisesView(
                        exerciseName = listFavExerciseList.value[it].name.capitalize(Locale.ROOT),
                        equipmentName = listFavExerciseList.value[it].equipment.capitalize(Locale.ROOT),
                        exerciseImage = listFavExerciseList.value[it].gifUrl
                    ) {

                        val exerciseModel = ExerciseModel(
                            id = listFavExerciseList.value[it].id,
                            name = listFavExerciseList.value[it].name,
                            gifUrl = listFavExerciseList.value[it].gifUrl,
                            equipment = listFavExerciseList.value[it].equipment,
                            target = listFavExerciseList.value[it].target,
                            secondaryMuscles = listFavExerciseList.value[it].secondaryMuscles,
                            bodyPart = listFavExerciseList.value[it].bodyPart,
                            instructions = listFavExerciseList.value[it].instructions
                        )
                        val exerciseDetail = URLEncoder.encode(
                            Json.encodeToString(exerciseModel),
                            StandardCharsets.UTF_8.toString()
                        )
                        navController.navigate(
                            Routes.ExerciseDetailScreen.passExerciseDetailModel(
                                exerciseDetail
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp)) // Add space between items
                }
            }
        }
    }
}
