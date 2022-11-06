package com.example.test2effectivemobile.common.di

import com.example.test2effectivemobile.common.constants.Constants
import com.example.test2effectivemobile.data.network.ApiClient
import com.example.test2effectivemobile.data.network.NetworkService
import com.example.test2effectivemobile.data.repository.StoreRepositoryIml
import com.example.test2effectivemobile.domain.repository.StoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn (SingletonComponent::class)
@Module
object DataModule {
    @Provides
    @Singleton
    fun provideApiClient(): ApiClient {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkService(api: ApiClient) = NetworkService(api = api)

    @Provides
    @Singleton
    fun provideRepository(networkService: NetworkService): StoreRepository {
        return StoreRepositoryIml(apiService = networkService)
    }

}