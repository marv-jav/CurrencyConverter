package com.assessement.currencyconverter.domain.usecase

import com.assessement.currencyconverter.data.remote.dto.HistoricalRatesResponse
import com.assessement.currencyconverter.domain.model.CurrencyRate
import com.assessement.currencyconverter.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case class for handling currency conversion logic.
 * It acts as a bridge between the ViewModel and Repository layers.
 */
class CurrencyConverterUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {

    // Fetch the latest currency rates from the API.
    suspend fun fetchRatesFromApi(base: String): Result<List<CurrencyRate>> {
        return currencyRepository.fetchRatesFromApi(base)
    }

    // Get cached currency rates from the local database.
    suspend fun getRatesFromDb(base: String): Flow<List<CurrencyRate>> {
        return currencyRepository.getRatesFromDb(base)
    }

    // Fetch historical currency rates between two currencies for a date range.
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
