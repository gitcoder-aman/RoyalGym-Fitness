package com.example.royalgymfitness.presentations.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.royalgymfitness.R
import com.example.royalgymfitness.backend.util.WorkoutType
import com.example.royalgymfitness.backend.util.bodyPartMap
import com.example.royalgymfitness.backend.util.equipmentMap
import com.example.royalgymfitness.backend.util.targetMap
import com.example.royalgymfitness.presentations.home.components.MonitoringComponent
import com.example.royalgymfitness.presentations.home.components.PagerSlider
import com.example.royalgymfitness.presentations.home.components.TextComponent
import com.example.royalgymfitness.presentations.home.components.WorkoutView
import com.example.royalgymfitness.presentations.nav_graph.Routes
import com.example.royalgymfitness.ui.theme.DarkBlue
import com.example.royalgymfitness.ui.theme.MainColor
import java.net.URLEncoder
import java.util.Locale

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor)
    ) {

        Column(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            HeaderHomeScreen()
            Spacer(modifier = Modifier.height(12.dp))
            SearchSection()
            Spacer(modifier = Modifier.height(16.dp))
//            TextComponent(
//                text = "Daily Monitoring",
//                fontSize = 16.sp,
//                font = R.font.sans_bold,
//                textAlign = TextAlign.Start
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            DailyMonitoring()
//            Spacer(modifier = Modifier.height(16.dp))
            PagerSlider()
            Spacer(modifier = Modifier.height(2.dp))
            TextComponent(
                text = "Target Workout",
                fontSize = 16.sp,
                font = R.font.sans_bold,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(4.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)

            ) {
                items(targetMap.size) {
                    WorkoutView(
                        imageLink = targetMap.values.elementAt(it),
                        exerciseName = targetMap.keys.elementAt(it)
                            .replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                            }
                    ) {

                        navController.navigate(
                            Routes.ExerciseScreen.passExercise(
                                exerciseName = targetMap.keys.elementAt(it),
                                exerciseImage = URLEncoder.encode(
                                    targetMap.values.elementAt(it),
                                    "UTF-8"
                                ), workoutType = WorkoutType.TARGET.toString()
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextComponent(
                text = "Body Part Workout",
                fontSize = 16.sp,
                font = R.font.sans_bold,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(4.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)

            ) {
                items(bodyPartMap.size) {
                    WorkoutView(
                        imageLink = bodyPartMap.values.elementAt(it),
                        exerciseName = bodyPartMap.keys.elementAt(it)
                            .replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                            }
                    ) {
                        navController.navigate(
                            Routes.ExerciseScreen.passExercise(
                                exerciseName = bodyPartMap.keys.elementAt(it),
                                exerciseImage = URLEncoder.encode(
                                    bodyPartMap.values.elementAt(it),
                                    "UTF-8"
                                ),
                                workoutType = WorkoutType.BODY.toString()
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextComponent(
                text = "Equipment Workout",
                fontSize = 16.sp,
                font = R.font.sans_bold,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(4.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)

            ) {
                items(equipmentMap.size) {
                    WorkoutView(
                        imageLink = equipmentMap.values.elementAt(it),
                        exerciseName = equipmentMap.keys.elementAt(it)
                            .replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                            }
                    ) {
                        navController.navigate(
                            Routes.ExerciseScreen.passExercise(
                                exerciseName = equipmentMap.keys.elementAt(it),
                                exerciseImage = URLEncoder.encode(
                                    equipmentMap.values.elementAt(it),
                                    "UTF-8"
                                ), workoutType = WorkoutType.EQUIPMENT.toString()
                            )
                        )
                    }
                }
            }


        }
    }
}

@Composable
fun HeaderHomeScreen() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MainColor)
            .padding(top = 28.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            TextComponent(text = "Welcome back", fontSize = 12.sp, font = R.font.sans_light)
            Spacer(modifier = Modifier.height(5.dp))
            TextComponent(text = "The Royal Gym ", fontSize = 20.sp, font = R.font.sans_bold)
        }
        Image(
            painter = painterResource(id = R.drawable.royal_gym_logo),
            contentDescription = "logo",
            modifier = Modifier
                .shadow(8.dp, shape = CircleShape)
                .size(50.dp)
        )
    }
}

@Composable
@Preview
fun SearchSection() {

    var searchText by remember {
        mutableStateOf("")
    }
    TextField(
        value = searchText,
        onValueChange = {
            searchText = it
        },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "search",
                tint = Color.Unspecified, modifier = Modifier.size(24.dp)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        shape = RoundedCornerShape(24.dp),
        placeholder = {
            TextComponent(
                text = "Search Workout...",
                fontSize = 12.sp,
                font = R.font.sans_medium,
                fontStyle = FontStyle.Italic
            )
        }, colors = TextFieldDefaults.colors(
            unfocusedContainerColor = DarkBlue,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = DarkBlue,
        ), keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search // Specify the keyboard action here
        )
    )
}

@Composable
fun DailyMonitoring() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MonitoringComponent(
            icon = R.drawable.monitor1,
            monitoringName = "Daily Colories",
            monitoringValue = "2500"
        )
        MonitoringComponent(
            icon = R.drawable.monitor2,
            monitoringName = "Sleep Time",
            monitoringValue = "6h 45min"
        )
        MonitoringComponent(
            icon = R.drawable.monitor3,
            monitoringName = "Total Workout",
            monitoringValue = "2w 4 days"
        )
    }
}
