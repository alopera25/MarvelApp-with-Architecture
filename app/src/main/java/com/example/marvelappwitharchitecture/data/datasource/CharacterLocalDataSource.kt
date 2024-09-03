package com.example.marvelappwitharchitecture.data.datasource

import com.example.marvelappwitharchitecture.data.datasource.database.CharacterDao
import com.example.marvelappwitharchitecture.data.Character

class CharacterLocalDataSource(private val characterDao: CharacterDao) {

    val character = characterDao.fetchCharacter()

    fun fetchCharacterById(id: Int) =characterDao.fetchCharacterById(id)

    suspend fun isEmpty() = characterDao.countCharacter() ==0

    suspend fun saveCharacter(character: List<Character>) = characterDao.saveCharacter(character)

}