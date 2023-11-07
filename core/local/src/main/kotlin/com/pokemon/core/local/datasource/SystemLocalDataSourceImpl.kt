package com.pokemon.core.local.datasource

import com.pokemon.core.local.preference.SystemPreference
import javax.inject.Inject

class SystemLocalDataSourceImpl @Inject constructor(
    private val systemPreference: SystemPreference,
) : SystemLocalDataSource {
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
}