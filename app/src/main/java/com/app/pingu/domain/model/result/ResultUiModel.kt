package com.app.pingu.domain.model.result

import kotlinx.serialization.Serializable

@Serializable
data class ResultUiModel(
    val status: ResultScreenType = ResultScreenType.SUCCESS,
    val successMessage: String = "",
    val errorMessage: String = "",
    val amount: String = "",
)

@Serializable
enum class ResultScreenType {
    SUCCESS
}