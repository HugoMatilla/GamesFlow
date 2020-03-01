package com.hugomatilla.gamesflow.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hugomatilla.gamesflow.db.game.Game
import com.hugomatilla.gamesflow.db.game.GameDao
import org.jetbrains.anko.AnkoLogger

@Database(
    entities = [
        (Game::class)
    ],
    version = 1
)

abstract class AppDB : RoomDatabase(), AnkoLogger {
    abstract fun gameDao(): GameDao

    companion object {

        @Volatile
        private var INSTANCE: AppDB? = null

        private const val dbName = "games-flow.db"

        fun init(context: Context) {
            if (INSTANCE == null) {
                synchronized(AppDB::class) {
                    INSTANCE =
                        Room.databaseBuilder(context.applicationContext, AppDB::class.java, dbName)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build()
                }
            }
        }

        fun getInstance(): AppDB {
            Log.i("DB", "AppDB getInstance")
            if (INSTANCE == null) {
                Log.wtf("Room", "Room Database not initialized")
                throw(IllegalAccessError())
            } else
                return INSTANCE!!

        }

        private fun destroyInstance() {
            INSTANCE = null
        }
    }
}