package com.paixao.dev.mbtest.presentation.model

import com.paixao.dev.mbtest.domain.entities.ExchangeEntity
import com.paixao.dev.mbtest.extensions.toCurrency

data class ExchangeDetails(
    val id: String,
    val name: String,
    val website: String?,
    val volumeHrs: String,
    val volumeDay: String,
    val volumeMonth: String
)

fun ExchangeEntity.toDetailModel(): ExchangeDetails {
    return ExchangeDetails(
        id = this.id,
        name = this.name,
        website = this.website,
        volumeHrs = this.volumeHrs.toCurrency(),
        volumeDay = this.volumeDay.toCurrency(),
        volumeMonth = this.volumeMonth.toCurrency()
    )
}