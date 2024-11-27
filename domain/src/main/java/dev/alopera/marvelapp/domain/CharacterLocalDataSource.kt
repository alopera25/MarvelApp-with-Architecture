package dev.alopera.marvelapp.domain

import kotlinx.coroutines.flow.Flow

interface CharacterLocalDataSource {
    val character: Flow<List<Character>>
    fun fetchCharacterById(id: Int): Flow<Character?>

    suspend fun saveCharacter(character: List<Character>)
}