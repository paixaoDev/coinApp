package com.paixao.dev.mbtest.domain.entities

data class ExchangeDetailEntity(
    val id: String,
    val name: String,
    val website: String?,
    val volumeHrs: String,
    val volumeDay: String,
    val volumeMonth: String
)
