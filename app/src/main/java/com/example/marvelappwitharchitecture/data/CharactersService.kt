package com.example.marvelappwitharchitecture.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersService {

    @GET("/v1/public/characters")
    suspend fun fetchCharacters(): RemoteResult

    @GET("/v1/public/characters/{characterId}")
    suspend fun fetchCharacterById(@Path("characterId") characterId: Int): RemoteResult
}