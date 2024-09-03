package com.example.marvelappwitharchitecture.domain

import com.example.marvelappwitharchitecture.data.datasource.remote.Thumbnail

data class Character(
    val id: Int?,
    val name: String?,
    val description: String?,
    val thumbnail: Thumbnail?,
    val isFavorite: Boolean
)
