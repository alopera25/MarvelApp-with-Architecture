package dev.alopera.marvelapp.usecases

import dev.alopera.marvelapp.domain.Character
import dev.alopera.marvelapp.domain.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindCharacterByIdUseCase  @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(id: Int): Flow<Character> = repository.fetchCharacterById(id)
}