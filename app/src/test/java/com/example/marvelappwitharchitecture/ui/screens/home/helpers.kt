package com.example.marvelappwitharchitecture.ui.screens.home

import dev.alopera.marvelapp.domain.Character

fun sampleCharacter(id: Int) = Character(
    id = id,
    name = "Name",
    description = "Description",
    isFavorite = false,
    thumbnail = null
)
fun sampleCharacters(vararg ids: Int) = ids.map { sampleCharacter(it) }