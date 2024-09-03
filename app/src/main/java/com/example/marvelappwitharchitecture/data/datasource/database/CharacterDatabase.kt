package com.example.marvelappwitharchitecture.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.marvelappwitharchitecture.data.Character

@Database(entities = [Character::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CharacterDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}