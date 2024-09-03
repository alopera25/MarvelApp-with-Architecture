package com.example.marvelappwitharchitecture.data.datasource.database

import androidx.room.TypeConverter
import com.example.marvelappwitharchitecture.data.datasource.remote.Thumbnail
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import com.example.marvelappwitharchitecture.data.Character

class Converters {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromThumbnail(thumbnail: Thumbnail): String {
        return json.encodeToString(thumbnail)
    }

    @TypeConverter
    fun toThumbnail(data: String): Thumbnail {
        return json.decodeFromString(data)
    }

}

