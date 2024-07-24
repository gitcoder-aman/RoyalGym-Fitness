package com.example.royalgymfitness.presentations.nav_graph

sealed class Routes(val route : String){
    object HomeScreen : Routes(route = "home_screen")
    object FavouriteScreen : Routes(route = "fav_screen")
    object ProfileScreen : Routes(route = "profile_screen")
    object SplashScreen : Routes(route = "splash_screen")


}