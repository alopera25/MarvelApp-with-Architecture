package com.example.marvelappwitharchitecture.ui.navigation

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
    NavHost(navController = navController, startDestination = NavScreen.Home.route) {
        composable(NavScreen.Home.route) {
            HomeScreen(onCharacterClick = { character ->
                navController.navigate(NavScreen.Detail.createRoute(character.id))
            })
        }
        composable(
            route = NavScreen.Detail.route,
            arguments = listOf(navArgument(NavArgs.CharacterId.key) { type = NavType.IntType })
        ) { backStackEntry ->
            val characterId = requireNotNull(backStackEntry.arguments?.getInt(NavArgs.CharacterId.key))
            DetailScreen(
                viewModel { DetailViewModel(characterId) },
                onBack = { navController.popBackStack() })
        }
    }
}