package com.eloueduniv.monsit.domain.di

import com.eloueduniv.monsit.data.repository.CallRepository
import com.eloueduniv.monsit.domain.usecase.GetResentCallsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideGetResentCallsUseCase(callRepository: CallRepository): GetResentCallsUseCase {
        return GetResentCallsUseCase(callRepository)
    }
}