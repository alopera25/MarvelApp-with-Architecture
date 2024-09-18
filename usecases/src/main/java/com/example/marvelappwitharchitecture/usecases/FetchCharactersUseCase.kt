package com.example.marvelappwitharchitecture.usecases

import com.example.marvelappwitharchitecture.data.CharacterRepository
import kotlinx.coroutines.flow.Flow
import com.example.marvelappwitharchitecture.domain.Character

class FetchCharactersUseCase(private val characterRepository: CharacterRepository) {
    operator fun invoke(): Flow<List<Character>> = characterRepository.characters
}