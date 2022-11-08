package com.example.test2effectivemobile.common.di

import com.example.test2effectivemobile.domain.repository.StoreRepository
import com.example.test2effectivemobile.domain.usecases.LoadBestSellerUseCase
import com.example.test2effectivemobile.domain.usecases.LoadHotSalesUseCase
import com.example.test2effectivemobile.domain.usecases.LoadPhoneImagesUseCase
import com.example.test2effectivemobile.domain.usecases.LoadProductDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn (ViewModelComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideLoadHotSalesUseCase(repository: StoreRepository) = LoadHotSalesUseCase(repository = repository)

    @Provides
    fun provideBestSellerUseCase(repository: StoreRepository) = LoadBestSellerUseCase(repository = repository)

    @Provides
    fun provideProductDetailsUseCase(repository: StoreRepository) = LoadProductDetailsUseCase(repository = repository)

    @Provides
    fun providePhoneImagesUseCase(repository: StoreRepository) = LoadPhoneImagesUseCase(repository = repository)




}