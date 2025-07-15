package com.example.feature_credit.di

import com.example.feature_credit.data.CreditRepositoryImpl
import com.example.feature_credit.domain.GetCreditUseCase
import com.example.feature_credit.domain.UpdateCreditUseCase
import com.example.feature_credit.domain.repository.CreditRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import slot.machine.database.model.CreditDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CreditModule {

    @Provides
    @Singleton
    fun provideCreditRepository(
        creditDao: CreditDao
    ) : CreditRepository {
        return CreditRepositoryImpl(creditDao)
    }

    @Provides
    @Singleton
    fun provideUpdateCreditUseCase(
        creditRepository: CreditRepository
    ): UpdateCreditUseCase {
        return UpdateCreditUseCase(
            creditRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetCreditUseCase(
        creditRepository: CreditRepository
    ): GetCreditUseCase {
        return GetCreditUseCase(
            creditRepository
        )
    }
}