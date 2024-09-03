package com.example.marvelappwitharchitecture.framework.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvelappwitharchitecture.framework.remote.Thumbnail

@Entity
data class DbCharacter(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String?,
    val description: String?,
    val thumbnail: Thumbnail?,
    val isFavorite: Boolean
)