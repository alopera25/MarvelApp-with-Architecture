package com.example.marvelappwitharchitecture.data.datasource

import com.example.marvelappwitharchitecture.data.datasource.database.CharacterDao
import com.example.marvelappwitharchitecture.data.datasource.database.DbCharacter
import com.example.marvelappwitharchitecture.domain.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface CharacterLocalDataSource {
    val character: Flow<List<Character>>
    fun fetchCharacterById(id: Int): Flow<Character?>

    suspend fun saveCharacter(character: List<Character>)
}

class CharacterRoomDataSource(private val characterDao: CharacterDao) : CharacterLocalDataSource {

override val character: Flow<List<Character>> = characterDao.fetchCharacter().map { it.toDomainCharacters() }

override fun fetchCharacterById(id: Int): Flow<Character?> =
    characterDao.fetchCharacterById(id).map { it?.toDomainCharacter() }

override suspend fun saveCharacter(character: List<Character>) = characterDao.saveCharacter(character.toDbCharacters())

}

private fun DbCharacter.toDomainCharacter() = Character(
    id = id,
    name = name,
    description = description,
    thumbnail = thumbnail,
    isFavorite = isFavorite
)

private fun List<DbCharacter>.toDomainCharacters() = map { it.toDomainCharacter() }

private fun Character.toDbCharacter() = DbCharacter(
    id = id,
    name = name,
    description = description,
    thumbnail = thumbnail,
    isFavorite = isFavorite
)

private fun List<Character>.toDbCharacters() = map { it.toDbCharacter() }