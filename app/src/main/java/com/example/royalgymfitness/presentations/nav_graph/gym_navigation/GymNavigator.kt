package com.example.royalgymfitness.presentations.nav_graph.gym_navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.royalgymfitness.R
import com.example.royalgymfitness.backend.domain.model.ExerciseModel
import com.example.royalgymfitness.backend.util.WorkoutType
import com.example.royalgymfitness.backend.viewmodel.ExerciseViewModel
import com.example.royalgymfitness.db.presentation.ExerciseDBViewModel
import com.example.royalgymfitness.presentations.allexercises.AllExerciseScreen
import com.example.royalgymfitness.presentations.favourite.FavouriteScreen
import com.example.royalgymfitness.presentations.home.TabSection
import com.example.royalgymfitness.presentations.nav_graph.ARG_KEY_EXERCISE_DETAIL_MODEL
import com.example.royalgymfitness.presentations.nav_graph.ARG_KEY_EXERCISE_IMAGE
import com.example.royalgymfitness.presentations.nav_graph.ARG_KEY_EXERCISE_NAME
import com.example.royalgymfitness.presentations.nav_graph.ARG_KEY_SEARCH_TEXT
import com.example.royalgymfitness.presentations.nav_graph.ARG_KEY_WORKOUT_TYPE
import com.example.royalgymfitness.presentations.nav_graph.Routes
import com.example.royalgymfitness.presentations.otherscreen.ExerciseDetailScreen
import com.example.royalgymfitness.presentations.otherscreen.ExerciseScreen
import com.example.royalgymfitness.presentations.otherscreen.ExerciseState
import com.example.royalgymfitness.presentations.splash.SplashScreen
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.util.Locale

@Composable
fun GymNavigator() {

    val bottomNavigationItem = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.exercise, text = "Exercises"),
            BottomNavigationItem(icon = R.drawable.heart, text = "Favourites")
        )
    }
    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Routes.TabScreen.route -> 0
            Routes.AllExerciseScreen.route -> 1
            Routes.FavouriteScreen.route -> 2
            else -> 0
        }
    }
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Routes.TabScreen.route || backStackState?.destination?.route == Routes.FavouriteScreen.route || backStackState?.destination?.route == Routes.AllExerciseScreen.route
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
                                route = Routes.TabScreen.route
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Routes.AllExerciseScreen.route
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Routes.FavouriteScreen.route
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
            composable(route = Routes.TabScreen.route) {
                TabSection(navController)
            }
