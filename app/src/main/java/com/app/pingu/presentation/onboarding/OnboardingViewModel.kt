package com.app.pingu.presentation.onboarding

import androidx.lifecycle.viewModelScope
import com.app.pingu.core.base.CoreViewModel
import com.app.pingu.core.datastore.PreferencesKeys
import com.app.pingu.core.datastore.PreferencesManager
import com.app.pingu.core.event.DefaultUiEventPublisher
import com.app.pingu.utils.resource.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    eventPublisher: DefaultUiEventPublisher,
    resourceProvider: ResourceProvider,
    private val preferencesManager: PreferencesManager
) : CoreViewModel(eventPublisher, resourceProvider) {

    fun onGetStartedClick() {
        viewModelScope.launch {
            preferencesManager.saveBoolean(PreferencesKeys.IS_FIRST_RUN, false)
        }
    }
} 