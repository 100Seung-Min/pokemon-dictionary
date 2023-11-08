package com.pokemon.core.data.local.datasource

interface SystemLocalDataSource {
    suspend fun saveLanguage(language: String)

    suspend fun fetchLanguage(): String?

    suspend fun fetchLanguageId(): String

    suspend fun saveIsDarkTheme(isDarkTheme: Boolean)

    suspend fun fetchIsDarkTheme(): Boolean
}