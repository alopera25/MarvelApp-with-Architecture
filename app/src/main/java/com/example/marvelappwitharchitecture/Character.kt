package com.example.marvelappwitharchitecture

data class Character(
    val id: Int,
    val name: String,
    val thumbnail: String
)

val characters = (1..100).map {
    Character(
        id = it,
        name = "Character $it",
        thumbnail = "https://picsum.photos/400/400"
    )
}
