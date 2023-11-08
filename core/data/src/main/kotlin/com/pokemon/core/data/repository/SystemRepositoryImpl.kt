package com.pokemon.core.data.repository

import com.pokemon.core.domain.repository.SystemRepository
import com.pokemon.core.data.local.datasource.SystemLocalDataSource
import javax.inject.Inject

class SystemRepositoryImpl @Inject constructor(
    private val systemLocalDataSource: SystemLocalDataSource,
) : SystemRepository {
    override suspend fun saveLanguage(language: String) =
        systemLocalDataSource.saveLanguage(language)

    override suspend fun fetchLanguage(): String? =
        systemLocalDataSource.fetchLanguage()

    override suspend fun saveIsDarkTheme(isDarkTheme: Boolean) =
        systemLocalDataSource.saveIsDarkTheme(isDarkTheme = isDarkTheme)

    override suspend fun fetchIsDarkTheme(): Boolean =
        systemLocalDataSource.fetchIsDarkTheme()
}