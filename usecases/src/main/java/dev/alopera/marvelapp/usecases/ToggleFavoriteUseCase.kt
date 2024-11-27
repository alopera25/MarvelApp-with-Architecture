package dev.alopera.marvelapp.usecases

import dev.alopera.marvelapp.domain.Character
import dev.alopera.marvelapp.domain.CharacterRepository

class ToggleFavoriteUseCase(private val repository: CharacterRepository) {
    suspend operator fun invoke(character: Character) {
        repository.toggleFavorite(character)
    }
}