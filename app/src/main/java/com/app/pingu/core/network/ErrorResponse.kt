package com.app.pingu.core.network

data class ApiException(
    val type: String?,
    val title: String?,
    val status: Int?,
    val detail: String?
) : Throwable()