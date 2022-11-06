package com.example.test2effectivemobile.data.network

import android.util.Log
import com.example.test2effectivemobile.domain.models.HomeStoreItem


class NetworkService(private val api: ApiClient) {
    suspend fun loadHomeStoreData(): HomeStoreItem? {
        var result: HomeStoreItem? = null
        Log.d("MyLog", "loading...")
        try {
            result = api.getHomeToreData().body()
        }
        catch (e: Exception) {
            Log.d("MyLog", e.toString())
        }

        Log.d("MyLog", result.toString())
        return result
    }
}