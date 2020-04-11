package com.hugomatilla.gamesflow.ui

import android.graphics.Color
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate

fun Window.setDarkMode(isDark: Boolean) {
    AppCompatDelegate.setDefaultNightMode(if (isDark) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    if (isDark) navigationBarColor = Color.BLACK
}