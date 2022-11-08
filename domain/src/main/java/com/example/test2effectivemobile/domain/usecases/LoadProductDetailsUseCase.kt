package com.example.test2effectivemobile.domain.usecases

import com.example.test2effectivemobile.domain.models.ProductDetailsItem
import com.example.test2effectivemobile.domain.repository.StoreRepository

class LoadProductDetailsUseCase(private val repository: StoreRepository) {
    suspend fun execute(): ProductDetailsItem? {
        return repository.loadProductDetails()
    }

}