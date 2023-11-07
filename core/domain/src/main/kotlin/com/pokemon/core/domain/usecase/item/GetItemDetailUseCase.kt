package com.pokemon.core.domain.usecase.item

import com.pokemon.core.domain.repository.ItemRepository
import javax.inject.Inject

class GetItemDetailUseCase @Inject constructor(
    private val itemRepository: ItemRepository,
) {
    suspend operator fun invoke(itemId: Int) = kotlin.runCatching {
        itemRepository.getItemDetail(itemId = itemId)
    }
}