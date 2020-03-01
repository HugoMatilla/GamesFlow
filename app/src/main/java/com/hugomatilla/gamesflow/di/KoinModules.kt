package com.hugomatilla.gamesflow.di

import com.hugomatilla.gamesflow.home.ToggleDarkModeUseCase
import com.hugomatilla.gamesflow.preferences.UserSettings
import org.koin.dsl.module


// just declare it
val appModule = module {
    single { UserSettings(get()) }
    single { ToggleDarkModeUseCase() }
}