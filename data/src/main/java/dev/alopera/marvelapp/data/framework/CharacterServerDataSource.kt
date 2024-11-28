package dev.alopera.marvelapp.data.framework

import dev.alopera.marvelapp.data.framework.remote.CharactersService
import dev.alopera.marvelapp.data.framework.remote.RemoteCharacter
import dev.alopera.marvelapp.domain.Character
import dev.alopera.marvelapp.domain.CharacterRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterServerDataSource @Inject constructor(
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
    isFavorite = false,
    thumbnail = thumbnail?.toDomainThumbnail()
)