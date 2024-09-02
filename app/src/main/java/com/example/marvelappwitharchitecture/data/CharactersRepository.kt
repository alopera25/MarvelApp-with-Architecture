package com.example.marvelappwitharchitecture.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class CharactersRepository {
    suspend fun fetchCharacters(): List<Character> = withContext(Dispatchers.IO) {
        delay(1000)
        characters
    }
}