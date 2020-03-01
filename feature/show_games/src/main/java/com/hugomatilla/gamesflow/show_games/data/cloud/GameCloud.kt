package com.hugomatilla.data

data class GamesCloud(
    val results: List<GameCloud>
)

data class GameCloud(
    val id: Long,
    val name: String,
    val background_image: String,
    val rating: String
)

