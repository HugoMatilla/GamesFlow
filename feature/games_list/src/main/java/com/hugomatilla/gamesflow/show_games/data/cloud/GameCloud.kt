package com.hugomatilla.gamesflow.show_games.data.cloud

data class GamesCloud(
    val results: List<GameCloud>
)

data class GameCloud(
    val id: Long,
    val name: String,
    val background_image: String,
    val rating: String
)

