package com.example.test2effectivemobile.domain.repository

import com.example.test2effectivemobile.domain.models.*

interface StoreRepository {
    suspend fun loadHotSales(): List<HotSalesItem>
    suspend fun loadBestSeller(): List<BestSellerItem>
    suspend fun loadProductDetails(): ProductDetailsItem?
    suspend fun loadCartInfo(): CartInfoItem?


}