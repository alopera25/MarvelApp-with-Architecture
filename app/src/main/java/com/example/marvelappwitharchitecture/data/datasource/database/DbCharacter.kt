package com.example.marvelappwitharchitecture.data.datasource.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvelappwitharchitecture.data.datasource.remote.Thumbnail

@Entity
data class DbCharacter(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String?,
    val description: String?,
    val thumbnail: Thumbnail?,
    val isFavorite: Boolean
)