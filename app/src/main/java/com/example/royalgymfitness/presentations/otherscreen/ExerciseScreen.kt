package com.example.royalgymfitness.presentations.otherscreen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.royalgymfitness.R
import com.example.royalgymfitness.backend.domain.model.ExerciseModel
import com.example.royalgymfitness.db.domain.model.ExerciseListEntity
import com.example.royalgymfitness.db.presentation.ExerciseDBViewModel
import com.example.royalgymfitness.presentations.home.components.TextComponent
import com.example.royalgymfitness.presentations.nav_graph.Routes
import com.example.royalgymfitness.presentations.splash.ProgressIndicatorAnimation
import com.example.royalgymfitness.ui.theme.DarkBlue
import com.example.royalgymfitness.ui.theme.MainColor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun ExerciseScreen(
    exerciseDbViewModel: ExerciseDBViewModel,
    isFavoriteState: ExerciseState<Boolean>,
    listFavExerciseBundle: ExerciseState<List<ExerciseListEntity>>,
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
                ProgressIndicatorAnimation()
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
                    text = exerciseListState.message,
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
            exerciseDbViewModel,
            listFavExerciseBundle,
            isFavoriteState,
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
    exerciseDbViewModel: ExerciseDBViewModel,
    listFavExerciseBundle: ExerciseState<List<ExerciseListEntity>>,
    isFavoriteState: ExerciseState<Boolean>,
    navController: NavHostController,
    workoutType: String?,
    exerciseName: String?,
    exerciseImage: String?,
    exerciseList: MutableState<List<ExerciseModel>>
) {
    val context = LocalContext.current

    var isFav by rememberSaveable {
        mutableStateOf(false)
    }
//    fav like state handle
    when (listFavExerciseBundle) {
        is ExerciseState.Loading -> {
            CircularProgressIndicator()
        }

        is ExerciseState.Success -> {
            val favExerciseBundle = listFavExerciseBundle.data
            Log.d("@@db", "bundleSize: ${favExerciseBundle.size}")
            if (favExerciseBundle.isNotEmpty()) {
                for (i in favExerciseBundle.indices) {
                    if (favExerciseBundle[i].id == exerciseList.value[0].id) {
                        Log.d("@@db", "LaunchScreenBundle: ${favExerciseBundle[i].id}")
                        Log.d("@@db", "LaunchScreenList: ${exerciseList.value[0].id}")
                        isFav = true
                    }
                }
            }

        }

        is ExerciseState.Error -> {
            val errorMessage = listFavExerciseBundle.message
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }

    }

    //do like or unlike exercise
    when (isFavoriteState) {
        is ExerciseState.Loading -> {
            CircularProgressIndicator()
        }

        is ExerciseState.Success -> {
            val isFavorite = isFavoriteState.data
            if (isFavorite) {
                isFav = true
                Toast.makeText(context, "Saved this Exercise", Toast.LENGTH_SHORT).show()
            } else {
                isFav = false
                Toast.makeText(context, "Remove this Exercise", Toast.LENGTH_SHORT).show()
            }
        }

        is ExerciseState.Error -> {
            val errorMessage = isFavoriteState.message
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()

        }

        else -> {}
    }
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
                ExerciseImage(isFav = isFav, exerciseImage = exerciseImage,
                    exerciseName = exerciseName,
                    exerciseListSize = exerciseList.value.size.toString(),
                    workoutType = workoutType,
                    onFavClick = {
                        if (isFav) {
                            Log.d("@@screen", "LaunchScreenId: ${exerciseList.value[0].id}")
                            exerciseDbViewModel.favRemoveListOfBundleExercises(exerciseList.value[0].id)
                        } else {
                            val exerciseListEntity =
                                ExerciseListEntity(
                                    id = exerciseList.value[0].id,
                                    exerciseName = exerciseName,
                                    bundleThumbnailImage = exerciseImage,
                                    workoutType = workoutType,
                                    exerciseList = exerciseList.value
                                )
                            exerciseDbViewModel.insertBundleFavListOfExercises(exerciseListEntity)
                        }
                    },
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
            items(exerciseList.value.size) {
                ExercisesView(
                    exerciseName = exerciseList.value[it].name,
                    equipmentName = exerciseList.value[it].equipment,
                    exerciseImage = exerciseList.value[it].gifUrl,
                    onClick = {
                        val exerciseDetail = URLEncoder.encode(
                            Json.encodeToString(exerciseList.value[it]),
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
    isFav: Boolean,
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
    ) {
        AsyncImage(
            model = exerciseImage.toString(),
            contentDescription = null, contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier
                .padding(start = 12.dp, top = 12.dp)
                .background(Color.DarkGray, shape = CircleShape)
                .padding(4.dp)
                .size(35.dp)
                .align(Alignment.TopStart)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    onBackClick()
                }
        )
        Icon(
            imageVector = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "",
            modifier = Modifier
                .padding(end = 12.dp, top = 12.dp)
                .background(Color.DarkGray, shape = CircleShape)
                .padding(4.dp)
                .align(Alignment.TopEnd)
                .size(35.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    onFavClick()
                },
            tint = if (isFav) Color.Red else Color.White
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