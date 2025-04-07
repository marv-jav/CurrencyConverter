package com.assessement.currencyconverter.data.remote.api

import com.assessement.currencyconverter.data.remote.dto.CurrencyRatesResponse
import com.assessement.currencyconverter.data.remote.dto.HistoricalRatesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {

    /**
     * Fetches the latest currency rates.
     * @param accessKey The API access key.
     * @param base The base currency to convert from.
     * @param symbols A comma-separated list of target currencies to fetch rates for.
     * @return A [CurrencyRatesResponse] containing the latest rates.
     */
    @GET("latest")
    suspend fun getLatestRates(
        @Query("access_key") accessKey: String,  // API access key for authentication
        @Query("base") base: String,  // The base currency for conversion
        @Query("symbols") symbols: String  // The target currencies to get rates for
    ): CurrencyRatesResponse

    /**
     * Fetches historical currency rates for a given period.
     * @param accessKey The API access key.
     * @param startDate The start date of the historical data range.
     * @param endDate The end date of the historical data range.
     * @param base The base currency for conversion.
     * @param symbols A comma-separated list of target currencies to fetch rates for.
     * @return A [HistoricalRatesResponse] containing the historical rates.
     */
    @GET("timeseries")
    suspend fun getHistoricalRates(
        @Query("access_key") accessKey: String,  // API access key for authentication
        @Query("start_date") startDate: String,  // Start date for historical data
        @Query("end_date") endDate: String,  // End date for historical data
        @Query("base") base: String,  // The base currency for conversion
        @Query("symbols") symbols: String  // The target currencies to get historical rates for
    ): HistoricalRatesResponse
}
