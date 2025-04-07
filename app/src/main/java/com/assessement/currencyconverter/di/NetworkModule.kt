package com.assessement.currencyconverter.di

import com.assessement.currencyconverter.data.local.dao.CurrencyRateDao
import com.assessement.currencyconverter.data.remote.api.CurrencyApiService
import com.assessement.currencyconverter.data.repo.CurrencyRepositoryImpl
import com.assessement.currencyconverter.domain.repository.CurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Hilt module that provides network-related dependencies like Retrofit,
 * API service, and the repository implementation.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://data.fixer.io/api/"

    // Provides a singleton Retrofit instance.
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Provides a singleton instance of the Currency API service.
    @Provides
    @Singleton
    fun provideCurrencyApiService(retrofit: Retrofit): CurrencyApiService {
        return retrofit.create(CurrencyApiService::class.java)
    }

    // Provides a singleton instance of the CurrencyRepository using the API service and DAO.
    @Provides
    @Singleton
    fun provideCurrencyRepository(
        apiService: CurrencyApiService,
        dao: CurrencyRateDao
    ): CurrencyRepository {
        return CurrencyRepositoryImpl(apiService, dao)
    }
}
