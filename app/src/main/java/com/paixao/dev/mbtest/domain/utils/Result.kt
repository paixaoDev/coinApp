package com.paixao.dev.mbtest.domain.utils

sealed class Result<out A, out B> {

    data class Success<A> constructor(
        val data: A
    ) : Result<A, Nothing>()

    data class Error<B> constructor(
        val error: B
    ) : Result<Nothing, B>()

    data class Failure constructor(
        val throwable: Throwable? = null
    ) : Result<Nothing, Nothing>()
}