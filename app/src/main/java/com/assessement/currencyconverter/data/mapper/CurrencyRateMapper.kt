package com.assessement.currencyconverter.data.mapper

import com.assessement.currencyconverter.data.local.entity.CurrencyRateEntity
import com.assessement.currencyconverter.domain.model.CurrencyRate

fun CurrencyRateEntity.toDomain(): CurrencyRate {
    return CurrencyRate(
        base = base,
        target = target,
        rate = rate
    )
}

fun CurrencyRate.toEntity(): CurrencyRateEntity {
    return CurrencyRateEntity(
        base = this.base,
        target = this.target,
        rate = this.rate
    )
}