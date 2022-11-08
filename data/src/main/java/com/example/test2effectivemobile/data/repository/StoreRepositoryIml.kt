package com.example.test2effectivemobile.data.repository

import com.example.test2effectivemobile.data.network.NetworkService
import com.example.test2effectivemobile.domain.models.BestSellerItem
import com.example.test2effectivemobile.domain.models.HotSalesItem
import com.example.test2effectivemobile.domain.models.ProductDetailsItem
import com.example.test2effectivemobile.domain.repository.StoreRepository

class StoreRepositoryIml(private val apiService: NetworkService): StoreRepository {
    override suspend fun loadHotSales(): List<HotSalesItem> {
        return apiService.loadHomeStoreData()?.home_store ?: listOf()
    }

    override suspend fun loadBestSeller(): List<BestSellerItem> {
        return apiService.loadHomeStoreData()?.best_seller ?: listOf()
    }

    override suspend fun loadProductDetails(): ProductDetailsItem? {
        return apiService.loadProductDetails()
    }
}