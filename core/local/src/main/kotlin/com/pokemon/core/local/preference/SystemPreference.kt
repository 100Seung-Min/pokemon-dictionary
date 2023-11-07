package com.pokemon.core.local.preference

interface SystemPreference {
    suspend fun saveLanguage(language: String)

    suspend fun fetchLanguage(): String?

    suspend fun saveIsDarkTheme(isDarkTheme: Boolean)

    suspend fun fetchIsDarkTheme(): Boolean
}