package com.hugomatilla.gamesflow

import android.content.Context
import hu.autsoft.krate.SimpleKrate
import hu.autsoft.krate.booleanPref

class UserSettings(context: Context) : SimpleKrate(context) {
    var darkMode by booleanPref("dark_mode_enabled", true)
}

