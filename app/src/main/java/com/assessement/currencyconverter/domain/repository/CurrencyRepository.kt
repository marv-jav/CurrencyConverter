package com.assessement.currencyconverter.domain.repository

import com.assessement.currencyconverter.domain.model.CurrencyRate
import com.assessement.currencyconverter.data.remote.dto.HistoricalRatesResponse
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface that defines the contract for fetching currency data.
 * Implemented by the data layer to provide data to the domain/viewmodel layers.
 */
interface CurrencyRepository {

    // Fetches the latest currency rates from the API based on the base currency.
    suspend fun fetchRatesFromApi(base: String): Result<List<CurrencyRate>>

    // Retrieves cached currency rates from the local database based on the base currency.
    suspend fun getRatesFromDb(base: String): Flow<List<CurrencyRate>>

    // Fetches historical currency exchange rates between two currencies for a given date range.
    suspend fun getHistoricalRates(
        base: String,
        target: String,
        startDate: String,
        endDate: String
    ): Result<List<HistoricalRatesResponse>>
}
