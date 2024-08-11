package com.example.royalgymfitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.royalgymfitness.presentations.nav_graph.gym_navigation.GymNavigator
import com.example.royalgymfitness.ui.theme.RoyalGymFitnessTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            RoyalGymFitnessTheme {
                GymNavigator()
            }
        }
    }
}
