package com.example.marvelappwitharchitecture.framework.database

import androidx.room.TypeConverter
import com.example.marvelappwitharchitecture.framework.remote.Thumbnail
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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

