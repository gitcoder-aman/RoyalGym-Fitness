package com.example.royalgymfitness.presentations.otherscreen

import android.os.Build.VERSION.SDK_INT
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.royalgymfitness.R
import com.example.royalgymfitness.backend.domain.model.ExerciseModel
import com.example.royalgymfitness.db.domain.model.ExerciseEntity
import com.example.royalgymfitness.db.presentation.ExerciseDBViewModel
import com.example.royalgymfitness.presentations.home.components.TextComponent
import com.example.royalgymfitness.ui.theme.DarkBlue
import com.example.royalgymfitness.ui.theme.MainColor
import java.util.Locale

@Composable
fun ExerciseDetailScreen(
    exerciseDbViewModel: ExerciseDBViewModel,
    isFavoriteState: ExerciseState<Boolean>,
    exerciseFavListState: ExerciseState<List<ExerciseEntity>>,
    navController: NavHostController,
    exerciseDetailJson: ExerciseModel?
) {
    val context = LocalContext.current

    var isFav by rememberSaveable {
        mutableStateOf(false)
    }

    //fav like state handle
    when(exerciseFavListState){
        is ExerciseState.Loading -> {
            CircularProgressIndicator()
        }
        is ExerciseState.Success -> {
            val exerciseFavList = exerciseFavListState.data
            for (i in exerciseFavList.indices) {
                if (exerciseFavList[i].id == exerciseDetailJson?.id) {
                    isFav = true
                }
            }
        }
        is ExerciseState.Error -> {
            val errorMessage = exerciseFavListState.message
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
                // Exercise detail content goes here
                ExerciseThumbnail(
                    isFav,
                    exerciseImage = exerciseDetailJson?.gifUrl,
                    onBackClick = {
                        // Handle back button click
                        navController.navigateUp()
                    },
                    onFavClick = {
                        // Handle favorite button click
                        if (isFav) {
                            exerciseDbViewModel.favRemoveExercise(exerciseDetailJson?.id.toString())
                        } else {
                            if (exerciseDetailJson != null) {
                                val exerciseEntity = ExerciseEntity(
                                    id = exerciseDetailJson.id,
                                    name = exerciseDetailJson.name,
                                    gifUrl = exerciseDetailJson.gifUrl,
                                    equipment = exerciseDetailJson.equipment,
                                    bodyPart = exerciseDetailJson.bodyPart,
                                    target = exerciseDetailJson.target,
                                    secondaryMuscles = exerciseDetailJson.secondaryMuscles,
                                    instructions = exerciseDetailJson.instructions
                                )
                                exerciseDbViewModel.favExercise(exerciseEntity)
                            }
                        }
                    }
                )
            }
            item {
                ExerciseDetail(exerciseDetailJson)
            }
        }
    }

}

@Composable
fun ExerciseDetail(exerciseDetailJson: ExerciseModel?) {

    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .background(MainColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkBlue, shape = RoundedCornerShape(12.dp))
                .padding(4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            TextComponent(
                text = exerciseDetailJson?.name!!.uppercase().replace("+", " "),
                fontSize = 18.sp,
                font = R.font.sans_extra_bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextComponent(
                text = "FOCUS AREA  :  ${
                    exerciseDetailJson.bodyPart.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                    }.replace("+", " ")
                }",
                fontSize = 16.sp,
                font = R.font.sans_medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextComponent(
                text = "EQUIPMENT  :  ${
                    exerciseDetailJson.equipment.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                    }.replace("+", " ")
                }",
                fontSize = 16.sp,
                font = R.font.sans_medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextComponent(
                text = "TARGET  :  ${
                    exerciseDetailJson.target.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                    }.replace("+", " ")
                }",
                fontSize = 16.sp,
                font = R.font.sans_medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextComponent(
                text = "SECONDARY MUSCLES",
                fontSize = 18.sp,
                font = R.font.sans_extra_bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            for (i in exerciseDetailJson.secondaryMuscles.indices) {
                TextComponent(
                    text = "${"${i + 1}" + "."} ${
                        exerciseDetailJson.secondaryMuscles[i].replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                        }.replace("+", " ")
                    }",
                    fontSize = 16.sp,
                    font = R.font.sans_medium
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextComponent(text = "INSTRUCTIONS", fontSize = 18.sp, font = R.font.sans_extra_bold)
            Spacer(modifier = Modifier.height(8.dp))
            for (i in exerciseDetailJson.instructions.indices) {
                TextComponent(
                    text = "${"${i + 1}" + "."} ${
                        exerciseDetailJson.instructions[i].replace(
                            "+",
                            " "
                        )
                    }",
                    fontSize = 16.sp,
                    font = R.font.sans_medium,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Composable
fun ExerciseThumbnail(
    isFav: Boolean,
    exerciseImage: String?,
    onBackClick: () -> Unit,
    onFavClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        GifImagePlay(
            gifLink = exerciseImage.toString()
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
    }
}

@Composable
fun GifImagePlay(
    modifier: Modifier = Modifier,
    gifLink: String
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()


    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = gifLink).build(),
            imageLoader = imageLoader,
            contentScale = ContentScale.Crop
        ),
        contentDescription = null,
        modifier = modifier
            .shadow(shape = RoundedCornerShape(16.dp), elevation = 4.dp)
            .fillMaxWidth()
            .height(300.dp)
    )
}
