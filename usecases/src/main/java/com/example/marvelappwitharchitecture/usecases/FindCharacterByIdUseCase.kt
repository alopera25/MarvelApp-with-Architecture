package com.example.marvelappwitharchitecture.usecases

import com.example.marvelappwitharchitecture.data.CharacterRepository
import com.example.marvelappwitharchitecture.domain.Character
import kotlinx.coroutines.flow.Flow

class FindCharacterByIdUseCase(private val repository: CharacterRepository) {
    operator fun invoke(id: Int): Flow<Character> = repository.fetchCharacterById(id)
}