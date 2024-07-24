package com.example.royalgymfitness.presentations.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.royalgymfitness.presentations.favourite.FavouriteScreen
import com.example.royalgymfitness.presentations.home.HomeScreen
import com.example.royalgymfitness.presentations.profile.ProfileScreen

//@Composable
//fun SetNavGraph() {
//    val navController  = rememberNavController()
//
//    NavHost(navController = navController, startDestination = Routes.HomeScreen.route ){
//
//        composable(route = Routes.HomeScreen.route){
//            HomeScreen(navController = navController)
//        }
//        composable(route = Routes.FavouriteScreen.route){
//            FavouriteScreen()
//        }
//        composable(route = Routes.ProfileScreen.route){
//            ProfileScreen()
//        }
//    }
//}