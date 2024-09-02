package com.example.marvelappwitharchitecture.ui.navigation

sealed class NavScreen(val route: String) {
    data object Home : NavScreen("home")
    data object Detail : NavScreen("detail/{${NavArgs.CharacterId.key}}") {
        fun createRoute(characterId: Int) = "detail/$characterId"
    }
}

enum class NavArgs(val key: String) {
    CharacterId("characterId")
}