package com.example.marvelappwitharchitecture.domain


data class Character(
    val id: Int?,
    val name: String?,
    val description: String?,
    val isFavorite: Boolean,
    val thumbnail: String?
)
