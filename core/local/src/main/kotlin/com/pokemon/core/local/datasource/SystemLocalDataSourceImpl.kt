package com.pokemon.core.local.datasource

import com.pokemon.core.data.local.preference.SystemPreference
import javax.inject.Inject

class SystemLocalDataSourceImpl @Inject constructor(
    private val systemPreference: SystemPreference,
) : com.pokemon.core.data.local.datasource.SystemLocalDataSource {
    override suspend fun saveLanguage(language: String) =
        systemPreference.saveLanguage(language)

    override suspend fun fetchLanguage(): String? =
        systemPreference.fetchLanguage()

    override suspend fun fetchLanguageId(): String {
        var languageId = systemPreference.fetchLanguage() ?: "ko"
        if (languageId == "ja") languageId = "ja-Hrkt"
        else if (languageId == "zh") languageId = "zh-Hant"
        return languageId
    }

    override suspend fun saveIsDarkTheme(isDarkTheme: Boolean) =
        systemPreference.saveIsDarkTheme(isDarkTheme = isDarkTheme)

    override suspend fun fetchIsDarkTheme(): Boolean =
        systemPreference.fetchIsDarkTheme()
}