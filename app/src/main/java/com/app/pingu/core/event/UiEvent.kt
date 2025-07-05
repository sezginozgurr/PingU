package com.app.pingu.core.event

sealed interface UiEvent {
    data class ErrorEvent(val message: String) : UiEvent
    data class LoadingEvent(val showLoading: Boolean) : UiEvent
    data object NavigateToLogin : UiEvent
}

