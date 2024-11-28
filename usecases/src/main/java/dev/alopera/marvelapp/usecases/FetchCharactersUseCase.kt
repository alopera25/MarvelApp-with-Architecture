package dev.alopera.marvelapp.usecases

import dev.alopera.marvelapp.domain.Character
import dev.alopera.marvelapp.domain.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCharactersUseCase @Inject constructor(private val characterRepository: CharacterRepository) {
    operator fun invoke(): Flow<List<Character>> = characterRepository.characters
}