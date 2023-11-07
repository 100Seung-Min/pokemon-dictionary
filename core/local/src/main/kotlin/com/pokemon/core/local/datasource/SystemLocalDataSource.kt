package com.pokemon.core.local.datasource

interface SystemLocalDataSource {
    suspend fun saveLanguage(language: String)

    suspend fun fetchLanguage(): String?

    suspend fun fetchLanguageId(): String
}