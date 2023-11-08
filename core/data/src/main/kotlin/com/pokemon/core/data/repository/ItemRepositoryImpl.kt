package com.pokemon.core.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.pokemon.core.data.mapper.remote.toEntity
import com.pokemon.core.data.mapper.remote.toItemEntity
import com.pokemon.core.domain.entity.DetailItemEntity
import com.pokemon.core.domain.entity.ItemEntity
import com.pokemon.core.domain.repository.ItemRepository
import com.pokemon.core.data.local.datasource.SystemLocalDataSource
import com.pokemon.core.remote.datasource.item.ItemRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val itemRemoteDataSource: ItemRemoteDataSource,
    private val systemLocalDataSource: SystemLocalDataSource,
) : ItemRepository {
    override suspend fun getItemList(): Flow<PagingData<ItemEntity>> =
        itemRemoteDataSource.getItemList().map { it.map { it.toItemEntity() } }

    override suspend fun getItemDetail(itemId: Int): DetailItemEntity =
        itemRemoteDataSource.getItemDetail(itemId = itemId)
            .toEntity(languageId = systemLocalDataSource.fetchLanguageId())
}