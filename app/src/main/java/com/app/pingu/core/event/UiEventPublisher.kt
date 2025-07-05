package com.app.pingu.core.event

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


class UiEventPublisher : DefaultUiEventPublisher {
    private val _eventsFlow = MutableSharedFlow<UiEvent>()
    val eventsFlow: SharedFlow<UiEvent> = _eventsFlow.asSharedFlow()

    override fun publishUiEvent(event: UiEvent) {
        CoroutineScope(Dispatchers.Main).launch {
            _eventsFlow.emit(event)
        }

    }

    override fun observeUiEvent(): SharedFlow<UiEvent> {
        return eventsFlow
    }
}