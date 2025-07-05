package com.app.pingu.presentation.login

import com.app.pingu.core.base.CoreViewModel
import com.app.pingu.core.event.DefaultUiEventPublisher
import com.app.pingu.utils.resource.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    eventPublisher: DefaultUiEventPublisher,
    resourceProvider: ResourceProvider
) : CoreViewModel(eventPublisher, resourceProvider) {

}