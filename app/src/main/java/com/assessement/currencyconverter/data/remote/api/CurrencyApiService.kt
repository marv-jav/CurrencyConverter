package com.assessement.currencyconverter.data.remote.api

import com.assessement.currencyconverter.data.remote.dto.CurrencyRatesResponse
import com.assessement.currencyconverter.data.remote.dto.HistoricalRatesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {
    @GET("latest")
    suspend fun getLatestRates(
        @Query("access_key") accessKey: String,
        @Query("base") base: String,
        @Query("symbols") symbols: String
    ): CurrencyRatesResponse

    @GET("timeseries")
    suspend fun getHistoricalRates(
        @Query("access_key") accessKey: String,
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("base") base: String,
        @Query("symbols") symbols: String
    ): HistoricalRatesResponse
}
