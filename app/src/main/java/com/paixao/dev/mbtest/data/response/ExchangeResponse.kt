package com.paixao.dev.mbtest.data.response

import com.paixao.dev.mbtest.domain.entities.ExchangeEntity

data class ExchangeResponse(
    val id: String,
    val name: String,
    val volumeHrs: Int,
    val volumeDay: Int,
    val volumeMonth: Int
)

internal fun ExchangeResponse.toEntity(): ExchangeEntity {
    return ExchangeEntity(
        id = id,
        name = name,
        volumeDay = volumeDay
    )
}
