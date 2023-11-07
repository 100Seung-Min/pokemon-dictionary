package com.pokemon.core.remote.datasource.move

import com.pokemon.core.remote.response.DetailMoveResponse

interface MoveRemoteDataSource {
    suspend fun getMoveDetail(moveId: Int): DetailMoveResponse
}