package com.example.marvelappwitharchitecture.data.datasource

import kotlinx.coroutines.flow.Flow
import com.example.marvelappwitharchitecture.domain.Character

interface CharacterLocalDataSource {
    val character: Flow<List<Character>>
    fun fetchCharacterById(id: Int): Flow<Character?>

    suspend fun saveCharacter(character: List<Character>)
}