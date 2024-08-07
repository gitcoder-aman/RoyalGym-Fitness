package com.example.royalgymfitness.presentations.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ehsanmsz.mszprogressindicator.progressindicator.LineScaleProgressIndicator
import com.example.royalgymfitness.R
import com.example.royalgymfitness.presentations.home.components.TextComponent
import com.example.royalgymfitness.presentations.nav_graph.Routes
import com.example.royalgymfitness.ui.theme.MainColor
import com.example.royalgymfitness.ui.theme.RoyalGymFitnessTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor)
    ) {
        Column {
            Column(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.intro_pic),
                    contentDescription = "logo",
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MainColor),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.royal_gym_logo),
                    contentDescription = "logo",
                    modifier = Modifier.shadow(8.dp, shape = CircleShape)
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextComponent(
                    text = "Royal Gym Fitness Chakand",
                    fontSize = 24.sp,
                    font = R.font.sans_medium
                )

                LineScaleProgressIndicator(
                    modifier = Modifier,
                    color = Color.White,
                    animationDuration = 800,
                    animationDelay = 200,
                    startDelay = 0
                )
            }
        }
    }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        delay(3000)
        coroutineScope.launch {
            navController.navigate(Routes.TabScreen.route) {
                popUpTo(Routes.SplashScreen.route) {
                    inclusive = true
                }
            }
        }
    }
}

@Composable
fun SplashPreview(modifier: Modifier = Modifier) {
    RoyalGymFitnessTheme {
        SplashScreen(navController = NavHostController(LocalContext.current))
    }
}