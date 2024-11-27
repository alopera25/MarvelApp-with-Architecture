package dev.alopera.marvelapp.usecases

import dev.alopera.marvelapp.domain.Character
import dev.alopera.marvelapp.domain.CharacterRepository
import kotlinx.coroutines.flow.Flow

class FindCharacterByIdUseCase (
    private val repository: CharacterRepository
) {
    operator fun invoke(id: Int): Flow<Character> = repository.fetchCharacterById(id)
}