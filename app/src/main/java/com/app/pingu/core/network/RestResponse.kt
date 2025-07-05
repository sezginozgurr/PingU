package com.app.pingu.core.network

data class RestResponse<T>(
    val status: Int = -1,
    val code: Int = -1,
    val message: String? = null,
    val data: T,
)
