package com.paixao.dev.mbtest.domain.entities

import java.math.BigDecimal

data class ExchangeEntity (
    val id: String,
    val name: String,
    val website: String?,
    var image: String? = "",
    val volumeHrs: BigDecimal,
    val volumeDay: BigDecimal,
    val volumeMonth: BigDecimal
)