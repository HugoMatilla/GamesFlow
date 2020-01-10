package com.hugomatilla.gamesflow

import android.app.Application
import android.content.Context
import com.hugomatilla.data.AppDB
import org.jetbrains.anko.AnkoLogger


class GamesFlowApplication : Application(), AnkoLogger {

    override fun onCreate() {
        super.onCreate()
        AppDB.init(this)
        info("AppInit")

    }

    val appContext: Context
        get() {
            return applicationContext
        }

}