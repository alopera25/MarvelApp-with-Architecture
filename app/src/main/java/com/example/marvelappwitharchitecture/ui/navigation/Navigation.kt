package com.example.marvelappwitharchitecture.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.marvelappwitharchitecture.App
import com.example.marvelappwitharchitecture.ui.screens.detail.DetailScreen
import com.example.marvelappwitharchitecture.ui.screens.detail.DetailViewModel
import com.example.marvelappwitharchitecture.ui.screens.home.HomeScreen
import com.example.marvelappwitharchitecture.ui.screens.home.HomeViewModel
import dev.alopera.marvelapp.data.framework.CharacterRoomDataSource
import dev.alopera.marvelapp.data.framework.CharacterServerDataSource
import dev.alopera.marvelapp.data.framework.remote.CharactersClient
import dev.alopera.marvelapp.domain.CharacterRepository
import dev.alopera.marvelapp.usecases.FetchCharactersUseCase

@Composable
fun Navigation() {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = NavScreen.Home.route) {

        composable(NavScreen.Home.route) {
            HomeScreen(
                vm = hiltViewModel(),
                onClick = { character ->
                    navController.navigate(NavScreen.Detail.createRoute(character.id!!))
                }
            )
        }

        composable(
            route = NavScreen.Detail.route,
            arguments = listOf(navArgument(NavArgs.CharacterId.key) { type = NavType.IntType })
        ) { backStackEntry ->
            val detailViewModel: DetailViewModel = hiltViewModel()
            /*val characterId =
                requireNotNull(backStackEntry.arguments?.getInt(NavArgs.CharacterId.key))
            */
            DetailScreen(
                vm = detailViewModel,
                onBack = { navController.popBackStack() })
        }
    }
}