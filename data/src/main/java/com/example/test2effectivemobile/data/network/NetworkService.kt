package com.example.test2effectivemobile.data.network

import android.util.Log
import com.example.test2effectivemobile.domain.models.CartInfoItem
import com.example.test2effectivemobile.domain.models.HomeStoreItem
import com.example.test2effectivemobile.domain.models.ProductDetailsItem


class NetworkService(private val api: ApiClient) {
    suspend fun loadHomeStoreData(): HomeStoreItem? {
        var result: HomeStoreItem? = null
        try {
            result = api.getHomeStoreData().body()
        }
        catch (e: Exception) {
        }

        return result
    }

    suspend fun loadProductDetails(): ProductDetailsItem? {
        var result: ProductDetailsItem? = null
        try {
            result = api.getProductDetails().body()
        }
        catch (e: java.lang.Exception) {
        }
        return result
    }

    suspend fun loadCartInfo(): CartInfoItem? {
        var result: CartInfoItem? = null
        try {
            result = api.getCartInfo().body()
        }
        catch (e: java.lang.Exception) {
        }
        return result
    }
}