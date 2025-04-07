package com.assessement.currencyconverter.data.repo

import com.assessement.currencyconverter.BuildConfig
import com.assessement.currencyconverter.data.local.dao.CurrencyRateDao
import com.assessement.currencyconverter.data.mapper.toDomain
import com.assessement.currencyconverter.data.mapper.toEntity
import com.assessement.currencyconverter.data.remote.api.CurrencyApiService
import com.assessement.currencyconverter.data.remote.dto.CurrencyRatesResponse
import com.assessement.currencyconverter.data.remote.dto.HistoricalRatesResponse
import com.assessement.currencyconverter.domain.model.CurrencyRate
import com.assessement.currencyconverter.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Implementation of [CurrencyRepository] to handle currency data operations.
 */
class CurrencyRepositoryImpl @Inject constructor(
    private val apiService: CurrencyApiService,
    private val dao: CurrencyRateDao
) : CurrencyRepository {

    // API key for accessing the currency API
    private val apiKey = BuildConfig.API_KEY

    /**
     * Fetches latest currency rates from API, caches them in DB, and returns the result.
     */
    override suspend fun fetchRatesFromApi(base: String): Result<List<CurrencyRate>> {
        return try {
            val response: CurrencyRatesResponse = apiService.getLatestRates(
                accessKey = apiKey,
                base = base,
                symbols = "GBP,JPY,EUR, USD, INR"
            )

            if (response.success) {
                // Convert the API response to domain model
                val rates = response.rates.map { (target, rate) ->
                    CurrencyRate(base = base, target = target, rate = rate)
                }

                // Store the converted entities into local database
                val currencyRateEntities = rates.map { it.toEntity() }
                dao.insertRates(currencyRateEntities)

                Result.success(rates)
            } else {
                Result.failure(Exception("API call failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Retrieves cached currency rates from local Room database.
     */
    override suspend fun getRatesFromDb(base: String): Flow<List<CurrencyRate>> {
        return dao.getRates(base).map { list -> list.map { it.toDomain() } }
    }

    /**
     * Fetches historical currency rates (timeseries) from the API.
     */
    override suspend fun getHistoricalRates(
        base: String,
        target: String,
        startDate: String,
        endDate: String
    ): Result<List<HistoricalRatesResponse>> {
        return try {
            val response = apiService.getHistoricalRates(
                accessKey = apiKey,
                base = base,
                symbols = target,
                startDate = startDate,
                endDate = endDate
            )

            // Transform the response into a list of HistoricalRatesResponse objects (one per date)
            val historicalRatesList = response.rates.map { (date, rateMap) ->
                HistoricalRatesResponse(
                    success = response.success,
                    timeseries = response.timeseries,
                    start_date = response.start_date,
                    end_date = response.end_date,
                    base = response.base,
                    rates = mapOf(date to rateMap)
                )
            }

            Result.success(historicalRatesList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
