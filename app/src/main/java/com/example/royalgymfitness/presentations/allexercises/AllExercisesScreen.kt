package com.example.royalgymfitness.presentations.allexercises

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AllExerciseScreen(modifier: Modifier = Modifier) {

    Box(modifier = modifier
        .background(Color.Green)
        .fillMaxSize()){
        Column(
           verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "All Exercises")
        }
    }


}