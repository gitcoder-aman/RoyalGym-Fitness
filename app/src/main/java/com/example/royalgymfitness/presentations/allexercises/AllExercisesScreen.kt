package com.example.royalgymfitness.presentations.allexercises

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.royalgymfitness.backend.domain.model.ExerciseModel
import com.example.royalgymfitness.presentations.home.SearchSection
import com.example.royalgymfitness.presentations.home.components.TextComponent
import com.example.royalgymfitness.presentations.nav_graph.Routes
import com.example.royalgymfitness.presentations.otherscreen.ExerciseState
import com.example.royalgymfitness.presentations.otherscreen.ExercisesView
import com.example.royalgymfitness.presentations.splash.ProgressIndicatorAnimation
import com.example.royalgymfitness.ui.theme.MainColor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.Locale

@Composable
fun AllExerciseScreen(
    exerciseListState: ExerciseState<List<ExerciseModel>>,
    navController: NavHostController,
    homeSearchText: String?
) {

    var exerciseList by remember {
        mutableStateOf<List<ExerciseModel>>(emptyList())
    }
    Log.d("@@homeSearchText", "AllExerciseScreen: $homeSearchText")

    when (exerciseListState) {
        is ExerciseState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MainColor),
                contentAlignment = Alignment.Center
            ) {
                ProgressIndicatorAnimation()
            }
        }

        is ExerciseState.Success -> {
            Log.d("@@all_", "AllExerciseScreen: ${exerciseListState.data.size}")
            exerciseList = exerciseListState.data
        }

        is ExerciseState.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MainColor),
                contentAlignment = Alignment.Center
            ) {
                TextComponent(
                    text = exerciseListState.message,
                    fontSize = 24.sp
                )
            }
        }

        else -> {}
    }

    if (exerciseList.isNotEmpty()) {

        var searchText by rememberSaveable {
            mutableStateOf("")
        }
        if (homeSearchText != null && homeSearchText != "{arg_key_search_text}") {
            searchText = homeSearchText
        }
        // Filtered list based on search query
        val filteredItems = remember(searchText) {
            exerciseList.filter {

                if (it.bodyPart.lowercase().contains(searchText.lowercase()) ||
                    it.target.lowercase().contains(searchText.lowercase()) ||
                    it.equipment.lowercase().contains(searchText.lowercase())
                ) {
                    true
                } else {
                    false
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MainColor)
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {

                Log.d("@@search", "AllExerciseScreen: $searchText")
                SearchSection(Modifier.padding(8.dp), searchText) {
                    searchText = it
                }
                Spacer(modifier = Modifier.height(4.dp))

                LazyColumn(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    val itemList = if (searchText.isNotEmpty()) {
                        filteredItems
                    } else {
                        exerciseList
                    }
                    items(itemList.size) {
                        ExercisesView(
                            exerciseName = itemList[it].name.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.ROOT
                                ) else it.toString()
                            },
                            equipmentName = itemList[it].equipment.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.ROOT
                                ) else it.toString()
                            },
                            exerciseImage = itemList[it].gifUrl,
                            onClick = {
                                val exerciseDetail = URLEncoder.encode(
                                    Json.encodeToString(itemList[it]),
                                    StandardCharsets.UTF_8.toString()
                                )
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
    }


}


//@Preview(showBackground = true)
@Composable
fun AllExerciseScreenPreview() {
//    AllExerciseScreen()
}