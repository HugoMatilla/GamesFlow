package com.hugomatilla.gamesflow.db.game

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.Flowable


@Dao
interface GameDao {
    @Query("SELECT * FROM $tableName ORDER BY $TITLE ASC")
    fun getAll(): List<Game>

    @Query("SELECT * FROM $tableName ORDER BY $TITLE ASC")
    fun getAllRx(): Flowable<List<Game>>

    @Query("SELECT * FROM $tableName WHERE $ID=:id")
    fun getById(id: Long): List<Game>

    @Query("UPDATE $tableName SET $STARRED = :starred WHERE $ID=:id")
    fun starGameById(id: Long, starred: Boolean)

    @Insert(onConflict = REPLACE)
    fun insertAll(item: List<Game>)

    @Query("DELETE FROM $tableName")
    fun deleteAll()
}
