package com.pokemon.core.data.remote.datasource

import com.pokemon.core.data.remote.response.move.DetailMoveResponse

interface MoveRemoteDataSource {
    suspend fun getMoveDetail(moveId: Int): DetailMoveResponse
}