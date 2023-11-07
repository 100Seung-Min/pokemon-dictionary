package com.pokemon.core.local.preference

import android.content.SharedPreferences
import javax.inject.Inject

class SystemPreferenceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : SystemPreference {
    override suspend fun saveLanguage(language: String) =
        saveStringPreference(LANGUAGE, language)

    override suspend fun fetchLanguage(): String? =
        fetchStringPreference(LANGUAGE)

    override suspend fun saveIsDarkTheme(isDarkTheme: Boolean) =
        saveBooleanPreference(IS_DARK_THEME, isDarkTheme)

    override suspend fun fetchIsDarkTheme(): Boolean =
        fetchBooleanPreference(IS_DARK_THEME)

    private fun fetchStringPreference(key: String): String? =
        sharedPreferences.getString(key, null)

    private fun saveStringPreference(key: String, value: String) =
        editPreference { it.putString(key, value) }

    private fun fetchBooleanPreference(key: String): Boolean =
        sharedPreferences.getBoolean(key, false)

    private fun saveBooleanPreference(key: String, value: Boolean) =
        editPreference { it.putBoolean(key, value) }

    private fun editPreference(edit: (SharedPreferences.Editor) -> Unit) =
        sharedPreferences.edit().let {
            edit(it)
            it.apply()
        }

    companion object Key {
        const val LANGUAGE = "LANGUAGE"
        const val IS_DARK_THEME = "IS_DARK_THEME"
    }
}