package com.assessement.currencyconverter.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.assessement.currencyconverter.data.local.entity.CurrencyRateEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO interface to interact with the 'currency_rates' table in the Room database.
 */
@Dao
interface CurrencyRateDao {

    /**
     * Inserts a list of currency rates into the database.
     * @param rates List of currency rates to be inserted.
     */
    @Insert
    suspend fun insertRates(rates: List<CurrencyRateEntity>)

    /**
     * Fetches currency rates for a given base currency.
     * @param base The base currency (e.g., "USD").
     * @return A Flow of currency rates for the specified base.
     */
    @Query("SELECT * FROM currency_rates WHERE base = :base")
    fun getRates(base: String): Flow<List<CurrencyRateEntity>>
}
