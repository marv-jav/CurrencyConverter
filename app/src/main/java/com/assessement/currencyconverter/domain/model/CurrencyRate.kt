package com.assessement.currencyconverter.domain.model

data class CurrencyRate(
    val base: String,
    val target: String,
    val rate: Double
)