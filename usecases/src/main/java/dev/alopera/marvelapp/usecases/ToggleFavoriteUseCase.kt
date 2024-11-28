package dev.alopera.marvelapp.usecases

import dev.alopera.marvelapp.domain.Character
import dev.alopera.marvelapp.domain.CharacterRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(private val repository: CharacterRepository) {
    suspend operator fun invoke(character: Character) {
        repository.toggleFavorite(character)
    }
}