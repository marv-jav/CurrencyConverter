package com.assessement.currencyconverter.utils

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


