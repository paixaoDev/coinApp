package com.paixao.dev.mbtest.presentation.model

import com.paixao.dev.mbtest.domain.entities.ExchangeDetailEntity

data class ExchangeDetails(
    val simpleDetails: ExchangeSimpleDetails
)

data class ExchangeSimpleDetails(
    val id: String,
    val name: String,
    val website: String?,
    val volumeHrs: String,
    val volumeDay: String,
    val volumeMonth: String
)

fun ExchangeDetailEntity.toModel(): ExchangeDetails {
    return ExchangeDetails(
        simpleDetails = ExchangeSimpleDetails(
            id = this.id,
            name = this.name,
            website = this.website,
            volumeHrs = this.volumeHrs,
            volumeDay = this.volumeDay,
            volumeMonth = this.volumeMonth
        )
    )
}