//            composable(route = Routes.HomeScreen.route) {
//                HomeScreen(navController)
//            }
            composable(route = Routes.FavouriteScreen.route) {
                val exerciseDbViewModel: ExerciseDBViewModel = hiltViewModel()
                val listFavExerciseBundle by exerciseDbViewModel.listExerciseBundle.observeAsState(ExerciseState.Loading)
                val listFavExercise by exerciseDbViewModel.exerciseFavList.observeAsState(ExerciseState.Loading)

                FavouriteScreen(listFavExerciseBundle,listFavExercise,navController)

            }
            composable(route = Routes.AllExerciseScreen.route, arguments = listOf(
                navArgument(ARG_KEY_SEARCH_TEXT){type = NavType.StringType}
            )) {

                val searchText = remember {
                    it.arguments?.getString(ARG_KEY_SEARCH_TEXT)
                }
                val exerciseViewModel: ExerciseViewModel = hiltViewModel()
                val exerciseListState by exerciseViewModel.exerciseList.collectAsState()
                AllExerciseScreen(exerciseListState,navController,searchText)
            }
            composable(
                route = Routes.ExerciseScreen.route, arguments = listOf(
                    navArgument(ARG_KEY_EXERCISE_IMAGE) { type = NavType.StringType },
                    navArgument(ARG_KEY_EXERCISE_NAME) { type = NavType.StringType },
                    navArgument(ARG_KEY_WORKOUT_TYPE) { type = NavType.StringType }
                )
            ) {
                val exerciseImage = remember {
                    it.arguments?.getString(ARG_KEY_EXERCISE_IMAGE)
                        ?.let { URLDecoder.decode(it, "UTF-8") }
                }
                val exerciseName = remember {
                    it.arguments?.getString(ARG_KEY_EXERCISE_NAME)?.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                    }
                }
                val workoutType = remember {
                    it.arguments?.getString(ARG_KEY_WORKOUT_TYPE)
                }

                val exerciseViewModel: ExerciseViewModel = hiltViewModel()

                BackHandler {
                    // Handle custom back press logic here if needed
                    navController.popBackStack()
                }

                LaunchedEffect(workoutType, exerciseName) {
                    when (workoutType) {
                        WorkoutType.TARGET.toString() -> {
                            exerciseViewModel.getTargetExercise(
                                exerciseName?.lowercase().toString()
                            )
                        }

                        WorkoutType.BODY.toString() -> {
                            exerciseViewModel.getBodyPartExercise(
                                exerciseName?.lowercase().toString()
                            )
                        }

                        WorkoutType.EQUIPMENT.toString() -> {
                            exerciseViewModel.getEquipmentExercise(
                                exerciseName?.lowercase().toString()
                            )
                        }
                    }
                }

                val targetExerciseListState by exerciseViewModel.targetExerciseList.collectAsState()
                val bodyPartExerciseListState by exerciseViewModel.bodyPartExerciseList.collectAsState()
                val equipmentExerciseListState by exerciseViewModel.equipmentExerciseList.collectAsState()


                //here to implement db for store exerciseList through favorite section
                val exerciseDbViewModel: ExerciseDBViewModel = hiltViewModel()
                val isFavoriteState by exerciseDbViewModel.isFavorite.observeAsState(ExerciseState.Loading)
                val listFavExerciseBundle by exerciseDbViewModel.listExerciseBundle.observeAsState(ExerciseState.Loading)

                when (workoutType) {
                    WorkoutType.TARGET.toString() -> {
                        ExerciseScreen(
                            exerciseDbViewModel,
                            isFavoriteState,
                            listFavExerciseBundle,
                            navController,
                            exerciseName,
                            exerciseImage,
                            workoutType,
                            targetExerciseListState
                        )
                    }

                    WorkoutType.BODY.toString() -> {
                        ExerciseScreen(
                            exerciseDbViewModel,
                            isFavoriteState,
                            listFavExerciseBundle,
                            navController,
                            exerciseName,
                            exerciseImage,
                            workoutType,
                            bodyPartExerciseListState
                        )
                    }

                    else -> {
                        ExerciseScreen(
                            exerciseDbViewModel,
                            isFavoriteState,
                            listFavExerciseBundle,
                            navController,
                            exerciseName,
                            exerciseImage,
                            workoutType,
                            equipmentExerciseListState
                        )
                    }
                }
            }
            composable(route = Routes.ExerciseDetailScreen.route, arguments = listOf(
                navArgument(ARG_KEY_EXERCISE_DETAIL_MODEL) { type = NavType.StringType }
            )) {
                val exerciseDetail = it.arguments?.getString(ARG_KEY_EXERCISE_DETAIL_MODEL)
                val exerciseDetailJson =
                    exerciseDetail?.let { it1 -> Json.decodeFromString<ExerciseModel>(it1) }

                val exerciseDbViewModel: ExerciseDBViewModel = hiltViewModel()
                val isFavoriteState by exerciseDbViewModel.isFavorite.observeAsState(ExerciseState.Loading)
                val exerciseFavListState by exerciseDbViewModel.exerciseFavList.observeAsState(ExerciseState.Loading)

                ExerciseDetailScreen(exerciseDbViewModel,isFavoriteState,exerciseFavListState,navController, exerciseDetailJson)
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
