package com.app.pingu.core.exception

data class PingUException(
    override val message: String,
    val errorCode: Int = -1
) : Throwable()