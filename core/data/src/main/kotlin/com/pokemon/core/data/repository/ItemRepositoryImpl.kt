package com.pokemon.core.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.pokemon.core.domain.entity.DetailItemEntity
import com.pokemon.core.domain.entity.ItemEntity
import com.pokemon.core.domain.repository.ItemRepository
import com.pokemon.core.local.datasource.SystemLocalDataSource
import com.pokemon.core.remote.datasource.item.ItemRemoteDataSource
import com.pokemon.core.remote.response.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val itemRemoteDataSource: ItemRemoteDataSource,
    private val systemLocalDataSource: SystemLocalDataSource,
) : ItemRepository {
    override suspend fun getItemList(): Flow<PagingData<ItemEntity>> =
        itemRemoteDataSource.getItemList().map { it.map { it.toEntity() } }

    override suspend fun getItemDetail(itemId: Int): DetailItemEntity {
        var languageId = systemLocalDataSource.fetchLanguage() ?: "ko"
        if (languageId == "ja") languageId = "ja-Hrkt"
        else if (languageId == "zh") languageId = "zh-Hant"
        return itemRemoteDataSource.getItemDetail(itemId = itemId)
            .toEntity(languageId = languageId)
    }
}