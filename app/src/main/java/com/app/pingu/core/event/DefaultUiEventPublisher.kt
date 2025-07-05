package com.app.pingu.core.event

import kotlinx.coroutines.flow.SharedFlow

interface DefaultUiEventPublisher {
    fun publishUiEvent(event: UiEvent)
    fun observeUiEvent(): SharedFlow<UiEvent>
}