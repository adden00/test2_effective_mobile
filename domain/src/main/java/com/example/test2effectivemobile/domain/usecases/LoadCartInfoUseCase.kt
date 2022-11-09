package com.example.test2effectivemobile.domain.usecases

import com.example.test2effectivemobile.domain.models.CartInfoItem
import com.example.test2effectivemobile.domain.repository.StoreRepository

class LoadCartInfoUseCase(private val repository: StoreRepository) {
    suspend fun execute(): CartInfoItem? {
        return repository.loadCartInfo()
    }

}