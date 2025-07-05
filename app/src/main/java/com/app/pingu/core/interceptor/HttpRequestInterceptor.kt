package com.app.pingu.core.interceptor

import com.app.pingu.core.event.DefaultUiEventPublisher
import com.app.pingu.core.event.UiEvent
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class HttpRequestInterceptor @Inject constructor(
    private val eventPublisher: DefaultUiEventPublisher,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val response = chain.proceed(originalRequest)
        if (response.code == ERROR_CODE_UNAUTHORIZED) {
            synchronized(this) {
                eventPublisher.publishUiEvent(UiEvent.NavigateToLogin)
            }
        }

        return response
    }

    companion object {
        private const val ERROR_CODE_UNAUTHORIZED = 401

    }
}
