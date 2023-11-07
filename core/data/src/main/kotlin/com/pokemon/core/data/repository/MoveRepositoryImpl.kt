package com.pokemon.core.data.repository

import com.pokemon.core.domain.entity.DetailMoveEntity
import com.pokemon.core.domain.repository.MoveRepository
import com.pokemon.core.local.datasource.SystemLocalDataSource
import com.pokemon.core.remote.datasource.move.MoveRemoteDataSource
import com.pokemon.core.remote.response.toEntity
import javax.inject.Inject

class MoveRepositoryImpl @Inject constructor(
    private val moveRemoteDataSource: MoveRemoteDataSource,
    private val systemLocalDataSource: SystemLocalDataSource,
) : MoveRepository {
    override suspend fun getMoveDetail(moveId: Int): DetailMoveEntity {
        var languageId = systemLocalDataSource.fetchLanguage() ?: "ko"
        if (languageId == "ja") languageId = "ja-Hrkt"
        else if (languageId == "zh") languageId = "zh-Hant"
        return moveRemoteDataSource.getMoveDetail(moveId = moveId)
            .toEntity(languageId = languageId)
    }
}