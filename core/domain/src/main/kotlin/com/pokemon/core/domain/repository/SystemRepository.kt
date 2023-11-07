package com.pokemon.core.domain.repository

interface SystemRepository {
    suspend fun saveLanguage(language: String)
    suspend fun fetchLanguage(): String?

    suspend fun saveIsDarkTheme(isDarkTheme: Boolean)

    suspend fun fetchIsDarkTheme(): Boolean
}