package dev.alopera.marvelapp.data.framework

import dev.alopera.marvelapp.data.framework.database.CharacterDao
import dev.alopera.marvelapp.data.framework.database.DbCharacter
import dev.alopera.marvelapp.data.framework.remote.Thumbnail
import dev.alopera.marvelapp.domain.Character
import dev.alopera.marvelapp.domain.CharacterLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterRoomDataSource(private val characterDao: CharacterDao) : CharacterLocalDataSource {

override val character: Flow<List<Character>> = characterDao.fetchCharacter().map { it.toDomainCharacters() }

override fun fetchCharacterById(id: Int): Flow<Character?> =
    characterDao.fetchCharacterById(id).map { it?.toDomainCharacter() }

override suspend fun saveCharacter(character: List<Character>) = characterDao.saveCharacter(character.toDbCharacters())

}

fun Thumbnail.toDomainThumbnail() = "$path.$extension"

private fun DbCharacter.toDomainCharacter() = Character(
    id = id,
    name = name,
    description = description,
    thumbnail = thumbnail?.toDomainThumbnail(),
    isFavorite = isFavorite
)

private fun String.toDbThumbnail() = Thumbnail(
    path = substringBeforeLast("."),
    extension = substringAfterLast(".")
)

private fun List<DbCharacter>.toDomainCharacters() = map { it.toDomainCharacter() }

private fun Character.toDbCharacter() = DbCharacter(
        id = id,
        name = name,
        description = description,
        thumbnail = thumbnail?.toDbThumbnail(),
        isFavorite = isFavorite
    )

private fun List<Character>.toDbCharacters() = map { it.toDbCharacter() }