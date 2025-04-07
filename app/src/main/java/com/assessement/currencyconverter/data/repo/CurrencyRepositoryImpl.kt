package com.assessement.currencyconverter.data.repo

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

class CurrencyRepositoryImpl @Inject constructor(
    private val apiService: CurrencyApiService,
    private val dao: CurrencyRateDao
) : CurrencyRepository {

    override suspend fun fetchRatesFromApi(base: String): Result<List<CurrencyRate>> {
        return try {
            // Call the API
            val response: CurrencyRatesResponse = apiService.getLatestRates(
                accessKey = "9d42731c9f2a208507bf99171c4a93ad",
                base = base,
                symbols = "GBP,JPY,EUR" // Example currencies, you can update it dynamically
            )

            // If the API call was successful
            if (response.success) {
                val rates = response.rates.map { (target, rate) ->
                    CurrencyRate(base = base, target = target, rate = rate)
                }

                val currencyRateEntities = rates.map { it.toEntity() } // Convert to Entity
                dao.insertRates(currencyRateEntities) // Insert into Room

                Result.success(rates) // Return the rates as a result
            } else {
                Result.failure(Exception("API call failed"))
            }
        } catch (e: Exception) {
            Result.failure(e) // Return failure in case of an error
        }
    }

    override suspend fun getRatesFromDb(base: String): Flow<List<CurrencyRate>> {
        return dao.getRates(base).map { list -> list.map { it.toDomain() } }
    }

    override suspend fun getHistoricalRates(
        base: String,
        target: String,
        startDate: String,
        endDate: String
    ): Result<List<HistoricalRatesResponse>> {
        return try {
            val response = apiService.getHistoricalRates(
                accessKey = "9d42731c9f2a208507bf99171c4a93ad",
                base = base,
                symbols = target,
                startDate = startDate,
                endDate = endDate
            )
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
