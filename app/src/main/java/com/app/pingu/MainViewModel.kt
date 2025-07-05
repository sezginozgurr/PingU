package com.app.pingu

import androidx.lifecycle.viewModelScope
import com.app.pingu.core.base.CoreViewModel
import com.app.pingu.core.event.DefaultUiEventPublisher
import com.app.pingu.core.manager.AuthManager
import com.app.pingu.navigation.Destination
import com.app.pingu.utils.resource.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    eventPublisher: DefaultUiEventPublisher,
    override val resourceProvider: ResourceProvider,
    private val autManager: AuthManager,
) : CoreViewModel(eventPublisher, resourceProvider) {

    private val _initialDestination = MutableStateFlow<Destination>(Destination.Empty)
    val initialDestination: StateFlow<Destination> = _initialDestination

    init {
        checkFirstRun()
    }

    private fun dismissSplash(savedPhoneNumber: String?, isFirstRun: Boolean = true) {
        when {

            isFirstRun -> {
                _initialDestination.value = Destination.Onboarding
            }

            savedPhoneNumber != null -> {
                _initialDestination.value = Destination.Login
            }

            else -> {
                _initialDestination.value = Destination.Login
            }
        }
    }

    fun updateDestination() {
        autManager.clearAll()
        _initialDestination.value = Destination.Login
    }

    private fun checkFirstRun() { //todo
        viewModelScope.launch {
            val savedPhoneNumber = autManager.getPhoneNumber()

            dismissSplash(savedPhoneNumber)
        }
    }
}