package com.hugomatilla.gamesflow.db.game

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


internal const val tableName = "Game"
internal const val ID = "_id"
internal const val TITLE = "TITLE"
internal const val IMAGE_URL = "IMAGE_URL"
internal const val CAPTION = "CAPTION"
internal const val RATING = "RATING"
internal const val GENRE = "GENRE"
internal const val STARRED = "STARRED"

@Entity(tableName = tableName)
data class Game(
    @PrimaryKey @ColumnInfo(name = ID) var id: Long,
    @ColumnInfo(name = TITLE) var title: String,
    @ColumnInfo(name = IMAGE_URL) var imageUrl: String,
    @ColumnInfo(name = CAPTION) var caption: String,
    @ColumnInfo(name = RATING) var rating: Int, // max 50 -> 5.0
    @ColumnInfo(name = GENRE) var genre: String,
    @ColumnInfo(name = STARRED) var starred: Boolean
)
// TODO extend : GameRepository

