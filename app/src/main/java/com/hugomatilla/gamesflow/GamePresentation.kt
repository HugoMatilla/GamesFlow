package com.hugomatilla.gamesflow

import com.hugomatilla.gamesflow.data.db.Game

const val CyberPunkImage =
    "https://generacionxbox.com/wp-content/uploads/2019/09/cyberpunk-2077.jpg"
const val WitcherImage =
    "https://windumanoth.com/wp-content/uploads/2018/02/Wiedzminrpg2.jpg"
const val AssasinImage =
    "https://cdn02.nintendo-europe.com/media/images/10_share_images/games_15/nintendo_switch_4/H2x1_NSwitch_AssassinsCreedIIIDefinitiveEdition_image1600w.jpg"
const val DoomImage =
    "https://www.muycomputer.com/wp-content/uploads/2017/11/DOOM-2016-1000x576.jpg"
const val GOW3Image =
    "https://i0.wp.com/www.gadgetlogia.com/wp-content/uploads/2015/03/God-of-War-3-PS4.jpg?fit=809%2C484"

data class GamePresentation(
    val title: String,
    val imageUrl: String,
    val caption: String,
    val rating: Int,
    val genre: Genre
)

enum class Genre {
    ACTION,
    ADVENTURE,
    RPG,
    FIGHT,
    TERROR
}

fun getGames() = listOf(
    GamePresentation(
        title = "Cyber Punk",
        imageUrl = CyberPunkImage,
        caption = "Lorem ipsum Cyber Punk",
        rating = 5,
        genre = Genre.ACTION
    ),
    GamePresentation(
        title = "Assasins Creed",
        imageUrl = AssasinImage,
        caption = "Lorem Assasins Creed ipsum",
        rating = 5,
        genre = Genre.ACTION
    ),
    GamePresentation(
        title = "Doom",
        imageUrl = DoomImage,
        caption = "Lorem Doom ipsum Doom",
        rating = 5,
        genre = Genre.ACTION
    ),
    GamePresentation(
        title = "God Of War 3",
        imageUrl = GOW3Image,
        caption = "God Of War Lorem ipsum",
        rating = 5,
        genre = Genre.ACTION
    ),
    GamePresentation(
        title = "The Witcher",
        imageUrl = WitcherImage,
        caption = "Lorem The Witcher ipsum",
        rating = 5,
        genre = Genre.ACTION
    )
)

fun List<Game>.toPresentation() = map { it.toPresentation() }
fun Game.toPresentation() = GamePresentation(
    title = title,
    imageUrl = imageUrl,
    caption = caption,
    rating = rating,
    genre = Genre.ACTION
//    genre = Genre.valueOf(genre.toUpperCase())
)
