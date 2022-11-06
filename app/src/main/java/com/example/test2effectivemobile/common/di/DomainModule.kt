package com.example.test2effectivemobile.common.di

import com.example.test2effectivemobile.domain.repository.StoreRepository
import com.example.test2effectivemobile.domain.usecases.LoadHotSalesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn (ViewModelComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideLoadHotSalesUseCase(repository: StoreRepository) = LoadHotSalesUseCase(repository = repository)


}