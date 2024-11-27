package dev.alopera.marvelapp.usecases

import dev.alopera.marvelapp.domain.Character
import dev.alopera.marvelapp.domain.CharacterRepository
import kotlinx.coroutines.flow.Flow

class FetchCharactersUseCase(private val characterRepository: CharacterRepository) {
    operator fun invoke(): Flow<List<Character>> = characterRepository.characters
}