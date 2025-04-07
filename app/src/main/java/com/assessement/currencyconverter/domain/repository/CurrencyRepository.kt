package com.assessement.currencyconverter.domain.repository

import com.assessement.currencyconverter.domain.model.CurrencyRate
import com.assessement.currencyconverter.data.remote.dto.HistoricalRatesResponse
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    suspend fun fetchRatesFromApi(base: String): Result<List<CurrencyRate>>
    suspend fun getRatesFromDb(base: String): Flow<List<CurrencyRate>>
    suspend fun getHistoricalRates(
        base: String,
        target: String,
        startDate: String,
        endDate: String
    ): Result<List<HistoricalRatesResponse>>

}