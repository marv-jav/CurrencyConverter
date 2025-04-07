package com.assessement.currencyconverter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assessement.currencyconverter.data.local.dao.CurrencyRateDao
import com.assessement.currencyconverter.data.local.entity.CurrencyRateEntity

/**
 * Room Database class to handle the 'currency_rates' table.
 * This class provides access to the CurrencyRateDao.
 */
@Database(entities = [CurrencyRateEntity::class], version = 1)
abstract class CurrencyDatabase : RoomDatabase() {

    /**
     * Provides access to the CurrencyRateDao.
     * @return The CurrencyRateDao for database operations.
     */
    abstract fun currencyRateDao(): CurrencyRateDao
}
