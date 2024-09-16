package com.example.marvelappwitharchitecture.usecases

import com.example.marvelappwitharchitecture.data.CharacterRepository

class ToggleFavoriteUseCase(private val repository: CharacterRepository) {
    suspend operator fun invoke(movie: Character) {
        repository.toggleFavorite(movie)
    }
}