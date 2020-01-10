package com.hugomatilla.gamesflow

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.games_list_fragment.*
import splitties.toast.toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        setupBottomBar()
        Stetho.initializeWithDefaults(this)
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
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (isDark) window.navigationBarColor = Color.BLACK
    }

    private fun setupBottomBar() {
        bottomNavigation.visibility = View.GONE
        bottomNavigation.setOnNavigationItemSelectedListener { toast(it.title).let { true } }
    }
}

