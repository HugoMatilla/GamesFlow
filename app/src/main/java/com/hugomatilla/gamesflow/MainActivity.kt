package com.hugomatilla.gamesflow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.games_list_fragment.*
import splitties.toast.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        setupBottomBar()
    }

    private fun setupToolbar() {
        val settings = UserSettings(this)
        setDarkMode(settings.darkMode)
        toolbar.setOnMenuItemClickListener {
            settings.darkMode = !settings.darkMode
            setDarkMode(settings.darkMode)
            true
        }
    }

    private fun setDarkMode(isDark: Boolean) {
        AppCompatDelegate.setDefaultNightMode(if (isDark) MODE_NIGHT_YES else MODE_NIGHT_NO)
    }

    private fun setupBottomBar() {
        bottomNavigation.setOnNavigationItemSelectedListener { toast(it.title).let { true } }
    }
}

