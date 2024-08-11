package com.example.royalgymfitness.presentations.nav_graph

const val ARG_KEY_EXERCISE_IMAGE = "arg_key_exercise_image"
const val ARG_KEY_EXERCISE_NAME = "arg_key_exercise_name"
const val ARG_KEY_WORKOUT_TYPE = "arg_key_workout_type"
const val ARG_KEY_EXERCISE_DETAIL_MODEL = "arg_key_exercise_detail_model"
const val ARG_KEY_SEARCH_TEXT = "arg_key_search_text"

sealed class Routes(val route: String) {
    object HomeScreen : Routes(route = "home_screen")
    object TabScreen : Routes(route = "tab_screen")
    object FavouriteScreen : Routes(route = "fav_screen")
    object AllExerciseScreen : Routes(route = "all_exercises_screen/{arg_key_search_text}"){
        fun passSearchText(searchText : String) : String{
            return "all_exercises_screen/{$ARG_KEY_SEARCH_TEXT}".replace(
                oldValue = "{$ARG_KEY_SEARCH_TEXT}",
                newValue = searchText
            )
        }
    }
    object SplashScreen : Routes(route = "splash_screen")
    object ExerciseScreen :
        Routes(route = "exercise_screen/{arg_key_exercise_image}/{arg_key_exercise_name}/{arg_key_workout_type}") {
        fun passExercise(exerciseImage: String, exerciseName: String, workoutType: String): String {
            return "exercise_screen/{$ARG_KEY_EXERCISE_IMAGE}/{$ARG_KEY_EXERCISE_NAME}/{$ARG_KEY_WORKOUT_TYPE}".replace(
                oldValue = "{$ARG_KEY_EXERCISE_IMAGE}",
                newValue = exerciseImage
            ).replace(
                oldValue = "{$ARG_KEY_EXERCISE_NAME}",
                newValue = exerciseName
            ).replace(
                oldValue = "{$ARG_KEY_WORKOUT_TYPE}",
                newValue = workoutType
            )

        }

    }

    object ExerciseDetailScreen : Routes(route = "exercise_detail_screen/{arg_key_exercise_detail_model}") {
        fun passExerciseDetailModel(exerciseDetailModel: String): String {
            return "exercise_detail_screen/{$ARG_KEY_EXERCISE_DETAIL_MODEL}".replace(
                oldValue = "{$ARG_KEY_EXERCISE_DETAIL_MODEL}",
                newValue = exerciseDetailModel
            )
        }
    }
}