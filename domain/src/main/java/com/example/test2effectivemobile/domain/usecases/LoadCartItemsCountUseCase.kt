package com.example.test2effectivemobile.domain.usecases

import com.example.test2effectivemobile.domain.models.CartInfoItem
import com.example.test2effectivemobile.domain.repository.StoreRepository

class LoadCartItemsCountUseCase(private val repository: StoreRepository) {
    suspend fun execute(): Int {
        return repository.loadCartInfo()?.basket?.size ?: 0
    }

}