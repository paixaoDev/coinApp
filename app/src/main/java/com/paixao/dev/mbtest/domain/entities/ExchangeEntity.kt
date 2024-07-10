package com.paixao.dev.mbtest.domain.entities

import java.math.BigDecimal

data class ExchangeEntity (
    val id: String,
    val name: String,
    var image: String? = "",
    val volumeDay: BigDecimal,
)