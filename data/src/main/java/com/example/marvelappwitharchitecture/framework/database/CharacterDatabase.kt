package com.example.marvelappwitharchitecture.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [DbCharacter::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CharacterDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}