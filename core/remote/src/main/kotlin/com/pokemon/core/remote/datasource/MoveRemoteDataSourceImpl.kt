package com.pokemon.core.remote.datasource

import com.pokemon.core.data.remote.datasource.MoveRemoteDataSource
import com.pokemon.core.remote.api.MoveAPI
import com.pokemon.core.data.remote.response.move.DetailMoveResponse
import com.pokemon.core.data.remote.util.pokemonApiCall
import javax.inject.Inject

class MoveRemoteDataSourceImpl @Inject constructor(
    private val moveAPI: MoveAPI,
) : MoveRemoteDataSource {
    override suspend fun getMoveDetail(moveId: Int): DetailMoveResponse = pokemonApiCall {
        moveAPI.getMoveDetail(moveId = moveId)
    }
}