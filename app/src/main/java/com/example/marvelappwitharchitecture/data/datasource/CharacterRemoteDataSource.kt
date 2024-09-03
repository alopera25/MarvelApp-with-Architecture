package com.example.marvelappwitharchitecture.data.datasource

import com.example.marvelappwitharchitecture.domain.Character
import com.example.marvelappwitharchitecture.data.datasource.remote.CharactersClient
import com.example.marvelappwitharchitecture.data.datasource.remote.CharactersService
import com.example.marvelappwitharchitecture.data.datasource.remote.RemoteCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface CharacterRemoteDataSource {
    suspend fun fetchCharacters(offset: Int, limit: Int): List<Character>?

    suspend fun fetchCharacterById(characterId: Int): Character?
}

class CharacterServerDataSource(
    private val charactersService: CharactersService
) : CharacterRemoteDataSource {

    override suspend fun fetchCharacters(offset: Int, limit: Int): List<Character>? =
        withContext(Dispatchers.IO) {
            try {
                charactersService.fetchCharacter(offset, limit)
                    .data
                    .results
                    .map { it.toDomainModel() }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

    override suspend fun fetchCharacterById(characterId: Int): Character? = withContext(Dispatchers.IO) {
        try {
            charactersService.fetchCharacterById(characterId)
                .data
                .results
                .firstOrNull()
                ?.toDomainModel()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}

private fun RemoteCharacter.toDomainModel() = Character(
    id = id,
    name = name,
    description = description,
    thumbnail = thumbnail,
    false
)