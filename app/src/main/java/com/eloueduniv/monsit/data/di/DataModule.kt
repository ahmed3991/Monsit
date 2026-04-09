package com.eloueduniv.monsit.data.di

import com.eloueduniv.monsit.data.repository.CallRepository
import com.eloueduniv.monsit.data.repository.CallRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideCallRepository(): CallRepository = CallRepositoryImpl()
}
