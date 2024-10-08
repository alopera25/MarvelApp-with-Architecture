package com.example.marvelappwitharchitecture.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.marvelappwitharchitecture.App
import com.example.marvelappwitharchitecture.data.CharacterRepository
import com.example.marvelappwitharchitecture.framework.CharacterRoomDataSource
import com.example.marvelappwitharchitecture.framework.CharacterServerDataSource
import com.example.marvelappwitharchitecture.framework.remote.CharactersClient
import com.example.marvelappwitharchitecture.ui.screens.detail.DetailScreen
import com.example.marvelappwitharchitecture.ui.screens.detail.DetailViewModel
import com.example.marvelappwitharchitecture.ui.screens.home.HomeScreen
import com.example.marvelappwitharchitecture.ui.screens.home.HomeViewModel
import com.example.marvelappwitharchitecture.usecases.FetchCharactersUseCase
import com.example.marvelappwitharchitecture.usecases.FindCharacterByIdUseCase
import com.example.marvelappwitharchitecture.usecases.ToggleFavoriteUseCase

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val app = LocalContext.current.applicationContext as App

    val characterRepository= CharacterRepository(
        CharacterServerDataSource(CharactersClient.instance),
        CharacterRoomDataSource(app.db.characterDao()),
    )

    NavHost(navController = navController, startDestination = NavScreen.Home.route) {

        composable(NavScreen.Home.route) {
            HomeScreen(
                viewModel { HomeViewModel(FetchCharactersUseCase(characterRepository)) },
                onClick = { character ->
                    navController.navigate(NavScreen.Detail.createRoute(character.id!!))
                }
            )
        }

        composable(
            route = NavScreen.Detail.route,
            arguments = listOf(navArgument(NavArgs.CharacterId.key) { type = NavType.IntType })
        ) { backStackEntry ->
            val characterId =
                requireNotNull(backStackEntry.arguments?.getInt(NavArgs.CharacterId.key))
            DetailScreen(
                viewModel {
                    DetailViewModel(
                        characterId,
                        FindCharacterByIdUseCase(characterRepository),
                        ToggleFavoriteUseCase(characterRepository)
                    )
                },
                onBack = { navController.popBackStack() })
        }
    }
}