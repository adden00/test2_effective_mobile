package com.example.test2effectivemobile.domain.usecases

import com.example.test2effectivemobile.domain.models.HotSalesItem
import com.example.test2effectivemobile.domain.repository.StoreRepository

class LoadHotSalesUseCase(private val repository: StoreRepository) {
    suspend fun execute(): List<HotSalesItem> {
        return repository.loadHotSales()
    }
}