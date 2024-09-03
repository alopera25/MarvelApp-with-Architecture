package com.example.marvelappwitharchitecture.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelappwitharchitecture.domain.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM DbCharacter")
    fun fetchCharacter(): Flow<List<DbCharacter>>

    @Query("SELECT * FROM DbCharacter WHERE id = :id")
    fun fetchCharacterById(id: Int): Flow<DbCharacter?>

    @Query("SELECT COUNT(*) FROM DbCharacter")
    suspend fun countCharacter(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacter(character: List<DbCharacter>)

}
