package com.pokemon.core.remote.datasource.move

import com.pokemon.core.remote.response.move.DetailMoveResponse

interface MoveRemoteDataSource {
    suspend fun getMoveDetail(moveId: Int): DetailMoveResponse
}