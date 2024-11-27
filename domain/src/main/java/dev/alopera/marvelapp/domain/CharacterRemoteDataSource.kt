package dev.alopera.marvelapp.domain

interface CharacterRemoteDataSource {
    suspend fun fetchCharacters(offset: Int, limit: Int): List<Character>?

    suspend fun fetchCharacterById(characterId: Int): Character?
}