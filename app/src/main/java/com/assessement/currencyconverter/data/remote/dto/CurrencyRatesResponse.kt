package com.assessement.currencyconverter.data.remote.dto

data class CurrencyRatesResponse(
    val success: Boolean,
    val timestamp: Long,
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)
