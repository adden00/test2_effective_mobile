package com.example.test2effectivemobile.domain.usecases

import com.example.test2effectivemobile.domain.models.BestSellerItem
import com.example.test2effectivemobile.domain.repository.StoreRepository

class LoadBestSellerUseCase(private val repository: StoreRepository) {

    suspend fun execute():List<BestSellerItem>{
        return repository.loadBestSeller()
    }

}