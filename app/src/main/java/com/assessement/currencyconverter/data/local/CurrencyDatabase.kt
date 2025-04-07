package com.assessement.currencyconverter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assessement.currencyconverter.data.local.dao.CurrencyRateDao
import com.assessement.currencyconverter.data.local.entity.CurrencyRateEntity

@Database(entities = [CurrencyRateEntity::class], version = 1)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun currencyRateDao(): CurrencyRateDao
}
