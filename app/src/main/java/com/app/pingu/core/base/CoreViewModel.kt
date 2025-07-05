package com.app.pingu.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pingu.R
import com.app.pingu.core.event.DefaultUiEventPublisher
import com.app.pingu.core.event.UiEvent
import com.app.pingu.core.exception.PingUException
import com.app.pingu.utils.resource.ResourceProvider
import com.app.pingu.core.network.ApiException
import com.app.pingu.core.network.RestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class CoreViewModel(
    private val eventPublisher: DefaultUiEventPublisher,
    open val resourceProvider: ResourceProvider
) : ViewModel() {

    private val _eventsFlow = MutableSharedFlow<UiEvent>()
    val eventsFlow: SharedFlow<UiEvent> = _eventsFlow.asSharedFlow()

    private var isLoadingShown = false

    fun <T> executeUseCase(
        result: Flow<RestResult<T>>,
        onSuccess: (suspend (T) -> Unit),
        onFailure: (suspend (throwable: PingUException) -> Unit) = { renderSiTaxiError(it) }
    ) {
        viewModelScope.launch {
            result.collectLatest { result ->
                when (result) {
                    is RestResult.Success -> {
                        onSuccess(result.data)
                    }

                    is RestResult.Failure -> {
                        when (val throwable = result.throwable) {
                            is PingUException -> onFailure.invoke(throwable)
                            is ApiException -> renderApiError(throwable)
                            else -> renderUnhandledError(throwable)
                        }

                    }

                    is RestResult.Loading -> {
                        handleLoading(result.isShowing)
                    }
                }
            }
        }
    }

    open fun renderUnhandledError(error: Throwable?) {
        error?.let {
            eventPublisher.publishUiEvent(UiEvent.ErrorEvent(resourceProvider.getString(R.string.generic_error)))
        }

        Timber.tag(this::class.java.name).d(error.toString())

    }

    open fun renderApiError(error: ApiException) {
        eventPublisher.publishUiEvent(
            UiEvent.ErrorEvent(
                message = error.detail ?: resourceProvider.getString(R.string.generic_error)
            )
        )

        Timber.tag(this::class.java.name).d(error.message.toString())

    }

    open fun renderSiTaxiError(error: Throwable?) {
        error?.let {
            eventPublisher.publishUiEvent(
                UiEvent.ErrorEvent(
                    message = error.message ?: resourceProvider.getString(
                        R.string.generic_error
                    )
                )
            )
        }

        Timber.tag(this::class.java.name).d(error.toString())

    }


    open fun handleLoading(showLoading: Boolean) {
        if (isLoadingShown != showLoading) {
            isLoadingShown = showLoading
            eventPublisher.publishUiEvent(UiEvent.LoadingEvent(showLoading))
        }
    }

    fun sendErrorEvent(event: UiEvent.ErrorEvent) {
        eventPublisher.publishUiEvent(event)
    }
}
