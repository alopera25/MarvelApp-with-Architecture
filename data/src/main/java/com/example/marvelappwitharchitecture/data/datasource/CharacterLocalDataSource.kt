package com.example.marvelappwitharchitecture.data.datasource

import com.example.marvelappwitharchitecture.domain.Character
import kotlinx.coroutines.flow.Flow

interface CharacterLocalDataSource {
    val character: Flow<List<Character>>
    fun fetchCharacterById(id: Int): Flow<Character?>

    suspend fun saveCharacter(character: List<Character>)
}