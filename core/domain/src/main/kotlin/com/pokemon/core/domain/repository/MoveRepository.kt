package com.pokemon.core.domain.repository

import com.pokemon.core.domain.entity.DetailMoveEntity

interface MoveRepository {
    suspend fun getMoveDetail(moveId: Int): DetailMoveEntity
}