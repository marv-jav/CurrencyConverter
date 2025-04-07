package com.assessement.currencyconverter.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_rates")
data class CurrencyRateEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val base: String,
    val target: String,
    val rate: Double
)
