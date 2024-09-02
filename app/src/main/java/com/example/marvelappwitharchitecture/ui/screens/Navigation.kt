package com.example.marvelappwitharchitecture.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.marvelappwitharchitecture.ui.screens.detail.DetailScreen
import com.example.marvelappwitharchitecture.ui.screens.detail.DetailViewModel
import com.example.marvelappwitharchitecture.ui.screens.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(onCharacterClick = { character ->
                navController.navigate("detail/${character.id}")
            })
        }
        composable(
            route = "detail/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            val characterId = requireNotNull(backStackEntry.arguments?.getInt("characterId"))
            DetailScreen(
                viewModel { DetailViewModel(characterId) },
                onBack = { navController.popBackStack() })
        }
    }
}