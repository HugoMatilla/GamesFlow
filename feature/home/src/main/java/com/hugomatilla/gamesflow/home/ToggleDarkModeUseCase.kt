package com.hugomatilla.gamesflow.home

import com.hugomatilla.gamesflow.preferences.UserSettings
import org.koin.core.KoinComponent
import org.koin.core.inject

class ToggleDarkModeUseCase() : KoinComponent {

    private val settings: UserSettings by inject()

    fun toggleAndSave() {
//        settings.darkMode = !settings.darkMode
    }

    fun saveDarkMode(isDark: Boolean) {
//        settings.darkMode = isDark
    }

    fun getDarkMode() = settings.darkModeLiveData
}
