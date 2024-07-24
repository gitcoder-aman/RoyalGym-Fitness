package com.example.royalgymfitness.presentations.nav_graph.gym_navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.royalgymfitness.R
import com.example.royalgymfitness.backend.viewmodel.HomeViewModel
import com.example.royalgymfitness.presentations.favourite.FavouriteScreen
import com.example.royalgymfitness.presentations.home.HomeScreen
import com.example.royalgymfitness.presentations.nav_graph.Routes
import com.example.royalgymfitness.presentations.profile.ProfileScreen
import com.example.royalgymfitness.presentations.splash.SplashScreen

@Composable
fun GymNavigator() {

    val bottomNavigationItem = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.heart, text = "Favourite"),
            BottomNavigationItem(icon = R.drawable.user, text = "Profile")
        )
    }
    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Routes.HomeScreen.route -> 0
            Routes.FavouriteScreen.route -> 1
            Routes.ProfileScreen.route -> 2
            else -> 0
        }
    }
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Routes.HomeScreen.route || backStackState?.destination?.route == Routes.FavouriteScreen.route || backStackState?.destination?.route == Routes.ProfileScreen.route
    }

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        bottomBar = {
            if (isBottomBarVisible) {
                GymBottomNavigation(
                    items = bottomNavigationItem,
                    selected = selectedItem,
                    onItemClick = {
                        when (it) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Routes.HomeScreen.route
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Routes.FavouriteScreen.route
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Routes.ProfileScreen.route
                            )
                        }
                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Routes.SplashScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Routes.SplashScreen.route) {
                SplashScreen(navController)
            }
            composable(route = Routes.HomeScreen.route) {
                val homeViewModel: HomeViewModel = hiltViewModel()
                HomeScreen(homeViewModel,navController)
            }
            composable(route = Routes.FavouriteScreen.route) {
                FavouriteScreen()
            }
            composable(route = Routes.ProfileScreen.route) {
                ProfileScreen()
            }
        }

    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
            navController.popBackStack(
                route,
                inclusive = true
            ) // Clear back stack and go to Screen1
        }
    }
}
