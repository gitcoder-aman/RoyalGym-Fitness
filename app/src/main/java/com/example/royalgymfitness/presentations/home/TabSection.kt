package com.example.royalgymfitness.presentations.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.royalgymfitness.R
import com.example.royalgymfitness.presentations.home.components.TextComponent
import com.example.royalgymfitness.presentations.home.weekly_plan.WeeklyPlanScreen
import com.example.royalgymfitness.ui.theme.DarkBlue
import com.example.royalgymfitness.ui.theme.MainColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TabSection(navController: NavHostController) {

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = DarkBlue
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
    ) {

        var selectedTabIndex by rememberSaveable {
            mutableIntStateOf(0)
        }
        val tabList = listOf("Main", "Weekly Workout")
        TabRow(selectedTabIndex = selectedTabIndex,containerColor = DarkBlue) {
            tabList.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        TextComponent(
                            text = title,
                            fontSize = 18.sp,
                            font = R.font.sans_medium
                        )
                    })
            }

        }
        when (selectedTabIndex) {
            0 -> {
                HomeScreen(navController = navController)
            }
            1 -> {
                WeeklyPlanScreen(navController = navController)
            }
        }
    }
}