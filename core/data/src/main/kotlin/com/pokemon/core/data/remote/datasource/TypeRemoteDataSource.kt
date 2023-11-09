package com.pokemon.core.data.remote.datasource

import com.pokemon.core.data.remote.response.type.DetailTypeResponse

interface TypeRemoteDataSource {
    suspend fun getTypeDetail(typeId: Int): DetailTypeResponse
}