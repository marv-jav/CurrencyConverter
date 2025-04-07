package com.assessement.currencyconverter.utils

import com.assessement.currencyconverter.R

// Function to get the flag resource ID for a given currency code (for displaying flags as images)
fun getFlagResourceId(currencyCode: String): Int {
    // Return a drawable resource ID based on the currency code
    return when (currencyCode) {
        "USD" -> R.drawable.us  // USD -> US flag
        "EUR" -> R.drawable.gb_eng  // EUR -> EU flag (or UK flag as proxy)
        "GBP" -> R.drawable.gb  // GBP -> GB flag
        "JPY" -> R.drawable.jp  // JPY -> Japan flag
        "INR" -> R.drawable.`in`  // INR -> India flag
        else -> R.drawable.gb_eng // Default to UK flag if not found
    }
}


