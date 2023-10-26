package com.pokemon.core.domain.usecase.system

import com.pokemon.core.domain.repository.SystemRepository
import javax.inject.Inject

class FetchLanguageUseCase @Inject constructor(
    private val systemRepository: SystemRepository,
) {
    suspend operator fun invoke() = kotlin.runCatching {
        systemRepository.fetchLanguage()
    }
}