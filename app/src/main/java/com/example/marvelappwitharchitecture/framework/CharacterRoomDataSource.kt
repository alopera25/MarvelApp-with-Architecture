package com.example.marvelappwitharchitecture.framework

import com.example.marvelappwitharchitecture.data.datasource.CharacterLocalDataSource
import com.example.marvelappwitharchitecture.domain.Character
import com.example.marvelappwitharchitecture.framework.database.CharacterDao
import com.example.marvelappwitharchitecture.framework.database.DbCharacter
import com.example.marvelappwitharchitecture.framework.remote.Thumbnail
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