package com.example.marvelappwitharchitecture.usecases

import com.example.marvelappwitharchitecture.data.CharacterRepository
import com.example.marvelappwitharchitecture.domain.Character

class ToggleFavoriteUseCase(private val repository: CharacterRepository) {
    suspend operator fun invoke(character: Character) {
        repository.toggleFavorite(character)
    }
}