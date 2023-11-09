package com.pokemon.core.remote.datasource

import com.pokemon.core.data.remote.datasource.TypeRemoteDataSource
import com.pokemon.core.data.remote.response.type.DetailTypeResponse
import com.pokemon.core.remote.api.TypeAPI
import javax.inject.Inject

class TypeRemoteDataSourceImpl @Inject constructor(
    private val typeAPI: TypeAPI,
) : TypeRemoteDataSource {
    override suspend fun getTypeDetail(typeId: Int): DetailTypeResponse =
        typeAPI.getTypeDetail(typeId = typeId)
}