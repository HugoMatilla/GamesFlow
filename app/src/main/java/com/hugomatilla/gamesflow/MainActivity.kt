package com.hugomatilla.gamesflow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }

    private fun setup() {
        val settings = UserSettings(this)
        setDarkMode(settings.darkMode)
        toolbar.setTitle(R.string.app_name)
        toolbar.inflateMenu(R.menu.main)
        toolbar.setOnMenuItemClickListener {
            settings.darkMode = !settings.darkMode
            setDarkMode(settings.darkMode)
            true
        }
        toolbar.menu.findItem(R.id.action_theme_mode).setTitle(R.string.theme_title)
    }

    private fun setDarkMode(isDark: Boolean) {
        AppCompatDelegate.setDefaultNightMode(if (isDark) MODE_NIGHT_YES else MODE_NIGHT_NO)
    }
}

