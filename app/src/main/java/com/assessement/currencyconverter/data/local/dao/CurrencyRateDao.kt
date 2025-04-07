package com.assessement.currencyconverter.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assessement.currencyconverter.data.local.entity.CurrencyRateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyRateDao {
    @Insert
    suspend fun insertRates(rates: List<CurrencyRateEntity>)

    @Query("SELECT * FROM currency_rates WHERE base = :base")
    fun getRates(base: String): Flow<List<CurrencyRateEntity>>
}