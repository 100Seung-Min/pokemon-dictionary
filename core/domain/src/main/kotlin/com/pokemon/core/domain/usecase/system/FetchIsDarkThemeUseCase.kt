package com.pokemon.core.domain.usecase.system

import com.pokemon.core.domain.repository.SystemRepository
import javax.inject.Inject

class FetchIsDarkThemeUseCase @Inject constructor(
    private val systemRepository: SystemRepository,
) {
    suspend operator fun invoke() = kotlin.runCatching {
        systemRepository.fetchIsDarkTheme()
    }
}