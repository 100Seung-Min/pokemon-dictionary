package com.pokemon.core.domain.usecase.system

import com.pokemon.core.domain.repository.SystemRepository
import javax.inject.Inject

class SaveLanguageUseCase @Inject constructor(
    private val systemRepository: SystemRepository,
) {
    suspend operator fun invoke(language: String) = kotlin.runCatching {
        systemRepository.saveLanguage(language = language)
    }
}