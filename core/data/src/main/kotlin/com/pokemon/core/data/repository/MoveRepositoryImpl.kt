package com.pokemon.core.data.repository

import com.pokemon.core.data.mapper.remote.toEntity
import com.pokemon.core.domain.entity.DetailMoveEntity
import com.pokemon.core.domain.repository.MoveRepository
import com.pokemon.core.local.datasource.SystemLocalDataSource
import com.pokemon.core.remote.datasource.move.MoveRemoteDataSource
import javax.inject.Inject

class MoveRepositoryImpl @Inject constructor(
    private val moveRemoteDataSource: MoveRemoteDataSource,
    private val systemLocalDataSource: SystemLocalDataSource,
) : MoveRepository {
    override suspend fun getMoveDetail(moveId: Int): DetailMoveEntity =
        moveRemoteDataSource.getMoveDetail(moveId = moveId)
            .toEntity(languageId = systemLocalDataSource.fetchLanguageId())
}