package com.hugomatilla.gamesflow.preferences

import android.content.Context
import hu.autsoft.krate.SimpleKrate
import hu.autsoft.krate.booleanPref

class UserSettings(context: Context) : SimpleKrate(context) {
    companion object {
        private const val DARK_MODE_ENABLED_KEY = "dark_mode_enabled"
    }

    private var darkMode by booleanPref(DARK_MODE_ENABLED_KEY, true)
    var darkModeLiveData = this.sharedPreferences.toLiveData(DARK_MODE_ENABLED_KEY, false)

    fun toggleDarkMode() {
        darkMode = !darkMode
    }
}

