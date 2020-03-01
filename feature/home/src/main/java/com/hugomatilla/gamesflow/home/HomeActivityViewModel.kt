package com.hugomatilla.gamesflow.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeActivityViewModel : ViewModel(), KoinComponent {

    private val toggleDarkModeUseCase: ToggleDarkModeUseCase by inject()

    private var _isDarkMode = MutableLiveData<Boolean>()
    val isDarkMode: LiveData<Boolean>
        get() = _isDarkMode

    fun toggleAndSaveDarkMode() {
        toggleDarkModeUseCase.toggleAndSave()
        _isDarkMode.postValue(toggleDarkModeUseCase.getDarkMode())
    }
}