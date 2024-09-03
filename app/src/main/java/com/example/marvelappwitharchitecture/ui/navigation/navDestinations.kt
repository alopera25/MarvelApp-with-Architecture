package com.example.marvelappwitharchitecture.ui.navigation

import kotlinx.serialization.Serializable


@Serializable
object Home

@Serializable
data class Detail (val characterId: Int)