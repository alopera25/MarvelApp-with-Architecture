package dev.alopera.marvelapp.data.framework.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.alopera.marvelapp.data.framework.remote.Thumbnail

@Entity
data class DbCharacter(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String?,
    val description: String?,
    val thumbnail: Thumbnail?,
    val isFavorite: Boolean
)