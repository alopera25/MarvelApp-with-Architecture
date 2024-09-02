package com.example.marvelappwitharchitecture.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class CharactersRepository {

    suspend fun fetchCharacters(): List<Character> =
        CharactersClient.instance.fetchCharacters()
            .results
            .map { it.toDomainModel() }

    suspend fun fetchCharacterById(id: Int): Character? =
        CharactersClient.instance.fetchCharacterById(id)
            .data
            .results
            .firstOrNull()
            ?.toDomainModel()

}

private fun RemoteCharacter.toDomainModel() = Character(
    id = id,
    name = name,
    thumbnail = "https://image.tmdb.org/t/p/w185/1",
)