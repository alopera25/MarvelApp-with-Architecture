package com.example.marvelappwitharchitecture.usecases

import com.example.marvelappwitharchitecture.data.CharacterRepository
import kotlinx.coroutines.flow.Flow

class FetchCharactersUseCase(private val characterRepository: CharacterRepository) {
    operator fun invoke(): Flow<List<Character>> = characterRepository.characters
}