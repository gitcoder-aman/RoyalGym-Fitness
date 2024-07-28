package com.example.royalgymfitness.presentations.otherscreen

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.example.royalgymfitness.presentations.home.components.TextComponent
import com.example.royalgymfitness.ui.theme.DarkBlue
import com.example.royalgymfitness.ui.theme.MainColor
import java.util.Locale

@Composable
fun ExerciseDetailScreen(navController: NavHostController, exerciseDetailJson: ExerciseModel?) {

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
                    exerciseImage = exerciseDetailJson?.gifUrl,
                    onBackClick = {
                        // Handle back button click
                        navController.navigateUp()
                    },
                    onFavClick = {
                        // Handle favorite button click
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
    exerciseImage: String?,
    onBackClick: () -> Unit,
    onFavClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
    ) {
        GifImagePlay(
            gifLink = exerciseImage.toString()
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
