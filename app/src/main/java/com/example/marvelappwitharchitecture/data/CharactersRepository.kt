package com.example.marvelappwitharchitecture.data

import com.example.marvelappwitharchitecture.data.datasource.CharacterLocalDataSource
import com.example.marvelappwitharchitecture.data.datasource.CharacterRemoteDataSource
import com.example.marvelappwitharchitecture.data.datasource.database.DbCharacter
import com.example.marvelappwitharchitecture.domain.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach

class CharacterRepository(
    private val characterRemoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: CharacterLocalDataSource
) {
    val characters: Flow<List<Character>> = localDataSource.character.onEach { localCharacters ->
        if (localCharacters.isEmpty()) {
            val remoteCharacters = characterRemoteDataSource.fetchCharacters(offset = 0, limit = 20)
            localDataSource.saveCharacter(remoteCharacters!!)
        }
    }

    fun fetchCharacterById(id: Int): Flow<Character> = localDataSource.fetchCharacterById(id)
        .onEach { character ->
            if (character == null) {
                val remoteCharacter = characterRemoteDataSource.fetchCharacterById(id)
                localDataSource.saveCharacter(listOf(remoteCharacter!!))
            }
        }
        .filterNotNull()

    suspend fun toggleFavorite(character: Character) {
        localDataSource.saveCharacter(listOf(character.copy(isFavorite = !character.isFavorite)))
    }
}