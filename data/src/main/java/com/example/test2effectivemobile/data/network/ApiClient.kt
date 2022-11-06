package com.example.test2effectivemobile.data.network

import com.example.test2effectivemobile.domain.models.HomeStoreItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {

    @GET ("654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getHomeToreData(): Response<HomeStoreItem>
}