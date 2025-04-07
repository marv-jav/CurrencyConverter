package com.assessement.currencyconverter.data.remote.dto

/**
 * Data class representing the API response for latest currency rates.
 */
data class CurrencyRatesResponse(
    val success: Boolean, // Indicates if the API call was successful
    val timestamp: Long, // The time when the rates were last updated (in UNIX format)
    val base: String, // The base currency for the conversion
    val date: String, // The date for which the rates apply
    val rates: Map<String, Double> // A map of target currency codes to their exchange rates
)
