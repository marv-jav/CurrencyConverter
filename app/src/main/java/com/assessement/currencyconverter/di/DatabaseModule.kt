package com.assessement.currencyconverter.di

import android.content.Context
import androidx.room.Room
import com.assessement.currencyconverter.data.local.CurrencyDatabase
import com.assessement.currencyconverter.data.local.dao.CurrencyRateDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module to provide Room database and DAO instances.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    // Provides a singleton instance of the Room database.
    @Provides
    @Singleton
    fun provideCurrencyDatabase(@ApplicationContext context: Context): CurrencyDatabase {
        return Room.databaseBuilder(
            context,
            CurrencyDatabase::class.java,
            "currency_db"
        ).build()
    }

    // Provides the DAO from the database instance.
    @Provides
    fun provideCurrencyRateDao(database: CurrencyDatabase): CurrencyRateDao {
        return database.currencyRateDao()
    }
}
