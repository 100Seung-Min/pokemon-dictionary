package com.pokemon.core.remote.datasource.move

import com.pokemon.core.remote.api.MoveAPI
import com.pokemon.core.remote.response.DetailMoveResponse
import com.pokemon.core.remote.util.pokemonApiCall
import javax.inject.Inject

class MoveRemoteDataSourceImpl @Inject constructor(
    private val moveAPI: MoveAPI,
) : MoveRemoteDataSource {
    override suspend fun getMoveDetail(moveId: Int): DetailMoveResponse = pokemonApiCall {
        moveAPI.getMoveDetail(moveId = moveId)
    }
}