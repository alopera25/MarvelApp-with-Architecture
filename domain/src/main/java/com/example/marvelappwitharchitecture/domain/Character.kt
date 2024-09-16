package com.example.marvelappwitharchitecture.domain

import com.example.marvelappwitharchitecture.framework.remote.Thumbnail

data class Character(
    val id: Int?,
    val name: String?,
    val description: String?,
    val isFavorite: Boolean
)
