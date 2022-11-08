package com.example.test2effectivemobile.domain.usecases

import com.example.test2effectivemobile.domain.repository.StoreRepository

class LoadPhoneImagesUseCase(private val repository: StoreRepository) {

    suspend fun execute(): List<String> {
        val info = repository.loadProductDetails()
        return info?.images ?: listOf()
    }
}