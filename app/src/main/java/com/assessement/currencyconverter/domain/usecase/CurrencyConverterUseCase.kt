package com.assessement.currencyconverter.domain.usecase

import com.assessement.currencyconverter.data.remote.dto.HistoricalRatesResponse
import com.assessement.currencyconverter.domain.model.CurrencyRate
import com.assessement.currencyconverter.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyConverterUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {
    suspend fun fetchRatesFromApi(base: String): Result<List<CurrencyRate>> {
        return currencyRepository.fetchRatesFromApi(base)
    }

    suspend fun getRatesFromDb(base: String): Flow<List<CurrencyRate>> {
        return currencyRepository.getRatesFromDb(base)
    }

    suspend fun getHistoricalRates(
        base: String,
        target: String,
        startDate: String,
        endDate: String
    ): Result<List<HistoricalRatesResponse>> {
        return try {
            currencyRepository.getHistoricalRates(base, target, startDate, endDate)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}