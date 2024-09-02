package com.example.marvelappwitharchitecture.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteResult(
    val data: Data
)

@Serializable
data class Data(
    val results: List<RemoteCharacter>
)

@Serializable
data class RemoteCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail?
)

@Serializable
data class Thumbnail(
    @SerialName("path") val path: String,
    @SerialName("extension") val extension: String
)