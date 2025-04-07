package com.assessement.currencyconverter.data.mapper

import com.assessement.currencyconverter.data.local.entity.CurrencyRateEntity
import com.assessement.currencyconverter.domain.model.CurrencyRate

/**
 * Extension function to convert CurrencyRateEntity to CurrencyRate (domain model).
 * @return A CurrencyRate object.
 */
fun CurrencyRateEntity.toDomain(): CurrencyRate {
    return CurrencyRate(
        base = base,  // The base currency
        target = target,  // The target currency
        rate = rate  // The conversion rate
    )
}

/**
 * Extension function to convert CurrencyRate (domain model) to CurrencyRateEntity (data model).
 * @return A CurrencyRateEntity object.
 */
fun CurrencyRate.toEntity(): CurrencyRateEntity {
    return CurrencyRateEntity(
        base = this.base,  // The base currency
        target = this.target,  // The target currency
        rate = this.rate // The conversion rate
    )
}