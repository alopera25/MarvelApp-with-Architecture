package com.example.marvelappwitharchitecture.data

data class Character(
    val id: Int,
    val name: String,
    val thumbnail: String
)

val characters = (1..100).map {
    Character(
        id = it,
        name = "Character $it",
        thumbnail = "https://picsum.photos/200/300?id=$it"
    )
}
