package dev.alopera.marvelapp.domain

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
            localDataSource.saveCharacter(remoteCharacters ?: emptyList())
        }
    }

    fun fetchCharacterById(id: Int): Flow<Character> = localDataSource.fetchCharacterById(id)
        .onEach { character ->
            if (character == null) {
                val remoteCharacter = characterRemoteDataSource.fetchCharacterById(id)
                remoteCharacter?.let {
                    localDataSource.saveCharacter(listOf(it))
                }
            }
        }
        .filterNotNull()

    suspend fun toggleFavorite(character: Character) {
        localDataSource.saveCharacter(listOf(character.copy(isFavorite = !character.isFavorite)))
    }
}