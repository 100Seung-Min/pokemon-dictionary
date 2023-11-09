package com.pokemon.core.data.repository

import com.pokemon.core.data.remote.datasource.TypeRemoteDataSource
import com.pokemon.core.data.remote.response.type.toEntity
import com.pokemon.core.domain.entity.DetailTypeEntity
import com.pokemon.core.domain.repository.TypeRepository
import javax.inject.Inject

class TypeRepositoryImpl @Inject constructor(
    private val typeRemoteDataSource: TypeRemoteDataSource,
) : TypeRepository {
    override suspend fun getTypeDetail(typeId: Int): DetailTypeEntity =
        typeRemoteDataSource.getTypeDetail(typeId = typeId).toEntity()
}