package com.example.test2effectivemobile.data.network

import com.example.test2effectivemobile.domain.models.HomeStoreItem
import com.example.test2effectivemobile.domain.models.ProductDetailsItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {

    @GET ("654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getHomeStoreData(): Response<HomeStoreItem>

    @GET ("6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getProductDetails(): Response<ProductDetailsItem>
}