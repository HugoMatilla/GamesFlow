package com.hugomatilla.gamesflow.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hugomatilla.gamesflow.preferences.UserSettings
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeActivityViewModel : ViewModel(), KoinComponent {

    private val settings: UserSettings by inject()
    var isDarkMode: LiveData<Boolean>

    init {
        isDarkMode = settings.darkModeLiveData
    }

}