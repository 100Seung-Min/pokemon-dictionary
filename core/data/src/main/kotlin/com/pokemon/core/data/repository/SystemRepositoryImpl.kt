package com.pokemon.core.data.repository

import com.pokemon.core.domain.repository.SystemRepository
import com.pokemon.core.local.datasource.SystemLocalDataSource
import javax.inject.Inject

class SystemRepositoryImpl @Inject constructor(
    private val systemLocalDataSource: SystemLocalDataSource,
) : SystemRepository {
    override suspend fun saveLanguage(language: String) =
        systemLocalDataSource.saveLanguage(language)

    override suspend fun fetchLanguage(): String? =
        systemLocalDataSource.fetchLanguage()
}