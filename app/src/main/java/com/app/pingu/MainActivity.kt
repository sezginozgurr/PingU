package com.app.pingu

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.app.pingu.core.components.GenericLoadingScreen
import com.app.pingu.core.components.SitScaffold
import com.app.pingu.ui.theme.PingUTheme
import com.app.pingu.core.event.DefaultUiEventPublisher
import com.app.pingu.core.event.UiEvent
import com.app.pingu.navigation.NavigationGraph
import com.app.pingu.utils.extension.collectFlow
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var eventPublisher: DefaultUiEventPublisher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        actionBar?.hide()

        setContent {
            PingUTheme {
                var isLoading by remember { mutableStateOf(false) }
                SitScaffold {
                    val navController = rememberNavController()
                    val initialDestination =
                        viewModel.initialDestination.collectAsStateWithLifecycle().value

                    NavigationGraph(
                        navController = navController,
                        startDestination = initialDestination,
                        modifier = Modifier.fillMaxHeight()
                    )
                }
                collectFlow(eventPublisher.observeUiEvent()) { event ->
                    when (event) {
                        is UiEvent.LoadingEvent -> {
                            isLoading = event.showLoading
                        }

                        is UiEvent.ErrorEvent -> {
                            Toast.makeText(
                                this,
                                "Alınan Hata Mesajı; ${event.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        UiEvent.NavigateToLogin -> {
                            viewModel.updateDestination()
                        }
                    }
                }
                if (isLoading) {
                    GenericLoadingScreen()
                }
            }
        }
    }
}