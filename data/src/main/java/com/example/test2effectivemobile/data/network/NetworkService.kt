package com.example.test2effectivemobile.data.network

import android.util.Log
import com.example.test2effectivemobile.domain.models.HomeStoreItem
import com.example.test2effectivemobile.domain.models.ProductDetailsItem


class NetworkService(private val api: ApiClient) {
    suspend fun loadHomeStoreData(): HomeStoreItem? {
        var result: HomeStoreItem? = null
        Log.d("MyLog", "loading...")
        try {
            result = api.getHomeStoreData().body()
        }
        catch (e: Exception) {
            Log.d("MyLog", e.toString())
        }

        Log.d("MyLog", result.toString())
        return result
    }

    suspend fun loadProductDetails(): ProductDetailsItem? {
        var result: ProductDetailsItem? = null
        try {
            result = api.getProductDetails().body()
        }
        catch (e: java.lang.Exception) {
            Log.d("MyLog", e.toString())
        }
        return result
    }
}