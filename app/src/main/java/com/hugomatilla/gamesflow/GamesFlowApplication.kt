package com.hugomatilla.gamesflow

import android.app.Application
import com.hugomatilla.gamesflow.db.AppDB
import com.hugomatilla.gamesflow.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class GamesFlowApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppDB.init(this)
        startKoin {
            androidContext(this@GamesFlowApplication)
            modules(appModule)
        }
    }

}