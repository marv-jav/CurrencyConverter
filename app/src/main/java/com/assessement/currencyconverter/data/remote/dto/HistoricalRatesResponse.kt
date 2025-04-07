package com.assessement.currencyconverter.data.remote.dto

data class HistoricalRatesResponse(
    val success: Boolean,
    val timeseries: Boolean,
    val start_date: String,
    val end_date: String,
    val base: String,
    val rates: Map<String, Map<String, Double>>
)