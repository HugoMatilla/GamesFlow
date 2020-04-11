package com.hugomatilla.gamesflow.preferences

import android.content.SharedPreferences
import androidx.lifecycle.LiveData

open class PreferenceLiveData<T>(
    val preferences: SharedPreferences,
    private val key: String,
    private val block: SharedPreferences.() -> T?
) : LiveData<T>() {

    private val listener: SharedPreferences.OnSharedPreferenceChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { preferences: SharedPreferences, key: String ->
            this.key.takeIf { it == key }?.let { value = block.invoke(preferences) }
        }

    override fun onActive() {
        super.onActive()
        value = block.invoke(preferences)
        preferences.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun onInactive() {
        super.onInactive()
        preferences.unregisterOnSharedPreferenceChangeListener(listener)
    }
}

class IntPreferenceLiveData(preferences: SharedPreferences, key: String, defaultValue: Int) :
    PreferenceLiveData<Int>(preferences, key, { getInt(key, defaultValue) })

class StringPreferenceLiveData(preferences: SharedPreferences, key: String, defaultValue: String) :
    PreferenceLiveData<String>(preferences, key, { getString(key, defaultValue) })

class BooleanPreferenceLiveData(preferences: SharedPreferences, key: String, defaultValue: Boolean) :
    PreferenceLiveData<Boolean>(preferences, key, { getBoolean(key, defaultValue) })

fun SharedPreferences.toLiveData(key: String, defaultValue: Int): IntPreferenceLiveData {
    return IntPreferenceLiveData(this, key, defaultValue)
}

fun SharedPreferences.toLiveData(key: String, defaultValue: String): StringPreferenceLiveData {
    return StringPreferenceLiveData(this, key, defaultValue)
}

fun SharedPreferences.toLiveData(key: String, defaultValue: Boolean): BooleanPreferenceLiveData {
    return BooleanPreferenceLiveData(this, key, defaultValue)
}
