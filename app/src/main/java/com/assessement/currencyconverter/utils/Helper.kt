package com.assessement.currencyconverter.utils

import com.assessement.currencyconverter.R
import com.assessement.currencyconverter.data.remote.dto.HistoricalRatesResponse
import com.assessement.currencyconverter.domain.model.HistoricalRate

fun getFlagEmoji(currencyCode: String): String {
    val currencyToCountry = mapOf(
        "USD" to "US", "EUR" to "EU", "GBP" to "GB", "JPY" to "JP",
        "INR" to "IN", "AUD" to "AU", "CAD" to "CA", "CHF" to "CH",
        "CNY" to "CN", "SEK" to "SE", "NZD" to "NZ"
    )
    val countryCode = currencyToCountry[currencyCode] ?: "US"
    return countryCode.uppercase()
        .map { 0x1F1E6 + it.code - 'A'.code }
        .map { it.toChar() }
        .joinToString("")
}

fun HistoricalRatesResponse.toHistoricalRateList(targetCurrency: String): List<HistoricalRate> {
    return rates.mapNotNull { (date, rateMap) ->
        val rate = rateMap[targetCurrency]
        if (rate != null) {
            HistoricalRate(date = date, rate = rate)
        } else null
    }.sortedBy { it.date }
}

fun getFlagResourceId(currencyCode: String): Int {
    return when (currencyCode) {
        "USD" -> R.drawable.us
        "EUR" -> R.drawable.gb_eng
        "GBP" -> R.drawable.gb
        "JPY" -> R.drawable.jp
        "INR" -> R.drawable.`in`
        else -> R.drawable.gb_eng // Default flag in case it's not found
    }
}


