package com.pokemon.core.domain.repository

import com.pokemon.core.domain.entity.DetailTypeEntity

interface TypeRepository {
    suspend fun getTypeDetail(typeId: Int): DetailTypeEntity
}