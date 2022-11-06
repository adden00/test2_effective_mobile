package com.example.test2effectivemobile.domain.repository

import com.example.test2effectivemobile.domain.models.BestSellerItem
import com.example.test2effectivemobile.domain.models.HomeStoreItem
import com.example.test2effectivemobile.domain.models.HotSalesItem

interface StoreRepository {
//    suspend fun loadHomeStoreData(): HomeStoreItem
    suspend fun loadHotSales(): List<HotSalesItem>
    suspend fun loadBestSeller(): List<BestSellerItem>


}